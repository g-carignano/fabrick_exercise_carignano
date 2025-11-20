package com.example.fabrick_exercise_carignano.repositories.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_TRANSACTION_INFO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TransactionInfo {

    private BigDecimal idTransaction;

    private String shopName;

    private String buyerEmail;

    private String issuer;

    private Transaction transaction;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_seq")
    @SequenceGenerator(name = "tran_seq", sequenceName = "TRAN_ID_SEQ", allocationSize = 1)
    @Column(name = "IDTRANSACTION", unique = true, nullable = false)
    public BigDecimal getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(BigDecimal idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Column(name = "SHOPNAME")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Column(name = "BUYEREMAIL")
    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    @Column(name = "ISSUER")
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDTRANSACTION", referencedColumnName = "IDTRANSACTION", insertable = false, updatable = false)
    @JsonBackReference
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionInfo(BigDecimal idTransaction, String shopName, String buyerEmail, String issuer, Transaction transaction) {
        this.idTransaction = idTransaction;
        this.shopName = shopName;
        this.buyerEmail = buyerEmail;
        this.issuer = issuer;
        this.transaction = transaction;
    }

    public TransactionInfo() {
    }
}
