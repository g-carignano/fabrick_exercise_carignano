package com.example.fabrick_exercise_carignano.service.moneytransfer;

import com.example.fabrick_exercise_carignano.dto.FabrickException;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponseError;
import com.example.fabrick_exercise_carignano.dto.converters.GlobalConverter;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.MoneyTransferResponse;
import com.example.fabrick_exercise_carignano.mapper.MoneyTransferRequestMapper;
import com.example.fabrick_exercise_carignano.service.client.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoneyTransferService implements IMoneyTransferService{

    private static final Logger log = LoggerFactory.getLogger(MoneyTransferService.class);
    private final IClientService clientService;
    @Autowired
    private MoneyTransferRequestMapper moneyTransferRequestMapper;

    public MoneyTransferService(IClientService clientService){
        this.clientService = clientService;
    }

    @Override
    public FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferRequest moneyTransferRequest)  throws FabrickException{
        FabrickResponse<MoneyTransferResponse> response;
        try{
            //response = clientService.postMoneyTransfer(accountId, GlobalConverter.mapMoneyTransferRequestToMoneyTransferFabrickRequest(moneyTransferRequest));
            response = clientService.postMoneyTransfer(accountId, moneyTransferRequestMapper.toMoneyTransferFabrickRequest(moneyTransferRequest));
        }catch (FabrickException fe){
            List<FabrickResponseError> errorList = new ArrayList<>();
            log.error("Fabrick Exception thrown errors: {}", fe.getErrorList());
            response = new FabrickResponse<>();
            response.setStatus(fe.getStatus());
            if(fe.getErrorList().getFirst().getCode().equals("API000")){
                errorList.add(new FabrickResponseError(fe.getErrorList().getFirst().getCode(),"Errore tecnico  La condizione BP049 non e' prevista per il conto id " + accountId));
                fe.setStatus(fe.getErrorList().getFirst().getCode());
                fe.setErrorList(errorList);
            }
            throw fe;
        }

        log.debug("Transfer response: {}", response);

        return response;
    }
}
