package com.github.rinha.controllers;

import com.github.rinha.domain.Cliente;
import com.github.rinha.dtos.ClienteResponse;
import com.github.rinha.dtos.ExtratoResponse;
import com.github.rinha.dtos.TransacaoRequest;
import com.github.rinha.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

//@RestController
public class ClienteController {

//    private final ClienteService clienteService;
//
//    public ClienteController(ClienteService clienteService) {
//        this.clienteService = clienteService;
//    }
//
//    @GetMapping("/clientes/{id}/extrato")
//    public Mono<ResponseEntity<?>> getExtratoByClienteId(@PathVariable int id) {
//        if (id < 1 || id > 5) {
//            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//        }
//
//        ExtratoResponse extrato = clienteService.getExtratoByClienteId(id);
//
//        return Mono.just(ResponseEntity.ok(extrato));
//    }
//
//    @Transactional
//    @PostMapping("/clientes/{id}/transacoes")
//    public Mono<ResponseEntity<?>> transacionar(@PathVariable int id, @RequestBody TransacaoRequest transacao) {
//        if (id < 1 || id > 5) {
//            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//        }
//
//        if (!transacao.isRequestValid()) {
//            return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
//        }
//
//        try {
//            Cliente cliente = this.clienteService.updateSaldoAndInsertTransacao(id, transacao.parseValorToInt(), transacao.tipo(), transacao.descricao());
//
//            var response = new ClienteResponse(cliente.getLimite(), cliente.getSaldo());
//
//            return Mono.just(ResponseEntity.ok(response));
//        } catch (IllegalArgumentException e) {
//            return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
//        }
//    }
}
