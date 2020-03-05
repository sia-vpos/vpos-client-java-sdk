package eu.sia.vpos.client.utils.constants;

public class Constants {

    public static final String CVV2 = "CVV2";
    public static final String UTF8 = "UTF-8";
    public static final String KEY_SEPARATOR = ".";
    public static final String DEFAULT = "DEFAULT";
    public static final String CONTACTPOINTS_SEPARATOR = "\\|";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String TIMESTAMP_PATTERN_VALIDATOR_AUTHORIZE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String TIMESTAMP_PATTERN_WS = "^([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2}):([0-9]{2})";
    public static final String REFNUM_PATTERN = "yyyyMMdd";
    public static final String MAC_VALIDATION = "true";
    public static final String PANALIAS_ERROR = "ERROR";
    public static final String PANALIAS_NULL = "NULL";
    public static final String ALIAS_NETWORK = "98";
    public static final String EXP_DATE_FORMAT = "yyMM";
    public static final String INVALID_PID = "0";
    public static final String DEFAULT_ALG= "HMAC_SHA_256";

    private Constants() {

    }

    public enum StatusCode {
        CONFIRMED("000"), NO_PID("100"), PID_EXPIRED_INVALID("200"), STEP2_3DS("300"), FAILED("900");

        String value;

        StatusCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public enum TransactionType {
        AUTHORIZE("authorize"), CANCEL("cancel"), CONFIRM("confirm"), REFUND("refund"), VERIFY("verify");

        String value;

        TransactionType(String value) {
            this.value = value;
        }

        public static boolean parse(String field) {
            boolean notPresent = true;
            for (TransactionType type : TransactionType.values()) {
                if (field != null && field.equals(type.getValue())) {
                    notPresent = false;
                }
            }
            return notPresent;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Currency {
        EUR("978");

        String value;

        Currency(String value) {
            this.value = value;
        }

        public static String getCurrencyName(String code) {
            String currName = "";
            for (Currency curr : Currency.values()) {
                if (code != null && code.equals(curr.getValue())) {
                    currName = curr.name();
                }
            }
            return currName;
        }

        public static boolean parse(String field) {
            boolean isPresent = false;
            for (Currency curr : Currency.values()) {
                if (field != null && field.equals(curr.getValue())) {
                    isPresent = true;
                }
            }
            return isPresent;
        }

        public static Currency getCurrency(String field) {
            for (Currency curr : Currency.values()) {
                if (field != null && field.equals(curr.name())) {
                    return curr;
                }
            }
            return Currency.EUR;
        }

        public String getValue() {
            return value;
        }
    }

    public enum AccountingMode {
        DEFERRED("D"), IMMEDIATE("I");

        String value;

        AccountingMode(String value) {
            this.value = value;
        }

        public static boolean parse(String field) {
            boolean isPresent = false;
            for (AccountingMode acc : AccountingMode.values()) {
                if (field != null && field.equals(acc.getValue())) {
                    isPresent = true;
                }
            }
            return isPresent;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Lang {
        ITALIAN("ITA"), ENGLISH("EN");

        String value;

        Lang(String value) {
            this.value = value;
        }

        public static boolean parse(String field) {
            boolean notPresent = true;
            for (Lang lg : Lang.values()) {
                if (field != null && field.equals(lg.getValue())) {
                    notPresent = false;
                }
            }
            return notPresent;
        }

        public String getValue() {
            return value;
        }
    }

    public enum TransactionStatus {
        CONFIRMED("00"), AUTHORIZED("01"), AUTHORIZATION_IN_PROGRESS("02"), REFUNDED("03"), CANCELLED("04"),
        UNAUTHORIZED("05"), REJECTED("06"), ERROR("07"), AUTH_3DS_DONE("08"), AUTH_3DS_IN_PROGRESS("09"),
        TO_BE_CONFIRMED("10"), TO_BE_REFUNDED("11"), REFUND_FAILED("12"), CANCEL_FAILED("13");

        String value;

        TransactionStatus(String value) {
            this.value = value;
        }

        public static String getStatusName(String code) {
            String ts = "";
            for (TransactionStatus status : TransactionStatus.values()) {
                if (code != null && code.equals(status.getValue())) {
                    ts = status.name();
                }
            }
            return ts;
        }

        public static boolean parse(String field) {
            boolean notPresent = true;
            for (TransactionStatus status : TransactionStatus.values()) {
                if (field != null && field.equals(status.getValue())) {
                    notPresent = false;
                }
            }
            return notPresent;
        }

        public String getValue() {
            return value;
        }
    }
}
