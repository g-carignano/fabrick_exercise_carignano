package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.localservice.image.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class PictureDbController {

    @Autowired
    IPictureService pictureService;

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
        try {
            pictureService.uploadImage(file);

            return ResponseEntity.ok("Image uploaded");
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Error uploading image");
        }

    }
}
