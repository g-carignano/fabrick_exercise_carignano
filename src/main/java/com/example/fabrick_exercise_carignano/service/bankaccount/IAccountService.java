package com.example.fabrick_exercise_carignano.service.bankaccount;

import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;

public interface IAccountService {

    public FabrickResponse<BalanceResponse> getAccountBalanceResponse(long accountId);
}
