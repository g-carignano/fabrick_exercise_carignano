package com.example.fabrick_exercise_carignano.fabrickservice.transaction;

import com.example.fabrick_exercise_carignano.fabrickdto.FabrickResponse;
import com.example.fabrick_exercise_carignano.fabrickdto.transaction.fabrick.TransactionResponseFabrick;
import com.example.fabrick_exercise_carignano.fabrickdto.transaction.local.Transaction;
import com.example.fabrick_exercise_carignano.mapper.TransactionMapper;
import com.example.fabrick_exercise_carignano.fabrickservice.client.IClientService;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final IClientService clientService;
    @Autowired
    private TransactionMapper transactionMapper;

    public TransactionService(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public List<Transaction> getTransactionList(Long accountId, Date fromDate, Date toDate) {
        List<Transaction> retList = new ArrayList<>();

        FabrickResponse<TransactionResponseFabrick> response = clientService.getAccountTransactionList(accountId, fromDate, toDate);

        if(response == null)
            throw  new InternalException("getTransactionList: Response is NULL!");

        /*for(TransactionFabrick tf : response.getPayload().getList()){
            retList.add(GlobalConverter.mapTransactionFabrickToTransaction(tf));
        }*/

        retList = transactionMapper.toTransactionList(response.getPayload().getList());

        return retList;
    }
}
