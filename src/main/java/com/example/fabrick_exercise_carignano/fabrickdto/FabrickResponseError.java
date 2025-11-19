package com.example.fabrick_exercise_carignano.fabrickdto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FabrickResponseError {
    private String code;
    private String description;
}
