package com.example.fabrick_exercise_carignano.dto.moneytransfer.fabrick;

import com.example.fabrick_exercise_carignano.constants.ProjectConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MoneyTransferFabrickRequest {

    private CreditorFabrick creditor;

    @JsonFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT)
    private Date executionDate;

    private String uri;

    private String description;

    private Number amount;

    private String currency;

    private Boolean isUrgent;

    private Boolean isInstant;

    private String feeType;

    private String feeAccountId;

    private TaxReliefFabrick taxRelief;

}
