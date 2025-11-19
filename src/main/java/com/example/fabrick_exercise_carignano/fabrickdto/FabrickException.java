package com.example.fabrick_exercise_carignano.fabrickdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FabrickException extends Exception{

    private String status;
    private List<FabrickResponseError> errorList;

    public FabrickException(String status, List<FabrickResponseError> errorList){
        this.status = status;
        this.errorList = errorList;
    }

}
