package com.example.fabrick_exercise_carignano.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FabrickResponseError {
    private String code;
    private String description;
    private String params;
}
