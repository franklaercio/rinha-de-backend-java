package com.github.rinha.controllers;

import com.github.rinha.domain.Account;
import com.github.rinha.domain.Statement;
import com.github.rinha.domain.Transact;
import com.github.rinha.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("/clientes/{id}/extrato")
  public Mono<ResponseEntity<Statement>> retrieveStatement(@PathVariable int id) {
    if (id < 1 || id > 5) {
      return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    return accountService.retrieveStatement(id)
        .map(ResponseEntity::ok)
        .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PostMapping("/clientes/{id}/transacoes")
  public Mono<ResponseEntity<Account>> transact(@PathVariable int id,
      @RequestBody Transact transact) {
    if (id < 1 || id > 5) {
      return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    if (!transact.isRequestValid()) {
      return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

    return accountService.processTransaction(id, transact.parseValueToInt(), transact.getKind(),
            transact.getDescription())
        .map(ResponseEntity::ok)
        .onErrorReturn(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
  }
}
