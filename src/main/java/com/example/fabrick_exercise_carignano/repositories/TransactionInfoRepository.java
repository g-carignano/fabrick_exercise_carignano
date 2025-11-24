package com.example.fabrick_exercise_carignano.repositories;

import com.example.fabrick_exercise_carignano.repositories.dao.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, BigDecimal> {
}
