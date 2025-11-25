package com.example.fabrick_exercise_carignano.localservice.db;

import com.example.fabrick_exercise_carignano.repositories.PictureRepository;
import com.example.fabrick_exercise_carignano.repositories.dao.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureDbS {

    private static final Logger log = LoggerFactory.getLogger(PictureDbS.class);
    @Autowired
    PictureRepository pictureRepository;

    public Boolean uploadPictureToDB(Picture picture){
        try{
            pictureRepository.save(picture);
        }catch (Exception ex){
            log.error("Error saving the image into the db! Cause: {}", ex.getMessage());
            return false;
        }

        return true;
    }

}
