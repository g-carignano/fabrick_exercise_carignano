package com.example.fabrick_exercise_carignano.localservice.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    Boolean uploadFile(MultipartFile  file) throws IOException;
}
