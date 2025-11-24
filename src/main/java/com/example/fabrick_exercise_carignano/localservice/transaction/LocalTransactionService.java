package com.example.fabrick_exercise_carignano.localservice.transaction;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionListRequestDTO;
import com.example.fabrick_exercise_carignano.localservice.db.PaymentMethodDbS;
import com.example.fabrick_exercise_carignano.localservice.db.TransactionDbS;
import com.example.fabrick_exercise_carignano.localservice.db.TransactionInfoDbS;
import com.example.fabrick_exercise_carignano.mapper.LocalTransactionMapper;
import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import com.example.fabrick_exercise_carignano.repositories.dao.TransactionInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalTransactionService implements ILocalTransactionService {

    @Autowired
    TransactionDbS transactionDbS;
    @Autowired
    LocalTransactionMapper localTransactionMapper;
    @Autowired
    PaymentMethodDbS paymentMethodDbS;
    @Autowired
    TransactionInfoDbS transactionInfoDbS;

    public List<TransactionDTO> getAllInternalAccountTransactions(){
        return transactionDbS.getAllInternalAccountTransactions();
    }

    public List<TransactionDTO> getTransactionsByFilter(TransactionListRequestDTO transactionFilterDTO){
        return transactionDbS.getTransactionsByFilter(transactionFilterDTO);
    }

    @Transactional
    public void insertTransaction(TransactionDTO transactionDTO){
        Transaction transaction = localTransactionMapper.toTransaction(transactionDTO);

        transaction.setPaymentMethod(paymentMethodDbS.getPaymentMethodByCircuit(transactionDTO.getPaymentMethod().getCircuitType()));
        transaction.setTransactionGuid(UUID.randomUUID().toString());
        transaction.getTransactionInfo().setTransaction(transaction);
        transaction = transactionDbS.insertTransaction(transaction);
        TransactionInfo transactionInfo = localTransactionMapper.toTransactionInfo(transactionDTO.getTransactionInfo());
        transactionInfo.setIdTransaction(transaction.getIdTransaction());
        transactionInfo.setTransaction(transaction);
        transactionInfo = transactionInfoDbS.insertTransactionInfo(transactionInfo);

    }

}
