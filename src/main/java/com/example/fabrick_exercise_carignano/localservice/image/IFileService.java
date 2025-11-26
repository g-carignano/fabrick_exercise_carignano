package com.example.fabrick_exercise_carignano.localservice.image;

import com.example.fabrick_exercise_carignano.localdto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

public interface IFileService {
    Boolean uploadFile(MultipartFile  file) throws IOException;
    FileDTO getFileById(BigDecimal id) throws RuntimeException;
}
