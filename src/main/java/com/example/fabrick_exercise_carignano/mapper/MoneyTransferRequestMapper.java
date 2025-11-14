package com.example.fabrick_exercise_carignano.mapper;

import com.example.fabrick_exercise_carignano.dto.moneytransfer.fabrick.MoneyTransferFabrickRequest;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.MoneyTransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MoneyTransferRequestMapper {
    MoneyTransferFabrickRequest toMoneyTransferFabrickRequest(MoneyTransferRequest moneyTransferRequest);
}
