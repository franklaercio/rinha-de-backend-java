package com.github.rinha.services;

import com.github.rinha.domain.Account;
import com.github.rinha.domain.Balance;
import com.github.rinha.domain.Statement;
import com.github.rinha.domain.Transaction;
import com.github.rinha.domain.exceptions.UnprocessableException;
import com.github.rinha.domain.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

  private final JdbcTemplate jdbcTemplate;

  public AccountService(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Mono<Statement> retrieveStatement(int id) {
    Balance account = new Balance();
    List<Transaction> transactions = new ArrayList<>();

    this.jdbcTemplate.query(
        "SELECT c.max_limit, c.balance, t.amount, t.kind, t.description, t.created_at FROM account as c LEFT JOIN transactions as t ON t.id_account = c.id WHERE c.id = ? ORDER BY t.created_at DESC LIMIT 10",
        new Object[]{id},
        (rs, rowNum) -> {
          if (account.getLimit() == 0) {
            account.setLimit(rs.getInt("max_limit"));
            account.setAmount(rs.getInt("balance"));
          }
          if (rs.getInt("amount") != 0) {
            transactions.add(
                new Transaction(rs.getInt("amount"), rs.getString("kind"), rs.getString("description"),
                    rs.getString("created_at")));
          }
          return null;
        });

    return Mono.just(new Statement(account, transactions));
  }

  @Transactional
  public Mono<Account> processTransaction(int id, int value, String type, String description) {
    Account account = this.jdbcTemplate.queryForObject(
        "SELECT id, max_limit, balance FROM account WHERE id = ? FOR UPDATE", new Object[]{id},
        (rs, rowNum) -> new Account(rs.getInt("max_limit"), rs.getInt("balance")));

    if (type.equals("d") && account.hasEnoughBalance(value)) {
      return Mono.error(UnprocessableException::new);
    }

    int newBalance = type.equals("d") ? account.getBalance() - value : account.getBalance() + value;

    this.jdbcTemplate.update(
        "WITH ins AS ( " +
            "    INSERT INTO transactions (id_account, amount, kind, description, created_at) VALUES (?, ?, ?, ?, ?) " +
            "    RETURNING id_account " +
            ") " +
            "UPDATE account c " +
            "SET balance = ? " +
            "FROM ins " +
            "WHERE c.id = ins.id_account",
        id, value, type, description, TimeUtils.now(), newBalance);

    return Mono.just(new Account(account.getMaxLimit(), newBalance));
  }

}
