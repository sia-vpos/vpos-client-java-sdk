package it.reply.cof.utils;

import java.security.SecureRandom;


public class Utils {

    private static final String NUMBERS = "0123456789";

    private Utils() {
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
