package com.example.fabrick_exercise_carignano.exception;

import com.example.fabrick_exercise_carignano.dto.FabrickResponse;
import com.example.fabrick_exercise_carignano.dto.FabrickResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<FabrickResponse<Object>> handleForbiddenException(HttpClientErrorException.Forbidden ex) {

        FabrickResponse<Object> response = new FabrickResponse<>();

        response.setPayload(null);

        response.setError(new ArrayList<>(Arrays.asList(new FabrickResponseError("403", "Invalid account identifier", ""))));

        response.setStatus("KO");

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
