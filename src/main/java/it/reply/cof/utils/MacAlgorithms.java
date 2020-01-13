package it.reply.cof.utils;

public enum MacAlgorithms {
    HMAC_SHA_256("HmacSHA256"), HMAC_SHA_512("HmacSHA512");

    private String value;

    private MacAlgorithms(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
