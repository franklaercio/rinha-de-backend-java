package com.github.rinha.domain;

public class Cliente {

    private int id;
    private int limite;
    private int saldo;

    public Cliente() {
    }

    public Cliente(int limite, int saldo) {
        this.limite = limite;
        this.saldo = saldo;
    }

    public Cliente(int id, int limite, int saldo) {
        this.id = id;
        this.limite = limite;
        this.saldo = saldo;
    }

    public boolean hasSaldoInsuficiente(int valor) {
        return this.saldo + this.limite <= valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
