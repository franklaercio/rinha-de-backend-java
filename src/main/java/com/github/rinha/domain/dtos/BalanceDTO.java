package com.github.rinha.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rinha.utils.ConstantsUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record BalanceDTO(int total, @JsonProperty("data_extrato") String dataExtrato, int limite) {

    public BalanceDTO(int total, int limite) {
        this(total, formatDataExtrato(), limite);
    }

    private static String formatDataExtrato() {
        return LocalDateTime.now()
                .atZone(ZoneId.of(ConstantsUtil.TIME_ZONE))
                .format(DateTimeFormatter.ofPattern(ConstantsUtil.DATE_FORMAT));
    }
}
