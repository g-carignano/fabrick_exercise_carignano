package com.example.fabrick_exercise_carignano.localservice.transaction;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionListRequestDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionResponseDTO;
import com.example.fabrick_exercise_carignano.localservice.db.PaymentMethodDbS;
import com.example.fabrick_exercise_carignano.localservice.db.TransactionDbS;
import com.example.fabrick_exercise_carignano.localservice.db.TransactionInfoDbS;
import com.example.fabrick_exercise_carignano.mapper.LocalTransactionMapper;
import com.example.fabrick_exercise_carignano.repositories.dao.PaymentMethod;
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
    public TransactionResponseDTO insertTransaction(TransactionDTO transactionDTO){
        Transaction transaction = localTransactionMapper.toTransaction(transactionDTO);

        transaction.setTransactionGuid(UUID.randomUUID().toString());

        transaction.setPaymentMethod(paymentMethodDbS.getPaymentMethodByCircuit(transactionDTO.getPaymentMethod().getCircuitType()));
        transaction.getTransactionInfo().setTransaction(transaction);
        transaction = transactionDbS.insertTransaction(transaction);
        TransactionInfo transactionInfo = localTransactionMapper.toTransactionInfo(transactionDTO.getTransactionInfo());
        transactionInfo.setIdTransaction(transaction.getIdTransaction());
        transactionInfo.setTransaction(transaction);
        transactionInfo = transactionInfoDbS.insertTransactionInfo(transactionInfo);

        return localTransactionMapper.toTransactionResponseDTO(transaction);
    }

    @Transactional
    public TransactionResponseDTO updateTransaction(TransactionDTO transactionDTO){
        Transaction transactionReceived = localTransactionMapper.toTransaction(transactionDTO);
        Transaction transactionToUpdate = null;
        PaymentMethod substitutePaymentMethod = null;

        try{
            UUID guid = UUID.fromString(transactionReceived.getTransactionGuid());
        }catch (Exception ex){
            throw  new IllegalArgumentException(ex.getMessage());
        }

        substitutePaymentMethod = paymentMethodDbS.getPaymentMethodByCircuit(transactionDTO.getPaymentMethod().getCircuitType());
        transactionToUpdate = transactionDbS.getTransactionByGuid(transactionDTO.getTransactionGuid());
        if(transactionToUpdate == null){
            throw new RuntimeException("Transaction Not Found!", new Throwable("Transaction Not Found!"));
        }
        this.setTransactionProperties(transactionToUpdate, transactionReceived);

        transactionToUpdate.setPaymentMethod(substitutePaymentMethod);
        transactionToUpdate = transactionDbS.updateTransaction(transactionToUpdate);
        TransactionInfo transactionInfo = transactionToUpdate.getTransactionInfo();
        transactionInfo = transactionInfoDbS.updateTransactionInfo(transactionInfo);

        return localTransactionMapper.toTransactionResponseDTO(transactionToUpdate);
    }

    private void setTransactionProperties(Transaction t1, Transaction t2){
        TransactionInfo transactionInfoToUpdate = t1.getTransactionInfo();
        TransactionInfo transactionInfoReceived = t2.getTransactionInfo();

        t1.setAmount(t2.getAmount());
        t1.setCurrency(t2.getCurrency());
        t1.setPaymentMethod(t2.getPaymentMethod());
        t1.setPaymentDate(t2.getPaymentDate());
        t1.setState(t2.getState());
        setTransactionInfoProperties(transactionInfoToUpdate, transactionInfoReceived);
    }

    private void setTransactionInfoProperties(TransactionInfo ti1, TransactionInfo ti2){
        ti1.setShopName(ti2.getShopName());
        ti1.setBuyerEmail(ti2.getBuyerEmail());
        ti1.setIssuer(ti2.getIssuer());
    }

    @Transactional
    public Boolean deleteTransaction(String guid){
        Transaction transaction = transactionDbS.getTransactionByGuid(guid);
        Boolean ret;

        if(transaction == null)
            return false;

        ret = transactionDbS.deleteTransaction(transaction);
        return ret;

    }

}
