package it.reply.cof.utils;

import it.reply.cof.utils.exception.COFException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class created to perform HMAC integrity check
 *
 * @author gab.marini
 */
public final class HmacCalculator {

    private static MacAlgorithms algorithm;
    private static Mac mac;

    public HmacCalculator() throws COFException {
        this(MacAlgorithms.HMAC_SHA_256);
    }

    public HmacCalculator(MacAlgorithms algorithm) throws COFException {
        try {
            this.algorithm = algorithm;
            this.mac = Mac.getInstance(algorithm.getValue());
        } catch (NoSuchAlgorithmException e) {
            throw new COFException("Invalid algorithm. Code corruption", e);
        }
    }

    /**
     * @param value on which MAC is calculated
     * @param key   used to calculate the MAC
     * @return the MAC of the string value
     * @throws COFException with relative description in case of failure
     */
    public static String calculate(String value, String key) throws COFException {
        String result = "";

        try {
            result = innerCalculate(value, key);
        } catch (Exception e) {
            throw new COFException("Hmac calculation failed", e.getCause());
        }

        return result;
    }

    private static String innerCalculate(String value, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), algorithm.getValue());
        mac.init(secretKey);
        return Hex.encodeHexString(mac.doFinal(value.getBytes("UTF-8")));
    }

    /**
     * @param value on which MAC is calculated
     * @param key   used to calculate the MAC
     * @return the MAC of the string value
     * @throws COFException with relative description in case of failure
     */
    public static String calculateWith512(String value, String key) throws Exception {
        algorithm = MacAlgorithms.HMAC_SHA_512;
        mac = Mac.getInstance(MacAlgorithms.HMAC_SHA_512.getValue());
        return innerCalculate(value, key);
    }

}
