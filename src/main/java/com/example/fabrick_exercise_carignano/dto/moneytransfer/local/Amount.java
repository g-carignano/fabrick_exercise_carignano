package com.example.fabrick_exercise_carignano.dto.moneytransfer.local;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Amount {

    private Number debtorAmount;

    private String debtorCurrency;

    private Number creditorAmount;

    private String creditorCurrency;

    private LocalDate creditorCurrencyDate;

    private Number exchangeRate;

}
