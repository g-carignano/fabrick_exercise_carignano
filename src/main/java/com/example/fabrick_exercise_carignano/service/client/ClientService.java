package com.example.fabrick_exercise_carignano.service.client;

import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.converters.FabrickResponseBalanceResponseConverter;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ClientService implements IClientService {

    @Value("${external.client.getbalance}")
    private String getBalanceUrl;

    @Value("${external.client.postmoneytransfer}")
    private String postMoneyTransfer;

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final RestClient restClient;

    public ClientService(RestClient restClient){
        this.restClient = restClient;
    }

    @Override
    public FabrickResponse<BalanceResponse> getBankAccountBalance(long accountId) {
        log.info("Calling external api: {} with accountId: {}",getBalanceUrl, accountId);

        return restClient.get()
                .uri(getBalanceUrl, accountId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<FabrickResponse<BalanceResponse>>() {})
                .getBody();
    }

    @Override
    public FabrickResponse<BalanceResponse> getBankAccountBalanceFromMapping(long accountId) {
        log.info("Calling external api with manual mapping: {} with accountId: {}",getBalanceUrl, accountId);

        String result = restClient.get().uri(getBalanceUrl,accountId).retrieve().body(String.class);

        log.info(result);

        try{

            JSONObject jsonObject = new JSONObject(result);
            return FabrickResponseBalanceResponseConverter.ConvertFromJSon(jsonObject);


        }catch (Exception ex){
            log.error(ex.getMessage());
            throw ex;
        }

    }

    @Override
    public FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferRequest moneyTransferRequest) {
        log.info("Calling external api: {} with accountId: {}",postMoneyTransfer, accountId);

        return restClient.post()
                .uri(postMoneyTransfer, accountId)
                .body(moneyTransferRequest)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<FabrickResponse<MoneyTransferResponse>>() {})
                .getBody();
    }
}
