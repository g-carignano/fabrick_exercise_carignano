package com.example.fabrick_exercise_carignano.repositories.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction {

    private BigDecimal idTransaction;
    private BigDecimal amount;
    private String currency;
    private Date paymentDate;
    private String state;
    private String transactionGuid;
    private PaymentMethod paymentMethod;
    private TransactionInfo transactionInfo;

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

    @Column(name = "AMOUNT")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "CURRENCY", length = 15)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "PAYMENTDATE")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENTMETHODID", nullable = false)
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "transaction")
    @JsonManagedReference
    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "TRANSACTION_UUID")
    public String getTransactionGuid() {
        return transactionGuid;
    }

    public void setTransactionGuid(String transactionGuid) {
        this.transactionGuid = transactionGuid;
    }

    public Transaction() {
    }

    public Transaction(BigDecimal idTransaction, BigDecimal amount, String currency, Date paymentDate, PaymentMethod paymentMethod, TransactionInfo transactionInfo) {
        this.idTransaction = idTransaction;
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.transactionInfo = transactionInfo;
    }
}
