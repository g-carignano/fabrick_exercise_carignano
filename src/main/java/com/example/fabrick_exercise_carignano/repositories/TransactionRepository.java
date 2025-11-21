package com.example.fabrick_exercise_carignano.repositories;

import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, BigDecimal> {

    @Query("SELECT t FROM Transaction t")
    List<Transaction> findAllTransactions();

    @Query("""
        SELECT t FROM Transaction t
        JOIN FETCH t.paymentMethod pm
        JOIN FETCH t.transactionInfo ti
        WHERE (:minAmount IS NULL OR t.amount >= :minAmount)
          AND (:maxAmount IS NULL OR t.amount <= :maxAmount)
          AND (:fromDate IS NULL OR t.paymentDate >= :fromDate)
          AND (:toDate IS NULL OR t.paymentDate <= :toDate)
          AND (:circuit IS NULL OR pm.circuitType = :circuit)
    """)
    List<Transaction> findAllRepositoriesByFilter(
            @Param("minAmount") BigDecimal minAmount,
            @Param("maxAmount") BigDecimal maxAmount,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            @Param("circuit") String circuit
    );

}
