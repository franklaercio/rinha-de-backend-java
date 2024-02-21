package com.github.rinha.controllers;

import com.github.rinha.domain.dtos.CustomerDTO;
import com.github.rinha.domain.dtos.StatementDTO;
import com.github.rinha.domain.dtos.TransactionRequest;
import com.github.rinha.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    private final AccountService accountService;

    public CustomerController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/clientes/{id}/extrato")
    public Mono<ResponseEntity<StatementDTO>> getExtratoByClienteId(@PathVariable int id) {
        return Mono.just(id)
                .filterWhen(accountService::isValidCustomerId)
                .flatMap(accountService::findStatementByCustomerId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/clientes/{id}/transacoes")
    public Mono<ResponseEntity<CustomerDTO>> transacionar(@PathVariable int id, @RequestBody TransactionRequest transaction) {
        return Mono.just(id)
                .filterWhen(accountService::isValidCustomerId)
                .flatMap(unused -> accountService.isTransactionValid(transaction))
                .flatMap(clientId -> accountService.updateBalanceAndInsertTransaction(id, transaction.parseValueToInt(), transaction.tipo(), transaction.descricao()))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .onErrorReturn(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }
}
