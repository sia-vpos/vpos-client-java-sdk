package it.reply.cof.utils;

public class Constants {

    private Constants() {

    }

    public static final class AposConstant {

        private AposConstant() {

        }

        public static final String AMOUNT = "AMOUNT";
        public static final String CURRENCY = "CURRENCY";
        public static final String SHOPID = "SHOPID";
        public static final String ORDERID = "ORDERID";
        public static final String URLDONE = "URLDONE";
        public static final String URLBACK = "URLBACK";
        public static final String URLMS = "URLMS";
        public static final String ACCOUNTINGMODE = "ACCOUNTINGMODE";
        public static final String AUTHORMODE = "AUTHORMODE";
        public static final String OPTIONS = "OPTIONS";
        public static final String MAC = "MAC";
        public static final String EMAIL = "EMAIL";
        public static final String EXPONENT= "EXPONENT";
        public static final String RESP_OK = "00";
        public static final String RESP_3DS = "20";
        public static final String RESP_TRANS_KO = "99";
        public static final String INCORRECT_CARD_NUMBER = "05";

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

        public String getValue() {
            return value;
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
    }

    public enum Currency {
        EUR("978");

        String value;

        Currency(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
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
    }

    public enum AccountingMode {
        DEFERRED("D"), IMMEDIATE("I");

        String value;

        AccountingMode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
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
    }

    public enum Lang {
        ITALIAN("ITA"), ENGLISH("EN");

        String value;

        Lang(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
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
    }

    public static final class OPERATIONS {

        private OPERATIONS() {

        }

        public static final class PARAMETERS {

            private PARAMETERS() {

            }

            public static final String OPERATION = "OPERATION";
            public static final String AUTHORIZATION3DSSTEP1 = "AUTHORIZATION3DSSTEP1";
            public static final String AUTHORIZATION3DSSTEP2 = "AUTHORIZATION3DSSTEP2";
            public static final String IBANAUTHORIZATION = "IBANAUTHORIZATION";
            public static final String REFUND = "REFUND";
            public static final String CONFIRM = "CONFIRM";
            public static final String TIMESTAMP = "TIMESTAMP";
            public static final String REQREFNUM = "REQREFNUM";
            public static final String ORIGINALREQREFNUM = "ORIGINALREQREFNUM";
            public static final String PARES = "PARES";
            public static final String INPERSON = "INPERSON";
            public static final String MERCHANTURL = "MERCHANTURL";

            public static final class AMOUNT {

                private AMOUNT() {

                }

                public static final String NAME = "AMOUNT";
                public static final int MIN_LEN = 2;
                public static final int MAX_LEN = 8;
                public static final String PATTERN = "[0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class CURRENCY {

                private CURRENCY() {

                }

                public static final String NAME = "CURRENCY";
                public static final int LEN = 3;
                public static final String PATTERN = "[0-9]{" + LEN + "}";
                public static final String EURO = "978";
                public static final boolean MANDATORY = true;
            }

            public static final class EXPONENT {

                private EXPONENT() {

                }

                public static final String NAME = "EXPONENT";
                public static final int LEN = 1;
                public static final String PATTERN = "[0-9]{" + LEN + "}";
                public static final boolean MANDATORY = false; // CONDITIONAL
                public static final String EURO = "2";
                public static final int DEFAULT = 2;
            } // Only if Currency is different from 978 (Euro)

            public static final class ORDERID {

                private ORDERID() {

                }

                public static final String NAME = "ORDERID";
                public static final int MIN_LEN = 1;
                public static final int MAX_LEN = 50;
                public static final String PATTERN = "[a-zA-Z0-9\\\\\\-\\_]{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class TRANSACTIONID {

                private TRANSACTIONID() {

                }

                public static final String NAME = "TRANSACTIONID";
                /*
                 * public static final int LEN = 15; public static final String PATTERN =
                 * "[0-9]{" + LEN + "}"; public static final boolean MANDATORY = true;
                 */
            }

            public static final class SHOPID {

                private SHOPID() {

                }

