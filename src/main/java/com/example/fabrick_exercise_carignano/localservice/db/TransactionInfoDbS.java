package com.example.fabrick_exercise_carignano.localservice.db;

import com.example.fabrick_exercise_carignano.repositories.TransactionInfoRepository;
import com.example.fabrick_exercise_carignano.repositories.dao.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionInfoDbS {

    @Autowired
    TransactionInfoRepository transactionInfoRepository;

    public TransactionInfo insertTransactionInfo(TransactionInfo transactionInfo){
        return transactionInfoRepository.saveAndFlush(transactionInfo);
    }

    public TransactionInfo updateTransactionInfo(TransactionInfo transactionInfo){
        return transactionInfoRepository.saveAndFlush(transactionInfo);
    }
}
