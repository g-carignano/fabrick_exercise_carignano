package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.localservice.image.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileDbController {

    @Autowired
    IFileService fileService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
        try {
            fileService.uploadFile(file);

            return ResponseEntity.ok("File uploaded");
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Error uploading file");
        }

    }
}
