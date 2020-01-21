package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPOSSimpleClient;
import it.reply.cof.utils.exception.COFException;

import java.util.HashMap;
import java.util.Map;

public class Tester {
    private static final String SHOP_ID = "129281292800104";
    private static final String PAN_ALIAS = "0000147162817302285";
    private static final String START_KEY = "CNCuDT5Vyws2v9t--RhDdtCwstaV2tqVeqfE-D8G-S-Ds--C-fFkSBxw-3wWBqBC--U9hwN-H-Mj4ZZHMr--YSHLdU5WKLx-cT-T";
    private static final String API_RESULT_KEY = "nS-zLHC35Pzu47m-AZtRejrd-xd2SsudbpKmmfGgD-9-dJjRQpm8pPJM84uEZgkgq-CfJ9n--sHNHjKx7--Aw-56jEqLVDe-aT-f";
    private static final String MAC_KEY_REDIRECT = "VnFBty7us-7QubjwHcjFaq4hb5-5n5-VdaymsXfqukzd3nhYhsLvuL5nFs-sCrJT--n9-J7zghhUYpFUw6q4KxCQ-XND6dV—abW";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/cardData?consumerId=3b350c34-d923-4552-91bf-67bc4f99da92";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";

    private static final String BASE64HTML = "PGh0bWw+CiAgICA8Ym9keT4KICAgICAgICAKICAgIDwvYm9keT4KPC9odG1sPg==";

    public static void main(String[] args) throws COFException {
        VPOSClient vposClient = new VPOSSimpleClient(URL_WEB_API, START_KEY, API_RESULT_KEY);
        Map<String, String> map = new HashMap<>();
        map.put("ORDERID","0oiujh6rd3tbhberwwww3g4ui777");
        map.put("SHOPID","129281292800104");
        map.put("AUTHNUMBER","322069");
        map.put("AMOUNT","10000");
        map.put("CURRENCY","978");
        map.put("TRANSACTIONID","8032112928SL2bmtp0bjts1d4");
        map.put("ACCOUNTINGMODE","D");
        map.put("AUTHORMODE","I");
        map.put("RESULT","00");
        map.put("TRANSACTIONTYPE","TT09");
        map.put("PANALIAS","0000921251277229326");
        map.put("PANALIASEXPDATE","2112");
        map.put("PANALIASTAIL","0031");
        map.put("MASKEDPAN","525590xxxxxx0031");
        map.put("PANTAIL","0031");
        map.put("PANEXPIRYDATE","2112");
        map.put("NETWORK","02");
        map.put("PANALIASREV", "");



        try {
            vposClient.verifyURL(map, "91865f3164c5b47764a9650c0be79496fad3980e801887974e8bef417412ab8b");
        }catch(Exception e){
            System.out.println("Thees ees: " + e.getMessage());
            throw new COFException(e.getMessage());
        }
    }
}
