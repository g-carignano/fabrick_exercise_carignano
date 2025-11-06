package com.example.fabrick_exercise_carignano.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class FabrickConfig {

    @Bean
    public RestClient restClient(@Value("${fabrick.base-url}") String baseUrl, @Value("${fabrick.auth-schema}") String authSchema, @Value("${fabrick.api-key}") String apiKey){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Api-Key", apiKey)
                .defaultHeader("Auth-Schema", authSchema)
                .build();
    }
}
