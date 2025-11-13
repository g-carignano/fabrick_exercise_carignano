package com.example.fabrick_exercise_carignano.dto.moneytransfer.fabrick;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DebtorFabrick {

    private String name;

    private AccountFabrick account;

}
