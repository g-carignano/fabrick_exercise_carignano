package com.example.fabrick_exercise_carignano.service.bankaccount;

import com.example.fabrick_exercise_carignano.dto.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import org.apache.logging.log4j.util.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.fabrick_exercise_carignano.service.client.IClientService;

@Service
public class AccountService implements IAccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final IClientService IClientService;

    public AccountService(IClientService IClientService){
        this.IClientService = IClientService;
    }

    @Override
    public FabrickResponse<BalanceResponse>  getAccountBalanceResponse(long accountId) {
        FabrickResponse<BalanceResponse> response = IClientService.getBankAccountBalance(accountId);

        if(response.getPayload() == null){
            log.error("BankAccountService Error: received payload from getBankAccountBalance to NULL");
            throw new InternalException("BankAccountService Error: received payload from getBankAccountBalance to NULL");
        }

        return response;
    }
}
