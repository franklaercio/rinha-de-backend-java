package com.github.rinha.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Statement {

  @JsonProperty("saldo")
  private Balance saldo;

  @JsonProperty("ultimas_transacoes")
  private List<Transaction> ultimasTransacoes;

  public Statement() {
  }

  public Statement(Balance saldo, List<Transaction> ultimasTransacoes) {
    this.saldo = saldo;
    this.ultimasTransacoes = ultimasTransacoes;
  }

  public Balance getSaldo() {
    return saldo;
  }

  public void setSaldo(Balance saldo) {
    this.saldo = saldo;
  }

  public List<Transaction> getUltimasTransacoes() {
    return ultimasTransacoes;
  }

  public void setUltimasTransacoes(List<Transaction> ultimasTransacoes) {
    this.ultimasTransacoes = ultimasTransacoes;
  }
}
