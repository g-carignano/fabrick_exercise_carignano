package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Fee {

    private String feeCode;

    private String description;

    private Number amount;

    private String currency;

}
