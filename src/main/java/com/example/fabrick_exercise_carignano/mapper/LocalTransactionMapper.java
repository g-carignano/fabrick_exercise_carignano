package com.example.fabrick_exercise_carignano.mapper;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionListRequestDTO;
import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import com.example.fabrick_exercise_carignano.repositories.filter.TransactionFilter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocalTransactionMapper {
    TransactionDTO toTransactionDTO(Transaction transaction);
    Transaction toTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> toListTransactionDTO(List<Transaction> transactionList);
    List<Transaction> toListTransaction(List<TransactionDTO> transactionDTOList);

    TransactionFilter toTransactionFilter(TransactionListRequestDTO transactionListRequestDTO);
}
