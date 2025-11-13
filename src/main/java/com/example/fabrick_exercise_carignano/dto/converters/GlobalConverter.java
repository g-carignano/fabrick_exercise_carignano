package com.example.fabrick_exercise_carignano.dto.converters;

import com.example.fabrick_exercise_carignano.dto.accountbalance.AccountBalance;
import com.example.fabrick_exercise_carignano.dto.accountbalance.BalanceResponse;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.fabrick.*;
import com.example.fabrick_exercise_carignano.dto.moneytransfer.local.*;
import com.example.fabrick_exercise_carignano.dto.transaction.fabrick.TransactionFabrick;
import com.example.fabrick_exercise_carignano.dto.transaction.fabrick.TransactionTypeFabrick;
import com.example.fabrick_exercise_carignano.dto.transaction.local.Transaction;
import com.example.fabrick_exercise_carignano.dto.transaction.local.TransactionType;

public class GlobalConverter {
    public static MoneyTransferFabrickRequest mapMoneyTransferRequestToMoneyTransferFabrickRequest(MoneyTransferRequest moneyTransferRequest){
        MoneyTransferFabrickRequest ret = new MoneyTransferFabrickRequest();

        ret.setCreditor(mapCreditorToCreditorFabrick(moneyTransferRequest.getCreditor()));
        ret.setUri(moneyTransferRequest.getUri());
        ret.setIsUrgent(moneyTransferRequest.getIsUrgent());
        ret.setIsInstant(moneyTransferRequest.getIsInstant());
        ret.setCurrency(moneyTransferRequest.getCurrency());
        ret.setFeeType(moneyTransferRequest.getFeeType());
        ret.setFeeAccountId(moneyTransferRequest.getFeeAccountId());
        ret.setExecutionDate(moneyTransferRequest.getExecutionDate());
        ret.setDescription(moneyTransferRequest.getDescription());
        ret.setTaxRelief(mapTaxReliefToTaxReliefFabrick(moneyTransferRequest.getTaxRelief()));
        ret.setAmount(Math.round(moneyTransferRequest.getAmount() * 100));

        return ret;
    }

    public static AccountBalance mapBalanceResponseIntoAccountBalance(BalanceResponse balanceResponse, long accountId){
        AccountBalance ret = new AccountBalance();

        ret.setAccountId(accountId);
        ret.setBalance(balanceResponse.getBalance() != null ? balanceResponse.getBalance().toString() : null);

        return ret;
    }

    private static CreditorFabrick mapCreditorToCreditorFabrick(Creditor creditor){
        CreditorFabrick ret = creditor != null ? new CreditorFabrick() : null;

        if(ret != null){
            ret.setAccount(mapAccountToAccountFabrick(creditor.getAccount()));
            ret.setAddress(mapAddressToAddressFabrick(creditor.getAddress()));
            ret.setName(creditor.getName());
        }


        return ret;
    }

    private static AccountFabrick mapAccountToAccountFabrick(Account account){
        AccountFabrick ret = account != null ? new AccountFabrick() : null;

        if(ret != null){
            ret.setAccountCode(account.getAccountCode());
            ret.setBicCode(account.getBicCode());
        }

        return ret;
    }

    private static AddressFabrick mapAddressToAddressFabrick(Address address){
        AddressFabrick ret = address != null ? new AddressFabrick() : null;

        if(ret != null){
            ret.setAddress(address.getAddress());
            ret.setCity(address.getCity());
            ret.setCountryCode(address.getCountryCode());
        }

        return ret;
    }

    private static TaxReliefFabrick mapTaxReliefToTaxReliefFabrick(TaxRelief taxRelief){
        TaxReliefFabrick ret = taxRelief != null ? new TaxReliefFabrick() : null;

        if(ret != null){
            ret.setTaxReliefId(taxRelief.getTaxReliefId());
            ret.setBeneficiaryType(taxRelief.getBeneficiaryType());
            ret.setCreditorFiscalCode(taxRelief.getCreditorFiscalCode());
            ret.setIsCondoUpgrade(taxRelief.getIsCondoUpgrade());
            ret.setNaturalPersonBeneficiary(mapNaturalPersonBeneficiaryToNaturalPersonBeneficiaryFabrick(taxRelief.getNaturalPersonBeneficiary()));
            ret.setLegalPersonBeneficiary(mapLegalPersonBeneficiaryToLegalPersonBeneficiaryFabrick(taxRelief.getLegalPersonBeneficiary()));
        }

        return ret;
    }

    private static NaturalPersonBeneficiaryFabrick mapNaturalPersonBeneficiaryToNaturalPersonBeneficiaryFabrick(NaturalPersonBeneficiary naturalPersonBeneficiary){
        NaturalPersonBeneficiaryFabrick ret = naturalPersonBeneficiary != null ? new NaturalPersonBeneficiaryFabrick() : null;

        if(ret != null){
            ret.setFiscalCode1(naturalPersonBeneficiary.getFiscalCode1());
            ret.setFiscalCode2(naturalPersonBeneficiary.getFiscalCode2());
            ret.setFiscalCode3(naturalPersonBeneficiary.getFiscalCode3());
            ret.setFiscalCode4(naturalPersonBeneficiary.getFiscalCode4());
            ret.setFiscalCode5(naturalPersonBeneficiary.getFiscalCode5());
        }

        return ret;
    }

    private static LegalPersonBeneficiaryFabrick mapLegalPersonBeneficiaryToLegalPersonBeneficiaryFabrick(LegalPersonBeneficiary legalPersonBeneficiary){
        LegalPersonBeneficiaryFabrick ret = legalPersonBeneficiary != null ? new LegalPersonBeneficiaryFabrick() : null;

        if(ret != null){
            ret.setFiscalCode(legalPersonBeneficiary.getFiscalCode());
            ret.setLegalRepresentativeFiscalCode(legalPersonBeneficiary.getLegalRepresentativeFiscalCode());
        }

        return ret;

    }

    public static Transaction mapTransactionFabrickToTransaction(TransactionFabrick transaction){

        Transaction ret = transaction != null ? new Transaction() : null;

        if(ret != null){
            ret.setTransactionId(transaction.getTransactionId());
            ret.setDescription(transaction.getDescription());
            ret.setAmount(transaction.getAmount());
            ret.setOperationId(transaction.getOperationId());
            ret.setAccountingDate(transaction.getAccountingDate());
            ret.setValueDate(transaction.getValueDate());
            ret.setCurrency(transaction.getCurrency());
            ret.setType(mapTransactionTypeFabrickToTransactionType(transaction.getType()));
        }

        return ret;

    }

    private static TransactionType mapTransactionTypeFabrickToTransactionType(TransactionTypeFabrick transactionType){
        TransactionType ret = transactionType != null ? new TransactionType() : null;

        if(ret != null){
            ret.setEnumeration(transactionType.getEnumeration());
            ret.setValue(transactionType.getValue());
        }

        return ret;
    }
}
