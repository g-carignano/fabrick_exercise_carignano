package com.example.fabrick_exercise_carignano.mapper;

import com.example.fabrick_exercise_carignano.localdto.FileDTO;
import com.example.fabrick_exercise_carignano.repositories.dao.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toFile(FileDTO fileDTO);
    FileDTO toFileDTO(File file);
}
