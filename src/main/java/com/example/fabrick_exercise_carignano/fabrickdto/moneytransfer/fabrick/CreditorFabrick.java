package com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.fabrick;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditorFabrick {

    public CreditorFabrick(@NotNull String  name, @NotNull AccountFabrick account, AddressFabrick address) {
        this.name = name;
        this.account = account;
        this.address = address;
    }

    @NotBlank
    @Size(max = 50)
    private String name;

    @Valid
    private AccountFabrick account;

    private AddressFabrick address;

}
