package com.example.fabrick_exercise_carignano.localservice.transaction;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.mapper.LocalTransactionMapper;
import com.example.fabrick_exercise_carignano.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalTransactionService implements ILocalTransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    LocalTransactionMapper localTransactionMapper;

    public List<TransactionDTO> getAllInternalAccountTransactions(){
        return localTransactionMapper.toListTransactionDTO(transactionRepository.findAll());
    }

}
