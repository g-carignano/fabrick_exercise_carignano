package com.example.fabrick_exercise_carignano.mapper;

import com.example.fabrick_exercise_carignano.fabrickdto.transaction.fabrick.TransactionFabrick;
import com.example.fabrick_exercise_carignano.fabrickdto.transaction.local.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionFabrick toTransactionFabrick(Transaction transaction);
    Transaction toTransaction(TransactionFabrick transactionFabrick);
    List<Transaction> toTransactionList(List<TransactionFabrick> transactionFabrickList);
    List<TransactionFabrick> toTransactionFabrickList(List<Transaction> transactionList);
}
