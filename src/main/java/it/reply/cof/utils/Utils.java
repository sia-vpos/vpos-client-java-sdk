package it.reply.cof.utils;

import it.reply.cof.utils.constants.Operations;
import it.reply.cof.utils.exception.COFException;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;


public class Utils {

    private static final String CLASSNAME = Utils.class.getSimpleName();
    private static final String NUMBERS = "0123456789";

    private Utils() {

    }

    public static String addField(String field, String fieldName) {
        String mac = "";

        if ((field != null && !field.isEmpty()) || (fieldName.equals(Operations.PARAMETERS.URLDONE.NAME)
                || fieldName.equals(Operations.PARAMETERS.ORDERID.NAME)
                || fieldName.equals(Operations.PARAMETERS.SHOPID.NAME)
                || fieldName.equals(Operations.PARAMETERS.AMOUNT.NAME)
                || fieldName.equals(Operations.PARAMETERS.CURRENCY.NAME)
                || fieldName.equals(Operations.PARAMETERS.ACCOUNTINGMODE.NAME))) {
            mac = "&" + fieldName + "=" + field;
        }

        return mac;
    }

    public static String addFieldAuth(String field, String fieldName) {
        String mac = "";

        if ((field != null && !field.isEmpty()) || (!fieldName.equals(Operations.AUTHORIZATION.PANTAIL)
                && !fieldName.equals(Operations.AUTHORIZATION.PANEXPIRYDATE)
                && !fieldName.equals(Operations.AUTHORIZATION.CARDTYPE))) {
            mac = "&" + field;
        }

        return mac;
    }

    public static String addFieldDone(String field, String fieldName) {
        String mac = "";

        if ((field != null && !field.isEmpty()) || (fieldName.equals(Operations.PARAMETERS.ORDERID.NAME)
                || fieldName.equals(Operations.PARAMETERS.SHOPID.NAME)
                || fieldName.equals(Operations.CONFIRMATION.AUTHORIZATIONUMBER)
                || fieldName.equals(Operations.PARAMETERS.AMOUNT.NAME)
                || fieldName.equals(Operations.PARAMETERS.CURRENCY.NAME)
                || fieldName.equals(Operations.CONFIRMATION.TRANSACTIONID)
                || fieldName.equals(Operations.CONFIRMATION.RESULT)
                || fieldName.equals(Operations.PARAMETERS.ACCOUNTINGMODE.NAME)
                || fieldName.equals(Operations.PARAMETERS.NETWORK.NAME)
                || fieldName.equals(Operations.CONFIRMATION.TRANSACTIONTYPE))) {
            mac = "&" + fieldName + "=" + field;
        }

        return mac;
    }

    public static String generateRandomDigits() {
        StringBuilder rndBuilder = new StringBuilder();
        SecureRandom rng = new SecureRandom();

        for (int i = 0; i < 24; i++) {
            rndBuilder.append(NUMBERS.charAt(rng.nextInt(NUMBERS.length())));
        }

        return rndBuilder.toString();
    }



}
