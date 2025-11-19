package com.example.fabrick_exercise_carignano.service.moneytransfer;

import com.example.fabrick_exercise_carignano.fabrickdto.FabrickException;
import com.example.fabrick_exercise_carignano.fabrickdto.FabrickResponse;
import com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local.MoneyTransferResponse;

public interface IMoneyTransferService {

    FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferRequest moneyTransferRequest) throws FabrickException;

}
