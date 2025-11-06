package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.dto.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.fabrick_exercise_carignano.service.client.IClientService;

@RestController
@RequestMapping("/api/account/")
public class FabrickApiController{
    private final IClientService clientService;

    public FabrickApiController(IClientService clientService){
        this.clientService = clientService;
    }
    //Add Actuators
    @GetMapping("/{accountId}/get-balance")
    public ResponseEntity<AccountBalance> getAccountBalance(@PathVariable Long accountId) {
        FabrickResponse<BalanceResponse> accBalResponse = this.clientService.getBankAccountBalance(accountId);
        return ResponseEntity.ok(new AccountBalance(accBalResponse.getPayload().getBalance().toString(), accountId));
    }
}
