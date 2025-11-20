package com.example.fabrick_exercise_carignano.localdto;

import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionInfoDTO {

    private String shopName;

    private String buyerEmail;

    private String issuer;

}
