package com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fee {

    private String feeCode;

    private String description;

    private Number amount;

    private String currency;

}
