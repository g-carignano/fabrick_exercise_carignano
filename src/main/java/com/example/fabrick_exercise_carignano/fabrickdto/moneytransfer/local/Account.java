package com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    @NotBlank
    private String accountCode;

    private String bicCode;

}
