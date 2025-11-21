package com.example.fabrick_exercise_carignano.localdto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionListRequestDTO {
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Date fromDate;
    private Date toDate;
    private String circuit;
}
