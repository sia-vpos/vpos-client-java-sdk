package it.reply.cof.utils.mac;

import it.reply.cof.utils.Constants.AposConstant;
import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.exception.COFException;

import java.util.Map;

/**
 * Utility class used in the context of message integrity check
 *
 * @author gab.marini
 */
public class Encoder {

    private HmacCalculator hmacCalculator;

    /**
     * Creates an Encoder with HMAC_SHA_256 encoding
     *
     * @throws COFException in case of code corruption
     */
    public Encoder() throws COFException {
        this(MacAlgorithms.HMAC_SHA_256);
    }

    /**
     * Creates an Encoder instance using the specified algorithm
     *
     * @param algorithm to be used during the MAC calculation
     * @throws COFException in case of code corruption
     */
    public Encoder(MacAlgorithms algorithm) throws COFException {
        hmacCalculator = new HmacCalculator(algorithm);
    }

    private static void appendField(String key, String value, StringBuilder sb) {
        if (value != null && !value.trim().isEmpty()) {
            sb.append('&');
            sb.append(key);
            sb.append('=');
            sb.append(value);
        }
    }

    /**
     * @param valueMap map containing all the values (required and optional) of REDIRECT messages
     * @param key      secret shared between merchant and SIA VPos
     * @return the encoded string
     * @throws COFException in case of failure (More information available in exception message)
     */
    public String getRedirectRequestMac(Map<String, String> valueMap, String key) throws COFException {
        StringBuilder sb = new StringBuilder();

        //start building hash string
        sb.append(AposConstant.URLMS).append("=").append(valueMap.get(AposConstant.URLMS));
        appendField(AposConstant.URLDONE, valueMap.get(AposConstant.URLDONE), sb);
        appendField(AposConstant.ORDERID, valueMap.get(AposConstant.ORDERID), sb);
        appendField(AposConstant.SHOPID, valueMap.get(AposConstant.SHOPID), sb);
        appendField(AposConstant.AMOUNT, valueMap.get(AposConstant.AMOUNT), sb);
        appendField(AposConstant.CURRENCY, valueMap.get(AposConstant.CURRENCY), sb);
        //optional
        appendField(AposConstant.EXPONENT, valueMap.get(AposConstant.EXPONENT), sb);
        appendField(AposConstant.ACCOUNTINGMODE, valueMap.get(AposConstant.ACCOUNTINGMODE), sb);
        appendField(AposConstant.AUTHORMODE, valueMap.get(AposConstant.AUTHORMODE), sb);
        //optionals
        appendField(AposConstant.OPTIONS, valueMap.get(AposConstant.OPTIONS), sb);
        appendField(AposConstant.NAME, valueMap.get(AposConstant.NAME), sb);
        appendField(AposConstant.SURNAME, valueMap.get(AposConstant.SURNAME), sb);
        appendField(AposConstant.TAXID, valueMap.get(AposConstant.TAXID), sb);
        appendField(AposConstant.LOCKCARD, valueMap.get(AposConstant.LOCKCARD), sb);
        appendField(AposConstant.COMMIS, valueMap.get(AposConstant.COMMIS), sb);
        appendField(AposConstant.ORDDESCR, valueMap.get(AposConstant.ORDDESCR), sb);
        appendField(AposConstant.VSID, valueMap.get(AposConstant.VSID), sb);
        appendField(AposConstant.OPDESCR, valueMap.get(AposConstant.OPDESCR), sb);
        appendField(AposConstant.REMAININGDURATION, valueMap.get(AposConstant.REMAININGDURATION);
        appendField(AposConstant.USERID, valueMap.get(AposConstant.USERID), sb);
        appendField(AposConstant.BP_POSTEPAY, valueMap.get(AposConstant.BP_POSTEPAY), sb);
        appendField(AposConstant.BP_CARDS, valueMap.get(AposConstant.BP_CARDS), sb);
        appendField(AposConstant.PHONENUMBER, valueMap.get(AposConstant.PHONENUMBER), sb);
        appendField(AposConstant.CAUSATION, valueMap.get(AposConstant.CAUSATION), sb);
        appendField(AposConstant.USER, valueMap.get(AposConstant.USER), sb);
        appendField(AposConstant.PRODUCTREF, valueMap.get(AposConstant.PRODUCTREF), sb);
        appendField(AposConstant.ANTIFRAUD, valueMap.get(AposConstant.ANTIFRAUD), sb);
        appendField(AposConstant.DATA3DS, valueMap.get(AposConstant.DATA3DS), sb);

        return hmacCalculator.calculate(sb.toString(), key);
    }

    /**
     * @param valueMap map containing all the values (required and optional) of REDIRECT messages
     * @param key      secret shared between merchant and SIA VPos
     * @return the encoded string
     * @throws COFException in case of failure (More information available in exception message)
     */
    public String getRedirectResponseMac(Map<String, String> valueMap, String key) throws COFException {
        StringBuilder sb = new StringBuilder();

        //start building hash string
        appendField(AposConstant.ORDERID, valueMap.get(AposConstant.ORDERID), sb);
        appendField(AposConstant.SHOPID, valueMap.get(AposConstant.SHOPID), sb);
        appendField(AposConstant.AUTHNUMBER, valueMap.get(AposConstant.AUTHNUMBER), sb);
        appendField(AposConstant.AMOUNT, valueMap.get(AposConstant.AMOUNT), sb);
        appendField(AposConstant.CURRENCY, valueMap.get(AposConstant.CURRENCY), sb);
        appendField(AposConstant.EXPONENT, valueMap.get(AposConstant.EXPONENT), sb);
        appendField(AposConstant.TRANSACTIONID, valueMap.get(AposConstant.TRANSACTIONID), sb);
        appendField(AposConstant.ACCOUNTINGMODE, valueMap.get(AposConstant.ACCOUNTINGMODE), sb);
        appendField(AposConstant.AUTHORMODE, valueMap.get(AposConstant.AUTHORMODE), sb);
        appendField(AposConstant.RESULT, valueMap.get(AposConstant.RESULT), sb);
        appendField(AposConstant.TRANSACTION_TYPE, valueMap.get(AposConstant.TRANSACTION_TYPE), sb);
        appendField(AposConstant.ISSUERCOUNTRY, valueMap.get(AposConstant.ISSUERCOUNTRY), sb);
        appendField(AposConstant.AUTHCODE, valueMap.get(AposConstant.AUTHCODE), sb);
        appendField(AposConstant.PAYERID, valueMap.get(AposConstant.PAYERID), sb);
        appendField(AposConstant.PAYER, valueMap.get(AposConstant.PAYER), sb);
        appendField(AposConstant.PAYERSTATUS, valueMap.get(AposConstant.PAYERSTATUS), sb);
        appendField(AposConstant.HASHPAN, valueMap.get(AposConstant.HASHPAN), sb);
        appendField(AposConstant.PANALIASREV, valueMap.get(AposConstant.PANALIASREV), sb);
        appendField(AposConstant.PANALIAS, valueMap.get(AposConstant.PANALIAS), sb);
        appendField(AposConstant.PANALIASEXPDATE, valueMap.get(AposConstant.PANALIASEXPDATE), sb);
        appendField(AposConstant.PANALIASTAIL, valueMap.get(AposConstant.PANALIASTAIL), sb);
        appendField(AposConstant.MASKEDPAN, valueMap.get(AposConstant.MASKEDPAN), sb);
        appendField(AposConstant.PANTAIL, valueMap.get(AposConstant.PANTAIL), sb);
        appendField(AposConstant.PANEXPIRYDATE, valueMap.get(AposConstant.PANEXPIRYDATE), sb);
        appendField(AposConstant.ACCOUNTHOLDER, valueMap.get(AposConstant.ACCOUNTHOLDER), sb);
        appendField(AposConstant.IBAN, valueMap.get(AposConstant.IBAN), sb);
        appendField(AposConstant.ALIASSTR, valueMap.get(AposConstant.ALIASSTR), sb);
        appendField(AposConstant.ACQUIRERBIN, valueMap.get(AposConstant.ACQUIRERBIN), sb);
        appendField(AposConstant.MERCHANTID, valueMap.get(AposConstant.MERCHANTID), sb);
        appendField(AposConstant.CARDTYPE, valueMap.get(AposConstant.CARDTYPE), sb);

        return hmacCalculator.calculate(sb.toString(), key);
    }
}
