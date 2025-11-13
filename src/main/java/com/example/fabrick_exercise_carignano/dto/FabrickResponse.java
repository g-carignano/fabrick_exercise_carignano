package com.example.fabrick_exercise_carignano.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FabrickResponse<T> {

    private String status;
    private List<FabrickResponseError> errors;
    private T payload;

    public  FabrickResponse(String status, List<FabrickResponseError> errors, T payload){
        this.status = status;
        this.errors = errors;
        this.payload = payload;
    }
}
