package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    @NotBlank
    private String accountCode;

    private String bicCode;

}
