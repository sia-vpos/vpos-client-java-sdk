package it.reply.cof.utils.constants;

public final class Errors {

    public static final String MALFORMED_REQUEST = "Malformed Request. Request fields are not qualified or rejected due to invalid syntax";
    public static final String MALFORMED_RESPONSE = "Malformed Response. Response fields are not qualified or rejected due to invalid syntax";

    public static final String INVALID_CHANNEL = "Channel is invalid. User not authorized";
    public static final String INVALID_LOCATION = "Location is invalid. User not authorized";
    public static final String INVALID_PID = "PaymentInstrumentId is unknown";

    private Errors() {

    }

}