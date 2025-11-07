package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.dto.FabrickException;
import com.example.fabrick_exercise_carignano.dto.accountbalance.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferResponse;
import com.example.fabrick_exercise_carignano.service.bankaccount.AccountService;
import com.example.fabrick_exercise_carignano.service.bankaccount.IAccountService;
import com.example.fabrick_exercise_carignano.service.moneytransfer.IMoneyTransferService;
import com.example.fabrick_exercise_carignano.service.moneytransfer.MoneyTransferService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.fabrick_exercise_carignano.service.client.IClientService;

@RestController
@Validated
@RequestMapping("/api/account/")
public class FabrickApiController{
    private final IClientService clientService;
    private final IAccountService accountService;
    private final IMoneyTransferService moneyTransferService;

    public FabrickApiController(IClientService clientService, AccountService accountService, MoneyTransferService moneyTransferService){
        this.clientService = clientService;
        this.accountService = accountService;
        this.moneyTransferService = moneyTransferService;
    }
    //Add Actuators
    @GetMapping("/{accountId}/get-balance")
    public ResponseEntity<AccountBalance> getAccountBalance(@PathVariable @Positive @NotNull Long accountId) {
        AccountBalance accountBalance = this.accountService.getAccountBalanceResponse(accountId);
        return ResponseEntity.ok(accountBalance);
    }

    //Add Actuators
    @PostMapping("/{accountId}/post-money-transfer")
    public ResponseEntity<MoneyTransferResponse> postMoneyTransfer(@PathVariable @Positive @NotNull Long accountId, @RequestBody MoneyTransferRequest moneyTransferRequest) {

        try{
            FabrickResponse<MoneyTransferResponse> monTranResp = this.moneyTransferService.postMoneyTransfer(accountId, moneyTransferRequest);
            return ResponseEntity.ok(monTranResp.getPayload());

        }catch (FabrickException fe){
            return ResponseEntity.status(400).body(new MoneyTransferResponse());
        }

    }
}
