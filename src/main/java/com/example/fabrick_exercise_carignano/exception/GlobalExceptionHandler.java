package com.example.fabrick_exercise_carignano.exception;

import com.example.fabrick_exercise_carignano.fabrickdto.FabrickException;
import com.example.fabrick_exercise_carignano.fabrickdto.FabrickResponse;
import com.example.fabrick_exercise_carignano.fabrickdto.FabrickResponseError;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

        response.setErrors(new ArrayList<>(Arrays.asList(new FabrickResponseError("403", "Invalid account identifier"))));

        response.setStatus("KO");

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FabrickResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(new FabrickResponseError(ex.getStatusCode().toString(), "Validation Failed! Check API Contracts!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<FabrickResponseError> handleInternalException(InternalException ex){
        return new ResponseEntity<>(new FabrickResponseError("500", "Internal server error!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FabrickException.class)
    public ResponseEntity<FabrickResponseError> handleFabrickException(FabrickException fe){
        return new ResponseEntity<>(new FabrickResponseError(fe.getStatus(), fe.getErrorList().getFirst().getDescription()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<FabrickResponseError> handleIllegalArgumentException(IllegalArgumentException ie){
        return new ResponseEntity<>(new FabrickResponseError("400", "Json arguments are not valid!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<FabrickResponseError> handleRuntimeException(RuntimeException re){
        if(re.getMessage().equals("Transaction Not Found!"))
            return new ResponseEntity<>(new FabrickResponseError("404", "Transaction Not Found!"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new FabrickResponseError("500", "Internal Server Error (To Manage)"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
