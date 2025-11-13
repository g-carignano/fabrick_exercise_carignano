package com.example.fabrick_exercise_carignano.service.transaction;

import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.converters.GlobalConverter;
import com.example.fabrick_exercise_carignano.dto.transaction.fabrick.TransactionFabrick;
import com.example.fabrick_exercise_carignano.dto.transaction.fabrick.TransactionResponseFabrick;
import com.example.fabrick_exercise_carignano.dto.transaction.local.Transaction;
import com.example.fabrick_exercise_carignano.service.client.IClientService;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final IClientService clientService;

    public TransactionService(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public List<Transaction> getTransactionList(Long accountId, Date fromDate, Date toDate) {
        List<Transaction> retList = new ArrayList<>();

        FabrickResponse<TransactionResponseFabrick> response = clientService.getAccountTransactionList(accountId, fromDate, toDate);

        if(response == null)
            throw  new InternalException("getTransactionList: Response is NULL!");

        for(TransactionFabrick tf : response.getPayload().getList()){
            retList.add(GlobalConverter.mapTransactionFabrickToTransaction(tf));
        }

        return retList;
    }
}
