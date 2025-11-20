package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localservice.transaction.LocalTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("api/internal")
public class TransactionDbController {

    private final LocalTransactionService localTransactionService;

    public TransactionDbController(LocalTransactionService localTransactionService) {
        this.localTransactionService = localTransactionService;
    }

    @GetMapping("/get-internal-transactions")
    public ResponseEntity<List<TransactionDTO>> getInternalAccountTransactions() {
        List<TransactionDTO> ret = localTransactionService.getAllInternalAccountTransactions();
        return ResponseEntity.ok(ret);
    }
}