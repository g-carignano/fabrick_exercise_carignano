package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MoneyTransferFabrickRequest {

    private Creditor creditor;

    private LocalDate executionDate;

    private String uri;

    private String description;

    private Number amount;

    private String currency;

    private Boolean isUrgent;

    private Boolean isInstant;

    private String feeType;

    private String feeAccountId;

    private TaxRelief taxRelief;

}
