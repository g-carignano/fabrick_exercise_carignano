package com.example.fabrick_exercise_carignano.service.moneytransfer;

import com.example.fabrick_exercise_carignano.dto.FabrickException;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferResponse;

public interface IMoneyTransferService {

    public FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferRequest moneyTransferRequest) throws FabrickException;

}
