package it.reply.cof.utils.constants;

public final class Operations {

    private Operations() {

    }

    public static final class PARAMETERS {

        public static final String OPERATION = "OPERATION";
        public static final String AUTHORIZATION3DSSTEP1 = "AUTHORIZATION3DSSTEP1";
        public static final String AUTHORIZATION3DSSTEP2 = "AUTHORIZATION3DSSTEP2";
        public static final String IBANAUTHORIZATION = "IBANAUTHORIZATION";
        public static final String ACCOUNTING = "ACCOUNTING";
        public static final String REFUND = "REFUND";
        public static final String ORDERSTATUS = "ORDERSTATUS";
        public static final String VERIFY = "VERIFY";
        public static final String DEFERREDREQUEST = "DEFERREDREQUEST";
        public static final String CONFIRM = "CONFIRM";
        public static final String TIMESTAMP = "TIMESTAMP";
        public static final String PARES = "PARES";

        private PARAMETERS() {

        }

        public static final class CREATEPANALIAS {
            public static final String NAME = "CREATEPANALIAS";
            public static final String PATTERN = "S";

            private CREATEPANALIAS(){

            }

        }

        public static final class INPERSON {
            public static final String NAME = "INPERSON";
            public static final String PATTERN = "([S]|[N]){1}";

            private INPERSON(){

            }
        }

        public static final class SERVICE {
           public static final String NAME = "SERVICE";
           public static final String PATTERN = "[SV47]{4}";

           private SERVICE(){

            }
        }


        public static final class MERCHANTURL {
            public static final String NAME ="MERCHANTURL";
            public static final String PATTERN = "[A-Za-z0-9_\\-/:. ]";

            private MERCHANTURL(){

            }
        }

        public static final class REQREFNUM {
            public static final String NAME = "REQREFNUM";
            public static final int LEN1 = 8;
            public static final int LEN2 = 24;
            public static final String PATTERN = "[20[0-9][0-9](0[1-9]|1[0-2])(0[1-9]|2[0-9]|3[0-1])]{" +
                    LEN1 + "}" + "\\d{" +LEN2 + "}";

            private REQREFNUM(){

            }
        }


        public static final class USRAUTHFLAG {
            public static final String NAME = " USRAUTHFLAG";
            public static final String PATTERN = "[0-2]{1}";

            private USRAUTHFLAG() {

            }
        }

        //pattern da controllare per XID e CAVV
        public static final class XID {
            public static final String NAME = "XID";
            public static final String PATTERN = "{40}";

            private XID(){

            }
        }

        public static final class CAVV {
            public static final String NAME = "CAVV";
            public static final String PATTERN = "{40}";

            private CAVV(){

            }
        }

        public static final class ECI {
            public static final String NAME = "ECI";
            public static final String PATTERN = "([01]|[02]|[05]|[07]){2}";

            private ECI(){

            }
        }


        public static final class CLOSEORDER {
            public static final String NAME = "CLOSEORDER";
        }

        public static final class AMOUNT {

            public static final String NAME = "AMOUNT";
            public static final int MIN_LEN = 2;
            public static final int MAX_LEN = 8;
            public static final String PATTERN = "[0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private AMOUNT() {

            }
        }

        public static final class ACQUIRER {
            public static final String NAME = "ACQUIRER";
            public static final String PATTERN = "[A-Za-z0-9]{5}";

            private ACQUIRER(){

            }
        }

        public static final class IPADDRESS {
            public static final String NAME = "IPADDRESS";
            public static final String PATTERN = "^(?=.*[^\\.]$)((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.?){4}$";

            private IPADDRESS(){

            }
        }

        public static final class PP_AUTHENTICATEMETHOD {
           public static final String NAME = "PP_AUTHENTICATEMETHOD";
           public static final String PATTERN = "([MERCHANT ONLY]|[3DS]|[NO AUTHENTICATION]){3,20}";

           private PP_AUTHENTICATEMETHOD(){

           }
        }

        public static final class PP_CARDENROLLMETHOD {
            public static final String NAME = "PP_CARDENROLLMETHOD";
            public static final String PATTERN = "([Manual]|[Direct Provisioned]|[3DS Manual]|[NFC Tap]){6,20}";

            private PP_CARDENROLLMETHOD(){

            }

        }

        public static final class SCENROLLSTATUS {
            public static final String NAME = "SCENROLLSTATUS";
            public static final String PATTERN = "([Y]|[N]|[U]){1}";

            private SCENROLLSTATUS(){

            }

        }



        public static final class CURRENCY {

            public static final String NAME = "CURRENCY";
            public static final int LEN = 3;
            public static final String PATTERN = "[0-9]{" + LEN + "}";
            public static final String EURO = "978";
            public static final boolean MANDATORY = true;

            private CURRENCY() {

            }

        }

        public static final class PARESSTATUS {
            public static final String NAME = "PARESSTATUS";
            public static final String PATTERN = "([Y]|[N]|[A]|[U]){1}";
        }

