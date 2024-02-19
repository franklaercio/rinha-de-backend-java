package com.github.rinha.domain.dtos;

import com.github.rinha.utils.ConstantsUtil;
import org.apache.logging.log4j.util.Strings;

public record TransactionRequest(String valor, String tipo, String descricao) {

    public boolean isValid() {
        return this.valor != null && this.tipo != null && this.descricao != null;
    }

    public boolean isValueValid() {
        if(Strings.isBlank(this.valor)) return false;

        try {
            return Integer.parseInt(this.valor) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isTypeValid() {
        if(Strings.isBlank(this.tipo)) return false;

        return this.tipo.equals(ConstantsUtil.TRANSACTION_TYPE_CREDIT) || this.tipo.equals(ConstantsUtil.TRANSACTION_TYPE_DEBIT);
    }

    public boolean isDescriptionValid() {
        if (Strings.isBlank(this.descricao)) return false;

        return this.descricao.length() <= 10;
    }

    public boolean isRequestValid() {
        return this.isValid() && this.isValueValid() && this.isTypeValid() && this.isDescriptionValid();
    }

    public int parseValueToInt() {
        return Integer.parseInt(this.valor);
    }
}
