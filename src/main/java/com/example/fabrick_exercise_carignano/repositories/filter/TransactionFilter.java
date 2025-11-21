package com.example.fabrick_exercise_carignano.repositories.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFilter {
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Date fromDate;
    private Date toDate;
    private String circuit;
}
