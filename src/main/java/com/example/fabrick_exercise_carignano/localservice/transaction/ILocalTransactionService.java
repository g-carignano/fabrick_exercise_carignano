package com.example.fabrick_exercise_carignano.localservice.transaction;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionListRequestDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionResponseDTO;

import java.util.List;

public interface ILocalTransactionService {
    List<TransactionDTO> getAllInternalAccountTransactions();
    List<TransactionDTO> getTransactionsByFilter(TransactionListRequestDTO transactionFilterDTO);
    TransactionResponseDTO insertTransaction(TransactionDTO transactionDTO);
    TransactionResponseDTO updateTransaction(TransactionDTO transactionDTO);
    Boolean deleteTransaction(String guid);
}
