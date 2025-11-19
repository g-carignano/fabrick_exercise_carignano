package com.example.fabrick_exercise_carignano.service.bankaccount;

import com.example.fabrick_exercise_carignano.fabrickdto.accountbalance.AccountBalance;

public interface IAccountService {

    public AccountBalance getAccountBalanceResponse(long accountId);
}