        public static final class SIGNATUREVERIFICATION {
            public static final String NAME = "SIGNATUREVERIFICATION";
            public static final int LEN = 1;
            public static final String PATTERN = "([Y]|[N]){" + LEN +  "}";

            private SIGNATUREVERIFICATION(){

            }
        }

        public static final class ORIGINALREQREFNUM {
            public static final String NAME ="ORIGINALREQREFNUM";
            public static final String PATTERN = "[20[0-9][0-9](0[1-9]|1[0-2])(0[1-9]|2[0-9]|3[0-1])]{8}\\d{24}";

            private ORIGINALREQREFNUM() {


            }
        }



        public static final class EXPONENT {

            public static final String NAME = "EXPONENT";
            public static final int LEN = 1;
            public static final String PATTERN = "[0-9]{" + LEN + "}";
            public static final boolean MANDATORY = false; // CONDITIONAL
            public static final String EURO = "2";
            public static final int DEFAULT = 2;

            private EXPONENT() {

            }

        } // Only if Currency is different from 978 (Euro)


        public static final class COMMIS{
            public static final String NAME = "COMMIS";
        }

        public static final class VSID{
            public static final String NAME = "VSID";
        }


        public static final class REMAININGDURATION{
            public static final String NAME = "REMAININGDURATION";
        }


        public static final class BP_POSTEPAY{
            public static final String NAME = "BP_POSTEPAY";
        }


        public static final class BP_CARDS{
            public static final String NAME = "BP_CARDS";
        }


        public static final class PHONENUMBER{
            public static final String NAME = "PHONENUMBER";
        }


        public static final class CAUSATION{
            public static final String NAME = "CAUSATION";
        }


        public static final class USER{
            public static final String NAME = "USER";
        }


        public static final class DATA3DS{
            public static final String NAME = "3DSDATA";
        }


        public static final class ORDERID {

