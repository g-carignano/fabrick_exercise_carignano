package com.example.fabrick_exercise_carignano.controller;

import com.example.fabrick_exercise_carignano.localdto.FileDTO;
import com.example.fabrick_exercise_carignano.localservice.image.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Base64;

@RestController
@RequestMapping("/api/files")
public class FileDbController {

    @Autowired
    IFileService fileService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            fileService.uploadFile(file);

            return ResponseEntity.ok("File uploaded");
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Error uploading file");
        }
    }

    @GetMapping("/get-file")
    public ResponseEntity<byte[]> getFile(@RequestParam("id")BigDecimal id){
        FileDTO file = fileService.getFileById(id);
        byte[] bytes = Base64.getDecoder().decode(file.getData());

        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + file.getFileName() + "\"")
                .header("Content-Type", "application/octet-stream").body(bytes);
    }
}
