package com.example.fabrick_exercise_carignano.fabrickdto.transaction.local;

import com.example.fabrick_exercise_carignano.constants.ProjectConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Transaction {

    private String transactionId;

    private String operationId;

    @JsonFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT)
    private Date accountingDate;

    @JsonFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT)
    private Date valueDate;

    private TransactionType type;

    private Number amount;

    private String currency;

    private String description;


}
