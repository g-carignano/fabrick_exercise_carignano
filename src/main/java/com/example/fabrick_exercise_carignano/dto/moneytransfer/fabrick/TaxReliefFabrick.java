package com.example.fabrick_exercise_carignano.dto.moneytransfer.fabrick;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxReliefFabrick {

    private String taxReliefId;

    @NotBlank
    private Boolean isCondoUpgrade;

    @NotBlank
    private String creditorFiscalCode;

    @NotBlank
    private String beneficiaryType;

    private NaturalPersonBeneficiaryFabrick naturalPersonBeneficiary;

    private LegalPersonBeneficiaryFabrick legalPersonBeneficiary;

}
