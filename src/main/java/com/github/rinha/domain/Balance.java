package com.github.rinha.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rinha.domain.utils.TimeUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Balance {

  @JsonProperty("total")
  private int amount;

  @JsonProperty("data_extrato")
  private String date;

  @JsonProperty("limite")
  private int limit;

  public Balance() {
  }

  public Balance(int amount, int limit) {
    this.amount = amount;
    this.date = TimeUtils.nowUTCToString();
    this.limit = limit;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  @Override
  public String toString() {
    return "{" +
        "\"amount\"=" + amount +
        ", \"date\"='" + date + '\'' +
        ", \"limit\"=" + limit +
        '}';
  }
}
