package com.example.fabrick_exercise_carignano.service.moneytransfer;

import com.example.fabrick_exercise_carignano.dto.FabrickException;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.MoneyTransferResponse;

public interface IMoneyTransferService {

    FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferRequest moneyTransferRequest) throws FabrickException;

}
