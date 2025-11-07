package com.example.fabrick_exercise_carignano.service.client;

import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferResponse;

public interface IClientService {
    public FabrickResponse<BalanceResponse> getBankAccountBalance(long accountId);
    public FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferRequest moneyTransferRequest);
}
