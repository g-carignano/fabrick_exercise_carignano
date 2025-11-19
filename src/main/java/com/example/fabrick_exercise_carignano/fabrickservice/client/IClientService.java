package com.example.fabrick_exercise_carignano.fabrickservice.client;

import com.example.fabrick_exercise_carignano.fabrickdto.FabrickException;
import com.example.fabrick_exercise_carignano.fabrickdto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.fabrickdto.FabrickResponse;
import com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.fabrick.MoneyTransferFabrickRequest;
import com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local.MoneyTransferResponse;
import com.example.fabrick_exercise_carignano.fabrickdto.transaction.fabrick.TransactionResponseFabrick;

import java.util.Date;

public interface IClientService {
    FabrickResponse<BalanceResponse> getBankAccountBalance(long accountId);
    FabrickResponse<BalanceResponse> getBankAccountBalanceFromMapping(long accountId);
    FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferFabrickRequest moneyTransferFabrickRequest) throws FabrickException;
    FabrickResponse<TransactionResponseFabrick> getAccountTransactionList(long accountId, Date fromAccountingDate, Date toAccountingDate);
}
