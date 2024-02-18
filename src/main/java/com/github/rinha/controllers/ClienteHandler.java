package com.github.rinha.controllers;

import com.github.rinha.dtos.ClienteResponse;
import com.github.rinha.dtos.ExtratoResponse;
import com.github.rinha.dtos.TransacaoRequest;
import com.github.rinha.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class ClienteHandler {

    private final ClienteService clienteService;

    public ClienteHandler(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes/{id}/extrato")
    public Mono<ResponseEntity<ExtratoResponse>> getExtratoByClienteId(@PathVariable int id) {
        if (id < 1 || id > 5) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        return clienteService.getExtratoByClienteId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Transactional
    @PostMapping("/clientes/{id}/transacoes")
    public Mono<ResponseEntity<ClienteResponse>> transacionar(@PathVariable int id, @RequestBody TransacaoRequest transacao) {
        if (id < 1 || id > 5) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        if (!transacao.isRequestValid()) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
        }

        return clienteService.updateSaldoAndInsertTransacao(id, transacao.parseValorToInt(), transacao.tipo(), transacao.descricao())
                .map(cliente -> {
                    var response = new ClienteResponse(cliente.getLimite(), cliente.getSaldo());
                    return ResponseEntity.ok(response);
                })
                .onErrorReturn(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }
}
