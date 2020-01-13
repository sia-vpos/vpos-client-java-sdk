package it.reply.cof.utils;

import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.HmacCalculator;

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
        map.put(Constants.AposConstant.ACCOUNTINGMODE, transaction.getAccountingMode());
        map.put(Constants.AposConstant.AMOUNT, transaction.getAmount());
        map.put(Constants.AposConstant.AUTHORMODE, transaction.getAuthorMode());
        map.put(Constants.AposConstant.CURRENCY, transaction.getCurrency());
        map.put(Constants.AposConstant.EXPONENT, transaction.getExponent());
        map.put(Constants.AposConstant.MAC, transaction.getMac());
        map.put(Constants.AposConstant.ORDERID, transaction.getOrderId());
        map.put(Constants.AposConstant.SHOPID, transaction.getShopId());
        map.put(Constants.AposConstant.URLBACK, transaction.getUrlBack());
        map.put(Constants.AposConstant.URLDONE, transaction.getUrlDone());
        map.put(Constants.AposConstant.URLMS, transaction.getUrlMs());
        for (String option : transaction.getNotCompulsoryFields().keySet()) {
            map.put(option, transaction.getNotCompulsoryFields().get(option));
        }
        return map;
    }

    public static String generateUrl(Map<String, String> map, String key, String urlApos) throws COFException {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.AposConstant.URLMS + '=' + map.get(Constants.AposConstant.URLMS));

        appendField(Constants.AposConstant.URLDONE, map.get(Constants.AposConstant.URLDONE), sb);
        appendField(Constants.AposConstant.ORDERID, map.get(Constants.AposConstant.ORDERID), sb);
        appendField(Constants.AposConstant.SHOPID, map.get(Constants.AposConstant.SHOPID), sb);
        appendField(Constants.AposConstant.AMOUNT, map.get(Constants.AposConstant.AMOUNT), sb);
        appendField(Constants.AposConstant.CURRENCY, map.get(Constants.AposConstant.CURRENCY), sb);
        appendField(Constants.AposConstant.ACCOUNTINGMODE, map.get(Constants.AposConstant.ACCOUNTINGMODE), sb);
        appendField(Constants.AposConstant.AUTHORMODE, map.get(Constants.AposConstant.AUTHORMODE), sb);
        appendField(Constants.AposConstant.OPTIONS, map.get(Constants.AposConstant.OPTIONS), sb);

        String tmp = sb.toString();
        appendField(Constants.AposConstant.MAC, HmacCalculator.calculate(tmp, key), sb);

        appendField(Constants.AposConstant.URLBACK, map.get(Constants.AposConstant.URLBACK), sb);
        appendField(Constants.AposConstant.EMAIL, map.get(Constants.AposConstant.EMAIL), sb);

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

        if ((field != null && !field.isEmpty()) || (fieldName.equals(Constants.OPERATIONS.PARAMETERS.URLDONE.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.ORDERID.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.SHOPID.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.AMOUNT.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.CURRENCY.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.ACCOUNTINGMODE.NAME))) {
            mac = "&" + fieldName + "=" + field;
        }

        return mac;
    }

    public static String addFieldAuth(String field, String fieldName) {
        String mac = "";

        if ((field != null && !field.isEmpty()) || (!fieldName.equals(Constants.OPERATIONS.AUTHORIZATION.PANTAIL)
                && !fieldName.equals(Constants.OPERATIONS.AUTHORIZATION.PANEXPIRYDATE)
                && !fieldName.equals(Constants.OPERATIONS.AUTHORIZATION.CARDTYPE))) {
            mac = "&" + field;
        }

        return mac;
    }

    public static String addFieldDone(String field, String fieldName) {
        String mac = "";

        if ((field != null && !field.isEmpty()) || (fieldName.equals(Constants.OPERATIONS.PARAMETERS.ORDERID.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.SHOPID.NAME)
                || fieldName.equals(Constants.OPERATIONS.CONFIRMATION.AUTHORIZATIONUMBER)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.AMOUNT.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.CURRENCY.NAME)
                || fieldName.equals(Constants.OPERATIONS.CONFIRMATION.TRANSACTIONID)
                || fieldName.equals(Constants.OPERATIONS.CONFIRMATION.RESULT)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.ACCOUNTINGMODE.NAME)
                || fieldName.equals(Constants.OPERATIONS.PARAMETERS.NETWORK.NAME)
                || fieldName.equals(Constants.OPERATIONS.CONFIRMATION.TRANSACTIONTYPE))) {
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
