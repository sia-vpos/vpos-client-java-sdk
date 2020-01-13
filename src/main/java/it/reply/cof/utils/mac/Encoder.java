package it.reply.cof.utils.mac;

import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.exception.COFException;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Utility class used in the context of message integrity check
 *
 * @author gab.marini
 */
public class Encoder {

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

    private static void appendField(String key, String value, StringBuilder sb) {
        if (value != null && !value.trim().isEmpty()) {
            sb.append('&');
            sb.append(key);
            sb.append('=');
            sb.append(value);
        }
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

        return hmacCalculator.calculate(sb.toString(), key);
    }
}
