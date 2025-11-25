package com.example.fabrick_exercise_carignano.repositories;

import com.example.fabrick_exercise_carignano.repositories.dao.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface FileRepository extends JpaRepository<File, BigDecimal> {
}
