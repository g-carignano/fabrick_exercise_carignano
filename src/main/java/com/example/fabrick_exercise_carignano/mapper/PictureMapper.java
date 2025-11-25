package com.example.fabrick_exercise_carignano.mapper;

import com.example.fabrick_exercise_carignano.localdto.PictureDTO;
import com.example.fabrick_exercise_carignano.repositories.dao.Picture;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PictureMapper {
    Picture toPicture(PictureDTO pictureDTO);
    PictureDTO toPictureDTO(Picture picture);
}
