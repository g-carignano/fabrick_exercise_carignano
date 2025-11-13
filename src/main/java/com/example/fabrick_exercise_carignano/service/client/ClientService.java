package com.example.fabrick_exercise_carignano.service.client;

import com.example.fabrick_exercise_carignano.dto.FabrickException;
import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.converters.FabrickResponseBalanceResponseConverter;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferFabrickRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.MoneyTransferResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.InternalException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    private final ObjectMapper objectMapper;

    public ClientService(RestClient restClient, ObjectMapper objectMapper){
        this.restClient = restClient;
        this.objectMapper = objectMapper;
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
    public FabrickResponse<MoneyTransferResponse> postMoneyTransfer(long accountId, MoneyTransferFabrickRequest moneyTransferFabrickRequest) throws FabrickException{
        log.info("Calling external api: {} with accountId: {}",postMoneyTransfer, accountId);
        String json = null;



        try{
            json = objectMapper.writeValueAsString(moneyTransferFabrickRequest);
            log.info("Serialized moneyTransferFabrickRequest: {}", json);
            JSONObject jsonObject = new JSONObject(json);
            log.info("Serialized moneyTransferFabrickRequest: {}", jsonObject);
        }catch (JsonProcessingException e) {
            // Handle or log gracefully
            log.error("Failed to serialize request body: " + e.getMessage());
        }


        ResponseEntity<FabrickResponse<MoneyTransferResponse>> responseEntity = restClient.post()
                .uri(postMoneyTransfer, accountId)
                .body(json)
                .exchange((clientRequest, clientResponse) -> {
                    HttpStatusCode statusCode = clientResponse.getStatusCode();
                    log.info("Uri: {}", clientRequest.getURI().toURL());
                    log.info("Headers: {}", clientRequest.getHeaders());
                    log.info("Response status: {}", statusCode);
                    FabrickResponse<MoneyTransferResponse> body = null;

                    if(statusCode.value() == HttpStatus.BAD_REQUEST.value()){
                        log.error("Bad Request - Invalid money transfer data");

                        body = clientResponse.bodyTo(new ParameterizedTypeReference<FabrickResponse<MoneyTransferResponse>>(){});

                        log.error("Error body: {}", body);

                        assert body != null;
                    }
                    return ResponseEntity.status(clientResponse.getStatusCode()).body(body);
                });

        if(responseEntity != null && responseEntity.getBody() != null && responseEntity.getBody().getErrors().stream().count() > 0)
            throw new FabrickException(responseEntity.getBody().getStatus(), responseEntity.getBody().getErrors());

        if(responseEntity == null)
            throw new InternalException("postMoneyTransfer: ResponseEntity is NULL!");

        return responseEntity.getBody();
    }
}
