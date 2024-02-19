package com.github.rinha.domain;

public class Customer {

    private int id;
    private int maxLimit;
    private int balance;

    public Customer() {
    }

    public Customer(int maxLimit, int balance) {
        this.maxLimit = maxLimit;
        this.balance = balance;
    }

    public Customer(int id, int maxLimit, int balance) {
        this.id = id;
        this.maxLimit = maxLimit;
        this.balance = balance;
    }

    public boolean hasEnoughBalance(int valor) {
        return this.balance + this.maxLimit <= valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
