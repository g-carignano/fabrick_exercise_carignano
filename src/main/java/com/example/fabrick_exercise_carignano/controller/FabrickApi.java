package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.dto.AccountBalance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface FabrickApi {
    @GetMapping("/balance")
    ResponseEntity<AccountBalance> getAccountBalance(@PathVariable Long accountId);
}
