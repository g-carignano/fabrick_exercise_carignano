package com.example.fabrick_exercise_carignano.localdto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionDTO {

    private BigDecimal amount;
    private String currency;
    private Date paymentDate;
    private String state;
    private String transactionGuid;
    private PaymentMethodDTO paymentMethod;
    private TransactionInfoDTO transactionInfo;

}
