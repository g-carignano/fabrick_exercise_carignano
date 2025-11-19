package com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Debtor {

    private String name;

    private Account account;

}
