package com.example.fabrick_exercise_carignano.repositories.csv;

import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionCsv {
    private BigDecimal idTransaction;
    private BigDecimal amount;
    private String currency;
    private String state;
    private Date paymentDate;
    private String transactionGuid;
    private String circuitType;
    private String shopName;
    private String buyerEmail;
    private String issuer;

    public TransactionCsv(Transaction t) {
        this.idTransaction = t.getIdTransaction();
        this.amount = t.getAmount();
        this.currency = t.getCurrency();
        this.state = t.getState();
        this.paymentDate = t.getPaymentDate();
        this.transactionGuid = t.getTransactionGuid();

        this.circuitType = t.getPaymentMethod() != null ? t.getPaymentMethod().getCircuitType() : "";

        this.shopName = t.getTransactionInfo() != null ? t.getTransactionInfo().getShopName() : "";
        this.buyerEmail = t.getTransactionInfo() != null ? t.getTransactionInfo().getBuyerEmail() : "";
        this.issuer = t.getTransactionInfo() != null ? t.getTransactionInfo().getIssuer() : "";
    }
}
