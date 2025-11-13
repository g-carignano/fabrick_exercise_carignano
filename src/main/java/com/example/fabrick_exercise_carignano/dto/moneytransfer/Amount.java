package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Amount {

    private Number debtorAmount;

    private String debtorCurrency;

    private Number creditorAmount;

    private String creditorCurrency;

    private LocalDate creditorCurrencyDate;

    private Number exchangeRate;

}
