package com.example.fabrick_exercise_carignano.localservice.db;

import com.example.fabrick_exercise_carignano.repositories.FileRepository;
import com.example.fabrick_exercise_carignano.repositories.dao.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDbS {

    private static final Logger log = LoggerFactory.getLogger(FileDbS.class);
    @Autowired
    FileRepository fileRepository;

    public Boolean uploadFileToDB(File file){
        try{
            fileRepository.save(file);
        }catch (Exception ex){
            log.error("Error saving the image into the db! Cause: {}", ex.getMessage());
            return false;
        }

        return true;
    }

}
