package com.example.fabrick_exercise_carignano.fabrickdto.accountbalance;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class BalanceResponse {
    private LocalDate date;
    private Number balance;
    private Number availableBalance;
    private String currency;
}
