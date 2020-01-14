package it.reply.cof.utils;

import it.reply.cof.apos.response.APosResponse;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Constants;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.HmacCalculator;

import java.util.HashMap;
import java.util.Map;

public class HTMLGenerator {

    private String generateHtml(Map<String, String> replacements,
                                PaymentInfo transaction, String refNumber, String statusCode)
            throws COFException {
        String paymentInstrumentUrl = "";
        final String NO_PID = Constants.StatusCode.NO_PID.getValue();
        final String INVALID_EXPIRED_PID = Constants.StatusCode.PID_EXPIRED_INVALID.getValue();
        final String STEP2_3DS = Constants.StatusCode.STEP2_3DS.getValue();

        if (NO_PID.equals(statusCode) || INVALID_EXPIRED_PID.equals(statusCode)) {
            paymentInstrumentUrl = generateRedirectHtml(replacements, transaction);
        } else if (STEP2_3DS.equals(statusCode)) {
            paymentInstrumentUrl = generateACSHtml(replacements, refNumber);
        }

        return paymentInstrumentUrl;
    }

    private String generateRedirectHtml(Map<String, String> replacements, PaymentInfo transaction, String urlDone, String urlBack, String urlMs, String key)
            throws COFException {
        String paymentInstrumentUrl;
        Map<String, String> map = generateMap(transaction, transaction.getShopId(), urlMs,
                urlDone, urlBack);
        String urlApos = "https://atpostest.ssb.it/atpos/pagamenti/main?PAGE=LAND";
        String redirectUrl = generateUrl(map, key, urlApos);
        replacements.put("REDIRECTURL", redirectUrl);
        replacements.put("URLDONE", urlDone);
        replacements.put("URLBACK", urlBack);
        paymentInstrumentUrl = Utils.htmlToBase64("RedirectURL.html", replacements);
        return paymentInstrumentUrl;
    }

    private String generateACSHtml(Map<String, String> replacements, String refNumber,
                                   APosResponse authRes) throws COFException {
        String paymentInstrumentUrl;
        replacements.put("URLACS", authRes.getAcsURL());
        replacements.put("PAREQ", authRes.getPaReq());
        String termURL = httpRequest.getLocalAddr() + ":" + httpRequest.getLocalPort() + httpRequest.getContextPath()
                + "/auth3DSStep2";
        replacements.put("TERMURL", termURL);
        replacements.put("MD", refNumber);
        paymentInstrumentUrl = Utils.htmlToBase64("ACSRedirect.html", replacements);
        return paymentInstrumentUrl;
    }

    public static Map<String, String> generateMap(PaymentInfo transaction, String shopId, String urlMs,
                                                  String urlDone, String urlBack) {
        Map<String, String> map = new HashMap<>();
        map.put(AposConstants.ACCOUNTINGMODE, transaction.getAccountingMode());
        map.put(AposConstants.AMOUNT, transaction.getAmount());
        map.put(AposConstants.AUTHORMODE, transaction.getAuthorMode());
        map.put(AposConstants.CURRENCY, transaction.getCurrency());
        map.put(AposConstants.EXPONENT, transaction.getExponent());
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

        HmacCalculator hmacCalculator= new HmacCalculator();
        appendField(AposConstants.MAC, hmacCalculator.calculate(tmp, key), sb);

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

}