                public static final String NAME = "SHOPID";
                public static final int LEN = 15;
                public static final String PATTERN = "[0-9]{" + LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class SHORTID {

                private SHORTID() {

                }

                public static final String NAME = "SHORTID";
                public static final int LEN = 2;
                public static final String PATTERN = "[0-9]{" + LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class URLBACK {

                private URLBACK() {

                }

                public static final String NAME = "URLBACK";
                public static final int MIN_LEN = 10;
                public static final int MAX_LEN = 254;
                public static final String PATTERN = "^https?://.{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class URLDONE {

                private URLDONE() {

                }

                public static final String NAME = "URLDONE";
                public static final int MIN_LEN = 10;
                public static final int MAX_LEN = 254;
                public static final String PATTERN = "^https?://.{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class URLMS {

                private URLMS() {

                }

                public static final String NAME = "URLMS";
                public static final int MIN_LEN = 10;
                public static final int MAX_LEN = 400;
                public static final String PATTERN = "^https?://.{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = false; // OPTIONAL
            }

            public static final class ACCOUNTINGMODE {

                private ACCOUNTINGMODE() {

                }

                public static final String NAME = "ACCOUNTINGMODE";
                public static final int LEN = 1;
                public static final String PATTERN = "[DI]{" + LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class MAC {

                private MAC() {

                }

                public static final String NAME = "MAC";
                public static final int MIN_LEN = 32;
                public static final int MAX_LEN = 32;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class NETWORK {

                private NETWORK() {

                }

                public static final String NAME = "NETWORK";
                public static final int LEN = 2;
                public static final String PATTERN = "[0-9]{" + LEN + "}";
                public static final boolean MANDATORY = false; // CONDITIONAL
            }

            public static final class PAN {

                private PAN() {

                }

                public static final String NAME = "PAN";
                public static final int MIN_LEN = 10;
                public static final int MAX_LEN = 18;
                public static final int PANALIAS_LEN = 19;
                public static final String PATTERNGENERIC = "^[0-9]{" + MIN_LEN + "," + PANALIAS_LEN + "}";
                public static final String PATTERNTOKENCARD = "^[0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final String PATTERNPAYPALPANALIAS = "^[0-9]{" + PANALIAS_LEN + "}";
                public static final boolean MANDATORY = false; // CONDITIONAL
            }

            public static final class EXPDATE {

                private EXPDATE() {

                }

                public static final String NAME = "EXPDATE";
                public static final String PATTERN = "^[0-9]{2}(0[0-9]|1[0-2])";
                public static final boolean MANDATORY = false; // CONDITIONAL
            }

            public static final class OPERATORID {

                private OPERATORID() {

                }

                public static final String NAME = "OPERATORID";
                public static final int MIN_LEN = 8;
                public static final int MAX_LEN = 18;
                public static final String PATTERN = "[a-zA-Z0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = false; // RECOMMENDED
                public static final String DEFAULT = "VPOSTELOP";
            }

            public static final class IBAN {

                private IBAN() {

                }

                public static final String NAME = "IBAN";
                public static final int LEN = 27;
                public static final String PATTERN = "[IT|SM]+[0-9]{2}+[A-Z]+[0-9]{" + (LEN - 5) + "}";
                public static final boolean MANDATORY = false; // CONDITIONAL
            }

            public static final class LANG {

                private LANG() {

                }

                public static final String NAME = "LANG";
                public static final int MIN_LEN = 2;
                public static final int MAX_LEN = 3;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = false; // OPTIONAL
            }

            public static final class OPTIONS {

                private OPTIONS() {

                }

                public static final String NAME = "OPTIONS";
                public static final String PATTERN = "[A-Za-z]*";
                public static final boolean MANDATORY = true;

                public static final String G = "G";
                public static final String H = "H";
                public static final String M = "M";
                public static final String N = "N";
            }

            public static final class LOCKCARD {

                private LOCKCARD() {

                }

                public static final String NAME = "LOCKCARD";
                public static final boolean MANDATORY = false; // OPTIONAL
            }

            public static final class EMAIL {

                private EMAIL() {

                }

                public static final String NAME = "EMAILCH";
                public static final int MIN_LEN = 7;
                public static final int MAX_LEN = 50;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = false; // RECOMMENDED
            }

            public static final class ORDDESCR {

                private ORDDESCR() {

                }

                public static final String NAME = "ORDDESCR";
                public static final int MIN_LEN = 1;
                public static final int MAX_LEN = 140;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = false; // OPTIONAL
            }

            public static final class OPDESCR {

                private OPDESCR() {

                }

                public static final String NAME = "OPDESCR";
                public static final int MIN_LEN = 1;
                public static final int MAX_LEN = 100;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = false; // OPTIONAL
            }

            public static final class USERID {

                private USERID() {

                }

                public static final String NAME = "USERID";
                public static final int MIN_LEN = 1;
                public static final int MAX_LEN = 255;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class NAME {

                private NAME() {

                }

                public static final String NAME = "NAME";
                public static final int MIN_LEN = 1;
                public static final int MAX_LEN = 40;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class SURNAME {

                private SURNAME() {

                }

                public static final String NAME = "SURNAME";
                public static final int MIN_LEN = 1;
                public static final int MAX_LEN = 40;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class TAXID {

                private TAXID() {

                }

                public static final String NAME = "TAXID";
                public static final int TI_LEN = 11;
                public static final int FC_LEN = 16;
                public static final String PATTERN = "^([A-Z0-9]{" + FC_LEN + "}|[0-9]{" + TI_LEN + "})";
                public static final boolean MANDATORY = true;
            }

            public static final class PRODUCTREF {

                private PRODUCTREF() {

                }

                public static final String NAME = "PRODUCTREF";
                public static final int MIN_LEN = 1;
                public static final int MAX_LEN = 15;
                public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class ANTIFRAUD {

                private ANTIFRAUD() {

                }

                public static final String NAME = "ANTIFRAUD";
                public static final String PATTERN = ".*";
                public static final boolean MANDATORY = true;
            }

            public static final class LINKCODE {

                private LINKCODE() {

                }

                public static final String NAME = "LINKCODE";
                public static final int LEN = 6;
                public static final String PATTERN = "[0-9]{" + LEN + "}";
                public static final boolean MANDATORY = true;
            }

            public static final class CVV {

                private CVV() {

                }

                public static final String NAME = "CVV";
                public static final int MIN_LEN = 3;
                public static final int MAX_LEN = 4;
                public static final String PATTERN = "[0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
                public static final boolean MANDATORY = true;
            }

        }

        public static final class AUTHORIZATION {

            private AUTHORIZATION() {

            }

            public static final String AUTHORIZATIONTYPE = "AUTHORIZATIONTYPE";
            public static final String PAYMENTTYPE = "PAYMENTTYPE";
            public static final String TRANSACTIONID = "TRANSACTIONID";
            public static final String NETWORK = "NETWORK";
            public static final String ORDERID = "ORDERID";
            public static final String TRANSACTIONAMOUNT = "TRANSACTIONAMOUNT";
            public static final String AUTHORIZEDAMOUNT = "AUTHORIZEDAMOUNT";
            public static final String CURRENCY = "CURRENCY";
            public static final String EXPONENT = "EXPONENT";
            public static final String ACCOUNTEDAMOUNT = "ACCOUNTEDAMOUNT";
            public static final String REFUNDEDAMOUNT = "REFUNDEDAMOUNT";
            public static final String TRANSACTIONRESULT = "TRANSACTIONRESULT";
            public static final String TIMESTAMP = "TIMESTAMP";
            public static final String AUTHORIZATIONNUMBER = "AUTHORIZATIONNUMBER";
            public static final String ACQUIRERBIN = "ACQUIRERBIN";
            public static final String MERCHANTID = "MERCHANTID";
            public static final String TRANSACTIONSTATUS = "TRANSACTIONSTATUS";
            public static final String RESPONSECODEISO = "RESPONSECODEISO";
            public static final String PANTAIL = "PANTAIL";
            public static final String PANEXPIRYDATE = "PANEXPIRYDATE";
            public static final String CARDTYPE = "CARDTYPE";

        }

        public static final class CONFIRMATION {

            private CONFIRMATION() {

            }

            public static final String AUTHORIZATIONUMBER = "AUTHNUMBER";
            public static final String TRANSACTIONID = "TRANSACTIONID";
            public static final String RESULT = "RESULT";
            public static final String TRANSACTIONTYPE = "TRANSACTIONTYPE";
            public static final String PANALIAS = "PANALIAS";
            public static final String PANALIASREV = "PANALIASREV";
            public static final String PANALIASEXPDATE = "PANALIASEXPDATE";
            public static final String PANALIASTAIL = "PANALIASTAIL";
            public static final String ACQUIRERBIN = "ACQUIRERBIN";
            public static final String MERCHANTID = "MERCHANTID";
            public static final String CARDTYPE = "CARDTYPE";

        }

    }

    public static final class ERRORS {

        private ERRORS() {

        }

        public static final String MALFORMED_REQUEST = "Malformed Request. Request fields are not qualified or rejected due to invalid syntax";
        public static final String INVALID_CHANNEL = "Channel is invalid. User not authorized";
        public static final String INVALID_LOCATION = "Location is invalid. User not authorized";
        public static final String INVALID_PID = "PaymentInstrumentId is unknown";

    }
}
