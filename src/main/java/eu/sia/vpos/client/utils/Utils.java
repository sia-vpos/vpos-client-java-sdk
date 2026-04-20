package eu.sia.vpos.client.utils;

import eu.sia.vpos.client.utils.exception.VPosClientException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * General purpose utility class
 *
 * @author Gabriel Raul Marini
 */
public class Utils {

    private static final String NUMBERS = "0123456789";

    private Utils() {
    }

    /**
     * @return a string containing a random number with 24 digits
     */
    public static String generateRandomDigits(int n) {
        StringBuilder rndBuilder = new StringBuilder();
        SecureRandom rng = new SecureRandom();

        for (int i = 0; i < n; i++) {
            rndBuilder.append(NUMBERS.charAt(rng.nextInt(NUMBERS.length())));
        }

        return rndBuilder.toString();
    }

    public static Map<String, String> splitQuery(String urlString) throws  VPosClientException {
        Map<String, String> queryPairs = new LinkedHashMap<>();
        try {
            URL url = new URL(urlString);

            String query = url.getQuery();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new VPosClientException("Malformed Url");
        }
        return queryPairs;
    }


    /**
     * Hide a PAN
     *
     * @param pan (String): pan
     * @return String: hidden pan
     */
    public static String HidePan(String pan) {

        if (pan != null && !pan.trim().isEmpty()) {
            // pan length minore di 12 = viene restituito il valore ricevuto in ingresso
            if (pan.length() < 12) {
                return pan;
                // pan length compreso tra 12 e 15 = oscuro solo i primi e gli ultimi 3 caratteri
            } else if (pan.length() >= 12 && pan.length() < 16) {
                return (pan.substring(0, 3) + pan.substring(3, pan.length() - 3).replaceAll("[0-9]", "x"))
                        + pan.substring(pan.length() - 3, pan.length());
                // pan length maggiore di 16 = oscuro i primi 6 e gli ultimi 4 caratteri
            } else {
                return (pan.substring(0, 6) + pan.substring(6, pan.length() - 4).replaceAll("[0-9]", "x"))
                        + pan.substring(pan.length() - 4, pan.length());
            }
        } else {
            return "";
        }
    }

    /**
     * Hide a CVV
     *
     * @param cvv (String): cvv
     * @return String: hidden cvv
     */
    public static String HideCVV(String cvv) {

        if (cvv != null && !cvv.trim().isEmpty()) {

            StringBuilder hiddenCVV = new StringBuilder();

            for (int i = 0; i < cvv.length(); i++) {
                hiddenCVV.append('X');
            }

            return hiddenCVV.toString();
        } else {
            return "";
        }
    }

}
