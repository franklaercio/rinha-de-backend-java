package com.github.rinha.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.rinha.dtos.TransacaoResponse;

import java.util.List;

public record ExtratoResponse(Saldo saldo, @JsonInclude(JsonInclude.Include.NON_NULL) List<TransacaoResponse> ultimas_transacoes) {
    public record Saldo(int total, String data_extrato, int limite) {
    }
}