            public static final String NAME = "ORDERID";
            public static final int MIN_LEN = 1;
            public static final int MAX_LEN = 50;
            public static final String PATTERN = "[a-zA-Z0-9\\\\\\-\\_]{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private ORDERID() {

            }
        }

        public static final class TRANSACTIONID {

            public static final String NAME = "TRANSACTIONID";
            public static final int LEN = 25;

            private TRANSACTIONID() {

            }
            /*
             * public static final int LEN = 15; public static final String PATTERN =
             * "[0-9]{" + LEN + "}"; public static final boolean MANDATORY = true;
             */
        }

        public static final class SHOPID {

            public static final String NAME = "SHOPID";
            public static final int LEN = 15;
            public static final String PATTERN = "[0-9]{" + LEN + "}";
            public static final boolean MANDATORY = true;

            private SHOPID() {

            }
        }

        public static final class SHORTID {

            public static final String NAME = "SHORTID";
            public static final int LEN = 2;
            public static final String PATTERN = "[0-9]{" + LEN + "}";
            public static final boolean MANDATORY = true;

            private SHORTID() {

            }
        }

        public static final class URLBACK {

            public static final String NAME = "URLBACK";
            public static final int MIN_LEN = 10;
            public static final int MAX_LEN = 254;
            public static final String PATTERN = "^https?://.{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private URLBACK() {

            }
        }

        public static final class URLDONE {

            public static final String NAME = "URLDONE";
            public static final int MIN_LEN = 10;
            public static final int MAX_LEN = 254;
            public static final String PATTERN = "^https?://.{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private URLDONE() {

            }
        }

        public static final class URLMS {

            public static final String NAME = "URLMS";
            public static final int MIN_LEN = 10;
            public static final int MAX_LEN = 400;
            public static final String PATTERN = "^https?://.{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = false; // OPTIONAL

            private URLMS() {

            }
        }

        public static final class ACCOUNTINGMODE {

            public static final String NAME = "ACCOUNTINGMODE";
            public static final int LEN = 1;
            public static final String PATTERN = "[DI]{" + LEN + "}";
            public static final boolean MANDATORY = true;

            private ACCOUNTINGMODE() {

            }
        }

        public static final class MAC {

            public static final String NAME = "MAC";
            public static final int MIN_LEN = 32;
            public static final int MAX_LEN = 32;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private MAC() {

            }
        }

        public static final class NETWORK {

            public static final String NAME = "NETWORK";
            public static final int LEN = 2;
            public static final String PATTERN = "[0-9]{" + LEN + "}";
            public static final boolean MANDATORY = false; // CONDITIONAL

            private NETWORK() {

            }
        }

        public static final class PAN {

            public static final String NAME = "PAN";
            public static final int MIN_LEN = 10;
            public static final int MAX_LEN = 18;
            public static final int PANALIAS_LEN = 19;
            public static final String PATTERNGENERIC = "^[0-9]{" + MIN_LEN + "," + PANALIAS_LEN + "}";
            public static final String PATTERNTOKENCARD = "^[0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final String PATTERNPAYPALPANALIAS = "^[0-9]{" + PANALIAS_LEN + "}";
            public static final boolean MANDATORY = false; // CONDITIONAL

            private PAN() {

            }
        }

        public static final class EXPDATE {

            public static final String NAME = "EXPDATE";
            public static final String PATTERN = "^[0-9]{2}(0[0-9]|1[0-2])";
            public static final boolean MANDATORY = false; // CONDITIONAL

            private EXPDATE() {

            }
        }

        public static final class OPERATORID {

            public static final String NAME = "OPERATORID";
            public static final int MIN_LEN = 8;
            public static final int MAX_LEN = 18;
            public static final String PATTERN = "[a-zA-Z0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = false; // RECOMMENDED
            public static final String DEFAULT = "VPOSTELOP";

            private OPERATORID() {

            }
        }

        public static final class IBAN {

            public static final String NAME = "IBAN";
            public static final int LEN = 27;
            public static final String PATTERN = "[IT|SM]+[0-9]{2}+[A-Z]+[0-9]{" + (LEN - 5) + "}";
            public static final boolean MANDATORY = false; // CONDITIONAL

            private IBAN() {

            }
        }

        public static final class LANG {

            public static final String NAME = "LANG";
            public static final int MIN_LEN = 2;
            public static final int MAX_LEN = 3;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = false; // OPTIONAL

            private LANG() {

            }
        }

        public static final class OPTIONS {

            public static final String NAME = "OPTIONS";
            public static final String PATTERN = "[A-Za-z]*";
            public static final boolean MANDATORY = true;
            public static final String G = "G";
            public static final String H = "H";
            public static final String M = "M";
            public static final String N = "N";

            private OPTIONS() {

            }
        }

        public static final class LOCKCARD {

            public static final String NAME = "LOCKCARD";
            public static final boolean MANDATORY = false; // OPTIONAL

            private LOCKCARD() {

            }
        }

        public static final class EMAIL {

            public static final String NAMECH = "EMAILCH";
            public static final String NAME = "EMAIL";
            public static final String SHOPNAME = "SHOPEMAIL";
            public static final int MIN_LEN = 7;
            public static final int MAX_LEN = 50;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = false; // RECOMMENDED

            private EMAIL() {

            }
        }

        public static final class ORDDESCR {

            public static final String NAME = "ORDDESCR";
            public static final int MIN_LEN = 1;
            public static final int MAX_LEN = 140;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = false; // OPTIONAL

            private ORDDESCR() {

            }
        }

        public static final class OPDESCR {

            public static final String NAME = "OPDESCR";
            public static final int MIN_LEN = 1;
            public static final int MAX_LEN = 100;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = false; // OPTIONAL

            private OPDESCR() {

            }
        }

        public static final class USERID {

            public static final String NAME = "USERID";
            public static final int MIN_LEN = 1;
            public static final int MAX_LEN = 255;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private USERID() {

            }
        }

        public static final class NAME {

            public static final String NAME = "NAME";
            public static final int MIN_LEN = 1;
            public static final int MAX_LEN = 40;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private NAME() {

            }
        }

        public static final class SURNAME {

            public static final String NAME = "SURNAME";
            public static final int MIN_LEN = 1;
            public static final int MAX_LEN = 40;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private SURNAME() {

            }
        }

        public static final class TAXID {

            public static final String NAME = "TAXID";
            public static final int TI_LEN = 11;
            public static final int FC_LEN = 16;
            public static final String PATTERN = "^([A-Z0-9]{" + FC_LEN + "}|[0-9]{" + TI_LEN + "})";
            public static final boolean MANDATORY = true;

            private TAXID() {

            }
        }

        public static final class PRODUCTREF {

            public static final String NAME = "PRODUCTREF";
            public static final int MIN_LEN = 1;
            public static final int MAX_LEN = 15;
            public static final String PATTERN = ".{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private PRODUCTREF() {

            }
        }

        public static final class ANTIFRAUD {

            public static final String NAME = "ANTIFRAUD";
            public static final String PATTERN = ".*";
            public static final boolean MANDATORY = true;

            private ANTIFRAUD() {

            }
        }

        public static final class LINKCODE {

            public static final String NAME = "LINKCODE";
            public static final int LEN = 6;
            public static final String PATTERN = "[0-9]{" + LEN + "}";
            public static final boolean MANDATORY = true;

            private LINKCODE() {

            }
        }

        public static final class CVV {

            public static final String NAME = "CVV";
            public static final int MIN_LEN = 3;
            public static final int MAX_LEN = 4;
            public static final String PATTERN = "[0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private CVV() {

            }
        }

        public static final class CVV2 {

            public static final String NAME = "CVV2";
            public static final int MIN_LEN = 3;
            public static final int MAX_LEN = 4;
            public static final String PATTERN = "[0-9]{" + MIN_LEN + "," + MAX_LEN + "}";
            public static final boolean MANDATORY = true;

            private CVV2() {

            }
        }

    }

    public static final class AUTHORIZATION {

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

        private AUTHORIZATION() {

        }

    }

    public static final class CONFIRMATION {

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

        private CONFIRMATION() {

        }

    }

}
