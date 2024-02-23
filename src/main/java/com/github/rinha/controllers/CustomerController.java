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
        if (id < 1 || id > 5) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        return accountService.findStatementByCustomerId(id)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/clientes/{id}/transacoes")
    public Mono<ResponseEntity<CustomerDTO>> transacionar(@PathVariable int id, @RequestBody TransactionRequest transaction) {
        if (id < 1 || id > 5) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        if (!transaction.isRequestValid()) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
        }

        return accountService.updateBalanceAndInsertTransaction(id, transaction.parseValueToInt(), transaction.tipo(), transaction.descricao())
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }
}
