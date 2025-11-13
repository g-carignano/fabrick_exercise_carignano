package com.example.fabrick_exercise_carignano.dto.converters;

import com.example.fabrick_exercise_carignano.dto.moneytransfer.*;
import org.apache.logging.log4j.util.InternalException;
import org.json.JSONObject;

public class PostMoneyRequestConverters {

    public static JSONObject convertRequestToJSON(MoneyTransferFabrickRequest moneyTransferRequest){
        JSONObject ret = moneyTransferRequest != null ? new JSONObject() : null;

        ret.put("creditor", convertCreditorToJSON(moneyTransferRequest.getCreditor()));
        ret.put("executionDate", moneyTransferRequest.getExecutionDate() != null ? moneyTransferRequest.getExecutionDate() : JSONObject.NULL);
        ret.put("uri", moneyTransferRequest.getUri() != null ? moneyTransferRequest.getUri() : JSONObject.NULL);

        if(moneyTransferRequest.getDescription() == null)
            throw new InternalException("Description can't be null!");

        ret.put("description", moneyTransferRequest.getDescription());

        if(moneyTransferRequest.getAmount() == null)
            throw new InternalException("Amount can't be null!");

        ret.put("amount", moneyTransferRequest.getAmount());

        if(moneyTransferRequest.getCurrency() == null)
            throw new InternalException("Currency can't be null!");

        ret.put("currency", moneyTransferRequest.getCurrency());

        ret.put("isUrgent", moneyTransferRequest.getIsUrgent());
        ret.put("isInstant", moneyTransferRequest.getIsInstant());

        String feeType = moneyTransferRequest.getFeeType();;

        if(feeType != null && !feeType.isEmpty() && !feeTypeOk(feeType))
            throw new InternalException("FeeType does not match a proper value!");

        ret.put("feeAccountId", moneyTransferRequest.getFeeAccountId() != null ? moneyTransferRequest.getFeeAccountId() : JSONObject.NULL);

        ret.put("taxRelief", convertTaxReliefToJSON(moneyTransferRequest.getTaxRelief()));

        return ret;
    }

    private static JSONObject convertCreditorToJSON(Creditor creditor){
        JSONObject ret = creditor != null ? new JSONObject() : null;

        if(ret == null)
            throw new InternalException("Creditor can't be null!");

        ret.put("name", creditor.getName() != null ? creditor.getName() : JSONObject.NULL);
        ret.put("account", convertAccountToJSON(creditor.getAccount()));

        ret.put("address", convertAddressToJSON(creditor.getAddress()));

        return ret;

    }

    private static JSONObject convertAccountToJSON(Account account){
        JSONObject ret = account != null ? new JSONObject() : null;

        if(ret == null)
            throw new InternalException("Account can't be null!");

        String accountCode = account.getAccountCode();
        if(accountCode == null || accountCode.trim().isEmpty())
            throw new InternalException("Account Code can't be null!");
        ret.put("accountCode", accountCode);
        ret.put("bicCode", account.getBicCode() != null ? account.getBicCode() : JSONObject.NULL);

        return ret;
    }

    private static boolean feeTypeOk(String feeType){
        return  (feeType.equals("SHA") || feeType.equals("OUR") || feeType.equals("BEN") );
    }

    private static JSONObject convertAddressToJSON(Address address){
        JSONObject ret = address != null ? new JSONObject() : null;

        if(ret != null){
            ret.put("address", address.getAddress() != null ? address.getAddress() : JSONObject.NULL);
            ret.put("city", address.getCity() != null ? address.getCity() : JSONObject.NULL);
            ret.put("countryCode", address.getCountryCode() != null ? address.getCountryCode() : JSONObject.NULL);
        }

        return ret;
    }

    private static JSONObject convertTaxReliefToJSON(TaxRelief taxRelief){

        JSONObject ret = taxRelief != null ? new JSONObject() : null;

        if(ret != null){
            ret.put("taxReliefId", taxRelief.getTaxReliefId() != null ? taxRelief.getTaxReliefId() : JSONObject.NULL);
            ret.put("isCondoUpgrade", taxRelief.isCondoUpgrade());



            if(taxRelief.getCreditorFiscalCode() == null)
                throw new InternalException("Creditor Fiscal Code can't be null!");

            ret.put("creditorFiscalCode", taxRelief.getCreditorFiscalCode());

            if(taxRelief.getBeneficiaryType() == null)
                throw new InternalException("Beneficiary Type can't be null!");

            ret.put("beneficiaryType", taxRelief.getBeneficiaryType());

            ret.put("naturalPersonBeneficiary", convertNaturalPersonBenificiaryToJSON(taxRelief.getNaturalPersonBeneficiary()));

            ret.put("legalPersonBeneficiary", convertLegalPersonBeneficiary(taxRelief.getLegalPersonBeneficiary(), taxRelief.getBeneficiaryType()));
        }

        return ret;
    }

    private static JSONObject convertNaturalPersonBenificiaryToJSON(NaturalPersonBeneficiary naturalPersonBeneficiary){
        JSONObject ret = naturalPersonBeneficiary != null ? new JSONObject() : null;

        if(ret != null){
            if(naturalPersonBeneficiary.getFiscalCode1() == null)
                throw new InternalException("Natural Person Beneficiary Fiscal Code 1 can't be null!");

            ret.put("fiscalCode1", naturalPersonBeneficiary.getFiscalCode1());

            ret.put("fiscalCode2", naturalPersonBeneficiary.getFiscalCode2() != null ? naturalPersonBeneficiary.getFiscalCode2() : JSONObject.NULL);
            ret.put("fiscalCode3", naturalPersonBeneficiary.getFiscalCode3() != null ? naturalPersonBeneficiary.getFiscalCode3() : JSONObject.NULL);
            ret.put("fiscalCode4", naturalPersonBeneficiary.getFiscalCode4() != null ? naturalPersonBeneficiary.getFiscalCode4() : JSONObject.NULL);
            ret.put("fiscalCode5", naturalPersonBeneficiary.getFiscalCode5() != null ? naturalPersonBeneficiary.getFiscalCode5() : JSONObject.NULL);
        }

        return ret;
    }

    private static JSONObject convertLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiary, String beneficiaryType){

        JSONObject ret = legalPersonBeneficiary != null ? new JSONObject() : null;

        if(ret != null){
            if(legalPersonBeneficiary.getFiscalCode() == null && beneficiaryType.equals("LEGAL_PERSON"))
                throw new InternalException("Legal Person Beneficiary Fiscal Code can't be null!");

            ret.put("fiscalCode", legalPersonBeneficiary.getFiscalCode());

            ret.put("legalRepresentativeFiscalCode", legalPersonBeneficiary.getLegalRepresentativeFiscalCode() != null ? legalPersonBeneficiary.getFiscalCode() : JSONObject.NULL);
        }

        return ret;

    }

}
