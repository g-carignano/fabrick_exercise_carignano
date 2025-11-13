package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Debtor {

    private String name;

    private Account account;

}
