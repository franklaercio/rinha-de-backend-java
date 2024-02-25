package com.github.rinha.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

  @JsonProperty("valor")
  private int amount;

  @JsonProperty("tipo")
  private String kind;

  @JsonProperty("descricao")
  private String description;

  @JsonProperty("realizada_em")
  private String createdAt;

  public Transaction() {
  }

  public Transaction(int amount, String kind, String description, String createdAt) {
    this.amount = amount;
    this.kind = kind;
    this.description = description;
    this.createdAt = createdAt;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
