package com.example.fabrick_exercise_carignano.service.bankaccount;

import com.example.fabrick_exercise_carignano.dto.accountbalance.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;

public interface IAccountService {

    public AccountBalance getAccountBalanceResponse(long accountId);
}
