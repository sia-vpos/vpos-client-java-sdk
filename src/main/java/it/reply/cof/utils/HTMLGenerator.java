package it.reply.cof.utils;

import it.reply.cof.apos.response.APosResponse;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.constants.Constants;
import it.reply.cof.utils.exception.COFException;

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

    private String generateRedirectHtml(Map<String, String> replacements, PaymentInfo transaction, String urlDone, String urlBack, String urlMs)
            throws COFException {
        String paymentInstrumentUrl;
        Map<String, String> map = Utils.generateMap(transaction, transaction.getShopId(), urlMs,
                urlDone, urlBack);

        String redirectUrl = Utils.generateUrl(map, configuration.getRedirectHMacKey(), transaction.getUrlRedirect());
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

}
