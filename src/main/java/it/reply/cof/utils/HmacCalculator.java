package it.reply.cof.utils;

import it.reply.cof.utils.exception.COFException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class HmacCalculator {

    private static final String ALGORITHM = "HmacSHA256";
    private static final String ALGORITHM_512 = "HmacSHA512";

    private HmacCalculator() {

    }

    public static String calculate(String value, String key) throws COFException {
        String result = "";

        try {
            result = innerCalculate(value, key, ALGORITHM);
        } catch (Exception e) {
            throw new COFException("Hmac calculation failed", e.getCause());
        }

        return result;
    }

    private static String innerCalculate(String value, String key, String algorithm) throws Exception {
        Mac mac = Mac.getInstance(algorithm);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
        mac.init(secretKey);
        return Hex.encodeHexString(mac.doFinal(value.getBytes("UTF-8")));
    }

    public static String calculateWith512(String value, String key) throws Exception {
        return innerCalculate(value, key, ALGORITHM_512);
    }

}
