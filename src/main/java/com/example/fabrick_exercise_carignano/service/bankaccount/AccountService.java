package com.example.fabrick_exercise_carignano.service.bankaccount;

import com.example.fabrick_exercise_carignano.dto.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;

public interface AccountService {

    public FabrickResponse<BalanceResponse> getAccountBalanceResponse(long accountId);
}
