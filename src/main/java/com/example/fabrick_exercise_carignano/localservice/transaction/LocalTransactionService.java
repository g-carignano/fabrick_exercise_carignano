package com.example.fabrick_exercise_carignano.localservice.transaction;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionListRequestDTO;
import com.example.fabrick_exercise_carignano.localservice.db.TransactionDbS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalTransactionService implements ILocalTransactionService {

    @Autowired
    TransactionDbS transactionDbS;

    public List<TransactionDTO> getAllInternalAccountTransactions(){
        return transactionDbS.getAllInternalAccountTransactions();
    }

    public List<TransactionDTO> getTransactionsByFilter(TransactionListRequestDTO transactionFilterDTO){
        return transactionDbS.getTransactionsByFilter(transactionFilterDTO);
    }

}
