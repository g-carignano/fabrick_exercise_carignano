package com.example.fabrick_exercise_carignano.localservice.image;

import com.example.fabrick_exercise_carignano.localdto.PictureDTO;
import com.example.fabrick_exercise_carignano.localservice.db.PictureDbS;
import com.example.fabrick_exercise_carignano.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureService implements IPictureService{

    @Autowired
    PictureMapper pictureMapper;
    @Autowired
    PictureDbS pictureDbS;

    public Boolean uploadImage(MultipartFile file) throws IOException {
        PictureDTO picture = new PictureDTO();
        picture.setFileName(file.getOriginalFilename());
        picture.setData(file.getBytes());

        if(!pictureDbS.uploadPictureToDB(pictureMapper.toPicture(picture)))
            throw new RuntimeException("Failed to load image!");
        return true;
    }
}
