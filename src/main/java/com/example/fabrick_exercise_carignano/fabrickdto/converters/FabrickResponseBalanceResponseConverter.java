package com.example.fabrick_exercise_carignano.fabrickdto.converters;

import com.example.fabrick_exercise_carignano.fabrickdto.FabrickResponse;
import com.example.fabrick_exercise_carignano.fabrickdto.FabrickResponseError;
import com.example.fabrick_exercise_carignano.fabrickdto.accountbalance.BalanceResponse;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class FabrickResponseBalanceResponseConverter {

    public static FabrickResponse<BalanceResponse> ConvertFromJSon (JSONObject jsonObject){

        FabrickResponse<BalanceResponse> ret = new FabrickResponse<>();
        JSONObject jsonObjectPayload = jsonObject.getJSONObject("payload");
        ArrayList<FabrickResponseError> responseErrors = new ArrayList<>();

        BalanceResponse balanceResponse = new BalanceResponse();

        ret.setStatus(jsonObject.getString("status"));
        ret.setErrors(responseErrors);

        balanceResponse.setDate(LocalDate.parse(jsonObjectPayload.getString("date")));
        balanceResponse.setBalance(jsonObjectPayload.getNumber("balance"));
        balanceResponse.setAvailableBalance(jsonObjectPayload.getNumber("availableBalance"));
        balanceResponse.setCurrency(jsonObjectPayload.getString("currency"));

        ret.setPayload(balanceResponse);

        return ret;
    }

}
