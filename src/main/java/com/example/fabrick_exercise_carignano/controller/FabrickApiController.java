package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.constants.ProjectConstants;
import com.example.fabrick_exercise_carignano.dto.FabrickException;
import com.example.fabrick_exercise_carignano.dto.accountbalance.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.MoneyTransferResponse;
import com.example.fabrick_exercise_carignano.dto.transaction.local.Transaction;
import com.example.fabrick_exercise_carignano.service.bankaccount.IAccountService;
import com.example.fabrick_exercise_carignano.service.moneytransfer.IMoneyTransferService;
import com.example.fabrick_exercise_carignano.service.transaction.ITransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.fabrick_exercise_carignano.service.client.IClientService;

import java.util.Date;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/account/")
public class FabrickApiController{
    private final IAccountService accountService;
    private final IMoneyTransferService moneyTransferService;
    private final ITransactionService transactionService;

    public FabrickApiController(IAccountService accountService, IMoneyTransferService moneyTransferService, ITransactionService transactionService){
        this.accountService = accountService;
        this.moneyTransferService = moneyTransferService;
        this.transactionService = transactionService;
    }

    //Add Actuators
    @GetMapping("/{accountId}/get-balance")
    public ResponseEntity<AccountBalance> getAccountBalance(@PathVariable @Positive @NotNull Long accountId) {
        AccountBalance accountBalance = this.accountService.getAccountBalanceResponse(accountId);
        return ResponseEntity.ok(accountBalance);
    }

    //Add Actuators
    @PostMapping("/{accountId}/post-money-transfer")
    public ResponseEntity<MoneyTransferResponse> postMoneyTransfer(@PathVariable @Positive @NotNull Long accountId, @RequestBody @Valid MoneyTransferRequest moneyTransferRequest) throws FabrickException{
        FabrickResponse<MoneyTransferResponse> monTranResp = this.moneyTransferService.postMoneyTransfer(accountId, moneyTransferRequest);
        return ResponseEntity.ok(monTranResp.getPayload());

    }

    @GetMapping("/{accountId}/get-transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable @Positive @NotNull Long accountId, @RequestParam @DateTimeFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT) Date fromAccountingDate, @RequestParam @DateTimeFormat(pattern = ProjectConstants.DateFormat.STANDARD_DATE_FORMAT) Date toAccountingDate) {
        List<Transaction> transactionList = this.transactionService.getTransactionList(accountId, fromAccountingDate, toAccountingDate);
        return ResponseEntity.ok(transactionList);
    }

}
