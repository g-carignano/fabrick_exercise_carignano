package com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LegalPersonBeneficiary {

    @NotBlank
    private String fiscalCode;

    private String legalRepresentativeFiscalCode;

}
