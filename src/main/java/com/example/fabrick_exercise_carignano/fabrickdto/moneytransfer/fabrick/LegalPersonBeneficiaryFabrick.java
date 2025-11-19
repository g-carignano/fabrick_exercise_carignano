package com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.fabrick;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LegalPersonBeneficiaryFabrick {

    @NotBlank
    private String fiscalCode;

    private String legalRepresentativeFiscalCode;

}
