package com.example.fabrick_exercise_carignano.localservice.image;

import com.example.fabrick_exercise_carignano.localdto.FileDTO;
import com.example.fabrick_exercise_carignano.localservice.db.FileDbS;
import com.example.fabrick_exercise_carignano.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;

@Service
public class FileService implements IFileService {

    @Autowired
    FileMapper fileMapper;
    @Autowired
    FileDbS fileDbS;

    public Boolean uploadFile(MultipartFile file) throws IOException {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(file.getOriginalFilename());
        fileDTO.setData(Base64.getEncoder().encodeToString(file.getBytes()));
        fileDTO.setFormat(file.getContentType());

        if(!fileDbS.uploadFileToDB(fileMapper.toFile(fileDTO)))
            throw new RuntimeException("Failed to load image!");
        return true;
    }

    public FileDTO getFileById(BigDecimal id) throws RuntimeException {
        return fileMapper.toFileDTO(fileDbS.getFileById(id));
    }
}
