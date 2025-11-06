package com.example.fabrick_exercise_carignano.service.client;

import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
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
}
