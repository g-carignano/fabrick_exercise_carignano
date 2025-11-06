package com.example.fabrick_exercise_carignano.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Getter
@Setter
public class BalanceResponse {
    private LocalDate date;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private String currency;
}
