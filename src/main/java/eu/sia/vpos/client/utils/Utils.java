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
        URL url= null;
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        try {
            url = new URL(urlString);

            String query = url.getQuery();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new VPosClientException("Malformed Url");
        }
        return query_pairs;
    }

}
