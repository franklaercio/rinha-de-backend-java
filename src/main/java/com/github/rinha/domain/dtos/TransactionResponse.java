package com.github.rinha.domain.dtos;

public record TransactionResponse(int valor, String tipo, String descricao, String realizada_em) {
}
