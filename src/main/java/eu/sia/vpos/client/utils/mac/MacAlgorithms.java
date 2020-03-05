package eu.sia.vpos.client.utils.mac;

/**
 * Enumerator containing all the supported MAC algorithms
 *
 * @author Gabriel Raul Marini
 */
public enum MacAlgorithms {
    HMAC_SHA_256("HmacSHA256"), HMAC_SHA_512("HmacSHA512");

    private String value;

    public static boolean parse(String field) {
        boolean notPresent = true;
        for (MacAlgorithms type : MacAlgorithms.values()) {
            if (field != null && field.equals(type.getValue())) {
                notPresent = false;
            }
        }
        return notPresent;
    }
    private MacAlgorithms(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
