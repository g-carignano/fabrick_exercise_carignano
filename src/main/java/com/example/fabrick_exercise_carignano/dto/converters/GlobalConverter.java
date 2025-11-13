package com.example.fabrick_exercise_carignano.dto.converters;

import com.example.fabrick_exercise_carignano.dto.accountbalance.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferFabrickRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferRequest;

public class GlobalConverter {
    public static MoneyTransferFabrickRequest mapMoneyTransferFabrickToMoneyTransferFabrickRequest(MoneyTransferRequest moneyTransferRequest){
        MoneyTransferFabrickRequest ret = new MoneyTransferFabrickRequest();

        ret.setCreditor(moneyTransferRequest.getCreditor());
        ret.setUri(moneyTransferRequest.getUri());
        ret.setIsUrgent(moneyTransferRequest.getIsUrgent());
        ret.setIsInstant(moneyTransferRequest.getIsInstant());
        ret.setCurrency(moneyTransferRequest.getCurrency());
        ret.setFeeType(moneyTransferRequest.getFeeType());
        ret.setFeeAccountId(moneyTransferRequest.getFeeAccountId());
        ret.setExecutionDate(moneyTransferRequest.getExecutionDate());
        ret.setDescription(moneyTransferRequest.getDescription());
        ret.setTaxRelief(moneyTransferRequest.getTaxRelief());
        ret.setAmount(Math.round(moneyTransferRequest.getAmount() * 100));

        return ret;
    }

    public static AccountBalance mapBalanceResponseIntoAccountBalance(BalanceResponse balanceResponse, long accountId){
        AccountBalance ret = new AccountBalance();

        ret.setAccountId(accountId);
        ret.setBalance(balanceResponse.getBalance() != null ? balanceResponse.getBalance().toString() : null);

        return ret;
    }
}
