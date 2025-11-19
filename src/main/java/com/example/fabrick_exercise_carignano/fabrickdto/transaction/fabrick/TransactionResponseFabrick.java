package com.example.fabrick_exercise_carignano.fabrickdto.transaction.fabrick;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TransactionResponseFabrick {
    List<TransactionFabrick> list;
}
