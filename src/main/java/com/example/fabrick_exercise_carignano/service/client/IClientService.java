package com.example.fabrick_exercise_carignano.service.client;

import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;

public interface IClientService {
    public FabrickResponse<BalanceResponse> getBankAccountBalance(long accountId);
}
