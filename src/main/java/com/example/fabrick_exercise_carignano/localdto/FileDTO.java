package com.example.fabrick_exercise_carignano.localdto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class FileDTO {

    private BigDecimal idFile;
    private String fileName;
    private String data;
    private String format;
}
