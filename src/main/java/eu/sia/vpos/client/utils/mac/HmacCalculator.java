package eu.sia.vpos.client.utils.mac;

import eu.sia.vpos.client.utils.exception.VPosClientException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class used to perform HMAC calculations
 *
 * @author Gabriel Raul Marini
 */
public final class HmacCalculator {

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private MacAlgorithms algorithm;
    private Mac mac;

    /**
     * Creates an instance of HmacCalculator enabled to perform HMAC-256 calculations
     *
     * @throws VPosClientException in case of code corruption (invalid algorithm)
     */
    public HmacCalculator() throws VPosClientException {
        this(MacAlgorithms.HMAC_SHA_256);
    }

    /**
     * Creates an instance of HmacCalculator initialized to perform calculations
     * with the specified algorithm
     *
     * @param algorithm used to perform the MAC calculation
     * @throws VPosClientException in case of code corruption (invalid algorithm)
     */
    public HmacCalculator(MacAlgorithms algorithm) throws VPosClientException {
        try {
            this.algorithm = algorithm;
            this.mac = Mac.getInstance(algorithm.getValue());
        } catch (NoSuchAlgorithmException e) {
            throw new VPosClientException("Invalid algorithm. Code corruption", e);
        }
    }

    /**
     * @param value on which MAC is calculated
     * @param key   used to calculate the MAC
     * @return the MAC of the string value
     * @throws VPosClientException with relative description in case of failure
     */
    public String calculate(String value, String key) throws VPosClientException {
        String result = "";

        try {
            result = innerCalculate(value, key);
        } catch (Exception e) {
            throw new VPosClientException("Hmac calculation failed", e.getCause());
        }

        return result;
    }

    /**
     * @param value on which MAC is calculated
     * @param key   used to calculate the MAC
     * @return the MAC of the string value
     * @throws VPosClientException with relative description in case of failure
     */
    public String calculateWith512(String value, String key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        algorithm = MacAlgorithms.HMAC_SHA_512;
        mac = Mac.getInstance(MacAlgorithms.HMAC_SHA_512.getValue());
        return innerCalculate(value, key);
    }

    /**
     * @return the algorithm used to perform HMAC calculation
     */
    public MacAlgorithms getAlgorithm() {
        return algorithm;
    }

    /**
     * @param algorithm used to perform the MAC calculation
     */
    public void setAlgorithm(MacAlgorithms algorithm) {
        this.algorithm = algorithm;
    }

    private String innerCalculate(String value, String key) throws InvalidKeyException, UnsupportedEncodingException {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(CHARSET.name()), algorithm.getValue());
        mac.init(secretKey);
        return Hex.encodeHexString(mac.doFinal(value.getBytes(CHARSET.name())));
    }

}
