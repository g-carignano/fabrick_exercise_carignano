package com.example.fabrick_exercise_carignano.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountBalance {

    private Long accountId;
    private String balance;

    public AccountBalance(String balance, Long accountId) {
        this.balance = balance;
        this.accountId = accountId;
    }
}
