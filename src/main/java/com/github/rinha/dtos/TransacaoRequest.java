package com.github.rinha.dtos;

import org.apache.logging.log4j.util.Strings;

public record TransacaoRequest(String valor, String tipo, String descricao) {

    public boolean isValid() {
        return this.valor != null && this.tipo != null && this.descricao != null;
    }

    public boolean isValorValid() {
        if(Strings.isBlank(this.valor)) return false;

        try {
            return Integer.parseInt(this.valor) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isTipoValid() {
        if(Strings.isBlank(this.tipo)) return false;

        return this.tipo.equals("c") || this.tipo.equals("d");
    }

    public boolean isDescricaoValid() {
        if (Strings.isBlank(this.descricao)) return false;

        return this.descricao.length() <= 10;
    }

    public boolean isRequestValid() {
        return this.isValid() && this.isValorValid() && this.isTipoValid() && this.isDescricaoValid();
    }

    public int parseValorToInt() {
        return Integer.parseInt(this.valor);
    }
}
