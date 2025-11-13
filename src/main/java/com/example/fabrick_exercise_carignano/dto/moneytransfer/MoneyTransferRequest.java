package com.example.fabrick_exercise_carignano.dto.moneytransfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MoneyTransferRequest {

    public MoneyTransferRequest(Creditor creditor, LocalDate executionDate, String uri, String description, @NotNull Double amount, String currency, Boolean isUrgent, Boolean isInstant, String feeType, String feeAccountId, TaxRelief taxRelief) {
        this.creditor = creditor;
        this.executionDate = executionDate;
        this.uri = uri;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.isUrgent = isUrgent;
        this.isInstant = isInstant;
        this.feeType = feeType;
        this.feeAccountId = feeAccountId;
        this.taxRelief = taxRelief;
    }

    @Valid
    private Creditor creditor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate executionDate;

    private String uri;

    @NotBlank
    @Size(min = 1, message = "{validation.description.size.too_short}")
    @Size(max = 140, message = "{validation.description.size.too_long}")
    private String description;

    @NotNull
    @Min(0)
    private Double amount;

    private String currency;

    private Boolean isUrgent;

    private Boolean isInstant;

    private String feeType;

    private String feeAccountId;

    private TaxRelief taxRelief;

}
