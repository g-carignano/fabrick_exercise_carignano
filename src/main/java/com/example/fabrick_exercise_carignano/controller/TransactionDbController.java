package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionListRequestDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionResponseDTO;
import com.example.fabrick_exercise_carignano.localservice.transaction.LocalTransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("/get-internal-transactions-by-filter")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByFilter(@RequestBody @Valid TransactionListRequestDTO transactionListRequestDTO) {
        List<TransactionDTO> ret = localTransactionService.getTransactionsByFilter(transactionListRequestDTO);
        return ResponseEntity.ok(ret);
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        TransactionResponseDTO createdTransaction = this.localTransactionService.insertTransaction(transactionDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTransaction.getIdTransaction()).toUri();
        return ResponseEntity.created(location).body(createdTransaction);
    }

    @PutMapping("/update-transaction")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        return ResponseEntity.ok(this.localTransactionService.updateTransaction(transactionDTO));
    }
}