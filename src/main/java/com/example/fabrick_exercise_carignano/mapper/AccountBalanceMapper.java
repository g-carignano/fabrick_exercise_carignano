package com.example.fabrick_exercise_carignano.mapper;

import com.example.fabrick_exercise_carignano.dto.accountbalance.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountBalanceMapper {

    @Mapping(target = "accountId", source = "accountId")
    @Mapping(source = "balanceResponse.balance", target = "balance", qualifiedByName = "numberToString")
    AccountBalance toAccountBalance(BalanceResponse balanceResponse, Long accountId);

    @Named("numberToString")
    public static String numberToString(Number number) {
        return number == null ? null : number.toString();
    }
}
