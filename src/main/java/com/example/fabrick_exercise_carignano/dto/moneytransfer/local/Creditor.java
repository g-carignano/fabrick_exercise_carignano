package com.example.fabrick_exercise_carignano.dto.moneytransfer.local;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Creditor {

    public Creditor(@NotNull String  name, @NotNull Account account, Address address) {
        this.name = name;
        this.account = account;
        this.address = address;
    }

    @NotBlank
    @Size(max = 50)
    private String name;

    @Valid
    private Account account;

    private Address address;

}
