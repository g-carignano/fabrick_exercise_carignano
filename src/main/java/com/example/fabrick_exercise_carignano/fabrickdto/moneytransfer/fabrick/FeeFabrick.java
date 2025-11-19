package com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.fabrick;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeeFabrick {

    private String feeCode;

    private String description;

    private Number amount;

    private String currency;

}
