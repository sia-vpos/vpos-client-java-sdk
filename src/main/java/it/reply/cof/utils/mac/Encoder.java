package it.reply.cof.utils.mac;

import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.exception.COFException;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class used in the context of message integrity check
 *
 * @author Gabriel Raul Marini
 */
public final class Encoder {

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

    /**
     * Calculates the MAC of the string MAPKEY1=MAPVAL1&MAPKEY2=MAPVALUE2...
     *
     * @param valueMap containing all the values from which MAC is calculated
     * @param key      secret shared between merchant and SIA VPos
     * @return the MAC calculated from the input map
     * @throws COFException in case of failure (More details are specified in exception message)
     */
    public String getMac(Map<String, String> valueMap, String key) throws COFException {
        StringBuilder sb = new StringBuilder();

        for (Entry<String, String> entry : valueMap.entrySet())
            appendField(entry.getKey().toUpperCase(), entry.getValue(), sb);
        //deleting the first &
        sb.deleteCharAt(0);

        Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "MAC string: " +sb.toString());

        return hmacCalculator.calculate(sb.toString(), key);
    }

    public String getMac(List<String> values, String key) throws COFException {
        StringBuilder sb = new StringBuilder();

        for (String value : values) {
            appendField(value, sb);
        }

        sb.deleteCharAt(0);
        return hmacCalculator.calculate(sb.toString(), key);
    }

    public String getMac(String value, String key) throws COFException {
        return hmacCalculator.calculate(value, key);
    }

    private void appendField(String key, String value, StringBuilder sb) {
        if (value != null) {
            sb.append('&');
            sb.append(key);
            sb.append('=');
            sb.append(value);
        }
    }

    private void appendField(String value, StringBuilder sb) {
        if (value != null && !value.trim().isEmpty()) {
            sb.append("&");
            sb.append(value);
        }
    }
}
