package com.github.rinha.services;

import com.github.rinha.domain.Customer;
import com.github.rinha.domain.dtos.CustomerDTO;
import com.github.rinha.domain.dtos.StatementDTO;
import com.github.rinha.domain.dtos.TransactionRequest;
import com.github.rinha.domain.dtos.TransactionResponse;
import com.github.rinha.domain.exceptions.UnprocessableException;
import com.github.rinha.persistence.AccountPersistence;
import com.github.rinha.utils.ConstantsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AccountService {

    private final AccountPersistence accountPersistence;

    public AccountService(AccountPersistence accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    @Transactional(readOnly = true)
    public Mono<StatementDTO> findStatementByCustomerId(int id) {
        Customer customer = this.accountPersistence.findCustomerById(id);
        List<TransactionResponse> transactions = this.accountPersistence.findTransactionsByCustomerId(id);

        return Mono.just(new StatementDTO(customer, transactions));
    }

    @Transactional
    public Mono<CustomerDTO> updateBalanceAndInsertTransaction(int id, int value, String type, String description) {
        Customer customer = this.accountPersistence.findCustomerForUpdate(id);
        if (type.equals(ConstantsUtil.TRANSACTION_TYPE_DEBIT) && customer.hasEnoughBalance(value)) {
            return Mono.error(UnprocessableException::new);
        }

        int newBalance = type.equals(ConstantsUtil.TRANSACTION_TYPE_DEBIT) ? customer.getBalance() - value : customer.getBalance() + value;
        this.accountPersistence.updateBalanceAndInsertTransaction(id, newBalance, value, type, description);

        return Mono.just(new CustomerDTO(customer.getMaxLimit(), newBalance));
    }

    public Mono<Boolean> isValidCustomerId(int id) {
        return Mono.just(this.accountPersistence.existsCustomerById(id));
    }

    public Mono<Boolean> isTransactionValid(TransactionRequest transaction) {
        return transaction.isRequestValid() ? Mono.just(true): Mono.error(UnprocessableException::new);
    }
}
