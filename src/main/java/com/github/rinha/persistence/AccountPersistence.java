package com.github.rinha.persistence;

import com.github.rinha.domain.Customer;
import com.github.rinha.domain.dtos.TransactionResponse;
import com.github.rinha.utils.ConstantsUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AccountPersistence {

    private static final String CUSTOMER_ID_SQL = "id";
    private static final String CUSTOMER_MAX_LIMIT_SQL = "max_limit";
    private static final String CUSTOMER_BALANCE_SQL = "balance";

    private static final String TRANSACTION_VALUE_SQL = "amount";
    private static final String TRANSACTION_TYPE_SQL = "kind";
    private static final String TRANSACTION_DESCRIPTION_SQL = "description";
    private static final String TRANSACTION_DATE_SQL = "created_at";

    private static final String FIND_CUSTOMER_BY_ID_QUERY = "SELECT id, max_limit, balance FROM CUSTOMER WHERE id = ?";
    private static final String FIND_CUSTOMER_BY_ID_FOR_UPDATE_QUERY = "SELECT id, max_limit, balance FROM CUSTOMER WHERE id = ? FOR UPDATE";
    private static final String UPDATE_CUSTOMER_BALANCE_QUERY = "UPDATE CUSTOMER SET balance = ? WHERE id = ?";
    private static final String INSERT_TRANSACTION_QUERY = "INSERT INTO CUSTOMER_TRANSACTION (id_customer, amount, kind, description, created_at) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_LAST_10_TRANSACTION_BY_CUSTOMER_ID_QUERY = "SELECT amount, kind, description, created_at FROM CUSTOMER_TRANSACTION WHERE id_customer = ? ORDER BY created_at DESC LIMIT 10";
    private static final String EXIST_CUSTOMER_BY_ID_QUERY = "SELECT EXISTS(SELECT 1 FROM CUSTOMER WHERE id = ?)";

    private final JdbcTemplate jdbcTemplate;

    public AccountPersistence(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Customer findCustomerById(int id) {
        return this.jdbcTemplate.queryForObject(
                FIND_CUSTOMER_BY_ID_QUERY,
                new Object[]{id},
                (rs, rowNum) -> new Customer(
                        rs.getInt(CUSTOMER_ID_SQL),
                        rs.getInt(CUSTOMER_MAX_LIMIT_SQL),
                        rs.getInt(CUSTOMER_BALANCE_SQL)
                )
        );
    }

    public List<TransactionResponse> findTransactionsByCustomerId(int id) {
        return this.jdbcTemplate.query(FIND_LAST_10_TRANSACTION_BY_CUSTOMER_ID_QUERY, new Object[]{id},
                (rs, rowNum) -> new TransactionResponse(
                        rs.getInt(TRANSACTION_VALUE_SQL),
                        rs.getString(TRANSACTION_TYPE_SQL),
                        rs.getString(TRANSACTION_DESCRIPTION_SQL),
                        formatDateToString(rs.getTimestamp(TRANSACTION_DATE_SQL))
                )
        );
    }

    private static String formatDateToString(Timestamp timestamp) {
        return timestamp.toLocalDateTime()
                .atZone(ZoneId.of(ConstantsUtil.TIME_ZONE))
                .format(DateTimeFormatter.ofPattern(ConstantsUtil.DATE_FORMAT));
    }

    public void updateBalanceAndInsertTransaction(int customerId, int newBalance, int value, String transactionType, String description) {
        insertTransaction(customerId, value, transactionType, description);
        updateBalance(customerId, newBalance);
    }

    public Customer findCustomerForUpdate(int id) {
        return this.jdbcTemplate.queryForObject(FIND_CUSTOMER_BY_ID_FOR_UPDATE_QUERY, new Object[]{id},
                (rs, rowNum) -> new Customer(
                        rs.getInt(CUSTOMER_ID_SQL),
                        rs.getInt(CUSTOMER_MAX_LIMIT_SQL),
                        rs.getInt(CUSTOMER_BALANCE_SQL)
                )
        );
    }

    private void updateBalance(int id, int balance) {
        this.jdbcTemplate.update(UPDATE_CUSTOMER_BALANCE_QUERY, balance, id);
    }

    private void insertTransaction(int id, int value, String transactionType, String description) {
        this.jdbcTemplate.update(INSERT_TRANSACTION_QUERY,
                id, value, transactionType, description,
                Timestamp.valueOf(LocalDateTime.now().atZone(ZoneId.of(ConstantsUtil.TIME_ZONE)).toLocalDateTime()));
    }

    public boolean existsCustomerById(int id) {
        return this.jdbcTemplate.queryForObject(EXIST_CUSTOMER_BY_ID_QUERY, new Object[]{id},
                (rs, rowNum) -> rs.getBoolean(1)
        );
    }
}
