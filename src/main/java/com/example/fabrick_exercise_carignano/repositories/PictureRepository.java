package com.example.fabrick_exercise_carignano.repositories;

import com.example.fabrick_exercise_carignano.repositories.dao.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface PictureRepository extends JpaRepository<Picture, BigDecimal> {
}
