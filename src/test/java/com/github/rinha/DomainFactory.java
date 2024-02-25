package com.github.rinha;

import com.github.rinha.domain.Account;
import com.github.rinha.domain.Balance;
import com.github.rinha.domain.Statement;
import com.github.rinha.domain.Transact;
import com.github.rinha.domain.Transaction;
import java.util.List;

public class DomainFactory {

  private DomainFactory() {
    throw new IllegalStateException("Utility class");
  }

  public static Transact createTransact() {
    return new Transact("100", "d", "Market");
  }

  public static Account createAccount() {
    return new Account(1000, 1000);
  }

  public static Balance createBalance() {
    return new Balance(1000, 1000);
  }

  public static Transaction createTransaction() {
    return new Transaction(100, "d", "Market", "2021-01-01T00:00:00Z");
  }

  public static Transact invalidTransact() {
    return new Transact("-a", "e", null);
  }

  public static Statement createStatement() {
    return new Statement(createBalance(), List.of(createTransaction()));
  }

}
