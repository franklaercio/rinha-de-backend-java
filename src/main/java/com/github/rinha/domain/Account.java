package com.github.rinha.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

  @JsonProperty("limite")
  private int maxLimit;

  @JsonProperty("saldo")
  private int balance;

  public Account() {
  }

  public Account(int maxLimit, int balance) {
    this.maxLimit = maxLimit;
    this.balance = balance;
  }

  public boolean hasEnoughBalance(int valor) {
    return this.balance + this.maxLimit <= valor;
  }

  public int getMaxLimit() {
    return maxLimit;
  }

  public void setMaxLimit(int maxLimit) {
    this.maxLimit = maxLimit;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
