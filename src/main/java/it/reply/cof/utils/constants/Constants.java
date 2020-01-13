package it.reply.cof.utils.constants;

public class Constants {

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
}
