package com.example.fabrick_exercise_carignano.config;

import com.example.fabrick_exercise_carignano.fabrickservice.client.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.fabrick_exercise_carignano.repositories")
public class FabrickConfig {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Bean
    public RestClient restClient(@Value("${fabrick.base-url}") String baseUrl, @Value("${fabrick.auth-schema}") String authSchema, @Value("${fabrick.api-key}") String apiKey){
        return RestClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .defaultHeader("Api-Key", apiKey)
                .defaultHeader("Auth-Schema", authSchema)
                .requestInterceptor((request, body, execution) -> {
                    log.info("Serialized body: " + new String(body, StandardCharsets.UTF_8));
                    return execution.execute(request, body);
                })
                .build();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
