package com.example.fabrick_exercise_carignano.dto.moneytransfer.fabrick;

import com.example.fabrick_exercise_carignano.constants.ProjectConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AmountFabrick {

    private Number debtorAmount;

    private String debtorCurrency;

    private Number creditorAmount;

    private String creditorCurrency;

    @JsonFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT)
    private Date creditorCurrencyDate;

    private Number exchangeRate;

}
