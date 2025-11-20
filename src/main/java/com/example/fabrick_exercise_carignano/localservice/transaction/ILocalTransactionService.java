package com.example.fabrick_exercise_carignano.localservice.transaction;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;

import java.util.List;

public interface ILocalTransactionService {
    List<TransactionDTO> getAllInternalAccountTransactions();
}
