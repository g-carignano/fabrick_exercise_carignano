package com.example.fabrick_exercise_carignano.localdto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionResponseDTO extends TransactionDTO{
    private BigDecimal idTransaction;
}
