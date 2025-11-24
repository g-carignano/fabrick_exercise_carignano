package com.example.fabrick_exercise_carignano.repositories;

import com.example.fabrick_exercise_carignano.repositories.dao.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, BigDecimal> {
    Optional<PaymentMethod> findByCircuitType(String circuitType);
}
