package com.example.fabrick_exercise_carignano.localservice.db;

import com.example.fabrick_exercise_carignano.localdto.TransactionDTO;
import com.example.fabrick_exercise_carignano.localdto.TransactionListRequestDTO;
import com.example.fabrick_exercise_carignano.mapper.LocalTransactionMapper;
import com.example.fabrick_exercise_carignano.repositories.TransactionHibernateRepository;
import com.example.fabrick_exercise_carignano.repositories.TransactionRepository;
import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import com.example.fabrick_exercise_carignano.repositories.filter.TransactionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDbS {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionHibernateRepository transactionHibernateRepository;
    @Autowired
    LocalTransactionMapper localTransactionMapper;

    public List<TransactionDTO> getAllInternalAccountTransactions(){
        return localTransactionMapper.toListTransactionDTO(transactionRepository.findAll());
    }

    public List<TransactionDTO> getTransactionsByFilter(TransactionListRequestDTO transactionFilterDTO){
        TransactionFilter filter = localTransactionMapper.toTransactionFilter(transactionFilterDTO);
        return localTransactionMapper.toListTransactionDTO(transactionRepository.findAllRepositoriesByFilter(filter.getMinAmount(),filter.getMaxAmount(),filter.getFromDate(),filter.getToDate(),filter.getCircuit()));
    }

    public List<TransactionDTO> getTransactionsByFilterHibernate(TransactionListRequestDTO transactionFilterDTO){
        return localTransactionMapper.toListTransactionDTO(transactionHibernateRepository.findTransactionsByFilter(localTransactionMapper.toTransactionFilter(transactionFilterDTO)));
    }

    public Transaction insertTransaction(Transaction transaction){
        return transactionRepository.saveAndFlush(transaction);
    }

    public Transaction getTransactionByGuid(String guid){
        return transactionRepository.findByTransactionGuid(guid);
    }

    public Transaction updateTransaction(Transaction transaction){
        return transactionRepository.saveAndFlush(transaction);
    }


}
