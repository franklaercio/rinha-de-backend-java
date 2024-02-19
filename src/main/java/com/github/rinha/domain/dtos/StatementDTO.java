package com.github.rinha.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.rinha.domain.Customer;

import java.util.List;

public record StatementDTO(BalanceDTO saldo, @JsonInclude(JsonInclude.Include.NON_NULL) List<TransactionResponse> ultimas_transacoes) {

    public StatementDTO(Customer customer, List<TransactionResponse> transacoes) {
        this(new BalanceDTO(customer.getBalance(), customer.getMaxLimit()), transacoes);
    }
}
