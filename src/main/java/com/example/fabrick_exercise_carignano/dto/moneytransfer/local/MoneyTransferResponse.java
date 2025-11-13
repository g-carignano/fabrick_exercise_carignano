package com.example.fabrick_exercise_carignano.dto.moneytransfer.local;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MoneyTransferResponse {

    private String moneyTransferId;

    private String status;

    private String direction;

    private Creditor creditor;

    private Debtor debtor;

    private String cro;

    private String trn;

    private String uri;

    private String description;

    private LocalDateTime createdDatetime;

    private LocalDateTime accountedDatetime;

    private LocalDate debtorValueDate;

    private LocalDate creditorValueDate;

    private Amount amount;

    private boolean isUrgent;

    private boolean isInstant;

    private String feeType;

    private String feeAccountID;

    private List<Fee> fees;

    private boolean hasTaxRelief;

}
