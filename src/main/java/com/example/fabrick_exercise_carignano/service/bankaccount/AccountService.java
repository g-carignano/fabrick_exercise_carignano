package com.example.fabrick_exercise_carignano.service.bankaccount;

import com.example.fabrick_exercise_carignano.dto.accountbalance.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import org.apache.logging.log4j.util.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.fabrick_exercise_carignano.service.client.IClientService;

@Service
public class AccountService implements IAccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final IClientService clientService;

    public AccountService(IClientService clientService){
        this.clientService = clientService;
    }

    @Override
    public AccountBalance getAccountBalanceResponse(long accountId) {
        FabrickResponse<BalanceResponse> response = clientService.getBankAccountBalanceFromMapping(accountId);

        if(response.getPayload() == null){
            log.error("BankAccountService Error: received payload from getBankAccountBalance to NULL");
            throw new InternalException("BankAccountService Error: received payload from getBankAccountBalance to NULL");
        }

        return mapBalanceResponseIntoAccountBalance(response.getPayload(), accountId);
    }

    private AccountBalance mapBalanceResponseIntoAccountBalance(BalanceResponse balanceResponse, long accountId){
        AccountBalance ret = new AccountBalance();

        ret.setAccountId(accountId);
        ret.setBalance(balanceResponse.getBalance().toString());

        return ret;
    }
}
