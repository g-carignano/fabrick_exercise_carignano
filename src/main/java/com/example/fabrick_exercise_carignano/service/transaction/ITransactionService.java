package com.example.fabrick_exercise_carignano.service.transaction;

import com.example.fabrick_exercise_carignano.fabrickdto.transaction.local.Transaction;

import java.util.Date;
import java.util.List;

public interface ITransactionService {
    List<Transaction> getTransactionList(Long accountId, Date fromDate, Date toDate);
}
