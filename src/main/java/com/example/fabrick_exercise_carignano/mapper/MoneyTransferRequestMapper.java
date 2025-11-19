package com.example.fabrick_exercise_carignano.mapper;

import com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.fabrick.MoneyTransferFabrickRequest;
import com.example.fabrick_exercise_carignano.fabrickdto.moneytransfer.local.MoneyTransferRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyTransferRequestMapper {
    MoneyTransferFabrickRequest toMoneyTransferFabrickRequest(MoneyTransferRequest moneyTransferRequest);
}
