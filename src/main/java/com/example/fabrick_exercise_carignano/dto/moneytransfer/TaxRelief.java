package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaxRelief {

    private String taxReliefId;

    @NotBlank
    private boolean isCondoUpgrade;

    @JsonProperty("isCondoUpgrade")
    public boolean isCondoUpgrade() { return isCondoUpgrade; }

    @JsonProperty("isCondoUpgrade")
    public void setCondoUpgrade(boolean isCondoUpgrade) { this.isCondoUpgrade = isCondoUpgrade; }

    @NotBlank
    private String creditorFiscalCode;

    @NotBlank
    private String beneficiaryType;

    private NaturalPersonBeneficiary naturalPersonBeneficiary;

    private LegalPersonBeneficiary legalPersonBeneficiary;

}
