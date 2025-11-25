package com.example.fabrick_exercise_carignano.localservice.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPictureService {
    Boolean uploadImage(MultipartFile  file) throws IOException;
}
