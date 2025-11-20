package com.example.fabrick_exercise_carignano.repositories;

import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, BigDecimal> {

    @Query("SELECT t FROM Transaction t")
    List<Transaction> findAllTransactions();

}
