package it.reply.cof.utils;

import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Constants;
import it.reply.cof.utils.constants.Operations;
import it.reply.cof.utils.exception.COFException;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Utils {

    private static final String CLASSNAME = Utils.class.getSimpleName();
    private static final String NUMBERS = "0123456789";

    private Utils() {

    }

    public static Map<String, String> generateMap(PaymentInfo transaction, String shopId, String urlMs,
                                                  String urlDone, String urlBack) {
        Map<String, String> map = new HashMap<>();
        map.put(AposConstants.ACCOUNTINGMODE, transaction.getAccountingMode());
        map.put(AposConstants.AMOUNT, transaction.getAmount());
        map.put(AposConstants.AUTHORMODE, transaction.getAuthorMode());
        map.put(AposConstants.CURRENCY, transaction.getCurrency());
        map.put(AposConstants.EXPONENT, transaction.getExponent());
     //   map.pus.AposConstasnt.MAC, transaction.getMac());
        map.put(AposConstants.ORDERID, transaction.getOrderId());
        map.put(AposConstants.SHOPID, transaction.getShopId());
        map.put(AposConstants.URLBACK, transaction.getUrlBack());
        map.put(AposConstants.URLDONE, transaction.getUrlDone());
        map.put(AposConstants.URLMS, transaction.getUrlMs());
        for (String option : transaction.getNotCompulsoryFields().keySet()) {
            map.put(option, transaction.getNotCompulsoryFields().get(option));
        }
        return map;
    }

    public static String generateUrl(Map<String, String> map, String key, String urlApos) throws COFException {
        StringBuilder sb = new StringBuilder();
        sb.append(AposConstants.URLMS + '=' + map.get(AposConstants.URLMS));

        appendField(AposConstants.URLDONE, map.get(AposConstants.URLDONE), sb);
        appendField(AposConstants.ORDERID, map.get(AposConstants.ORDERID), sb);
        appendField(AposConstants.SHOPID, map.get(AposConstants.SHOPID), sb);
        appendField(AposConstants.AMOUNT, map.get(AposConstants.AMOUNT), sb);
        appendField(AposConstants.CURRENCY, map.get(AposConstants.CURRENCY), sb);
        appendField(AposConstants.ACCOUNTINGMODE, map.get(AposConstants.ACCOUNTINGMODE), sb);
        appendField(AposConstants.AUTHORMODE, map.get(AposConstants.AUTHORMODE), sb);
        appendField(AposConstants.OPTIONS, map.get(AposConstants.OPTIONS), sb);

        String tmp = sb.toString();
    //    appendField(Constants.AposConstant.MAC, HmacCalculator.calculate(tmp, key), sb);

        appendField(AposConstants.URLBACK, map.get(AposConstants.URLBACK), sb);
        appendField(AposConstants.EMAIL, map.get(AposConstants.EMAIL), sb);

        return urlApos + "&LANG=ITA&" + sb.toString();
    }

    private static void appendField(String key, String string, StringBuilder sb) {
        sb.append('&');
        sb.append(key);
        sb.append('=');
        sb.append(string);
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

    public static String htmlToBase64(String fileName, Map<String, String> replacements) throws COFException {
        String html;

        try {
            html = new Scanner(new File("filename")).useDelimiter("\\Z").next();
        } catch (IOException e) {
            throw new COFException("Error while generating HTML base64 encoded file", e.getCause());
        }

        for (Map.Entry<String, String> replacement : replacements.entrySet()) {
            html = html.replace("[" + replacement.getKey() + "]", replacement.getValue());
        }

        return Base64.getEncoder().encodeToString(html.getBytes());
    }

}
