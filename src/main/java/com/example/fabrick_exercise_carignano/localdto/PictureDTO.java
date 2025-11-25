package com.example.fabrick_exercise_carignano.localdto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PictureDTO {

    private BigDecimal idPicture;
    private String fileName;
    private byte[] data;
}
