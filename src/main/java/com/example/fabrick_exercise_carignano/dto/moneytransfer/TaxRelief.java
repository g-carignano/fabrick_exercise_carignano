package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxRelief {

    private String taxReliefId;

    @NotBlank
    private Boolean isCondoUpgrade;

    @NotBlank
    private String creditorFiscalCode;

    @NotBlank
    private String beneficiaryType;

    private NaturalPersonBeneficiary naturalPersonBeneficiary;

    private LegalPersonBeneficiary legalPersonBeneficiary;

}
