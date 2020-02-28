package eu.sia.vpos.client.utils;

import java.security.SecureRandom;

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

}
