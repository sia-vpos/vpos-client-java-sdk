package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPOSSimpleClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.exception.COFException;

public class Application {
    private static final String SHOP_ID = "129281292800104";
    private static final String PAN_ALIAS = "0000147162817302285";
    private static final String MAC_KEY_VPOS = "CNCuDT5Vyws2v9t--RhDdtCwstaV2tqVeqfE-D8G-S-Ds--C-fFkSBxw-3wWBqBC--U9hwN-H-Mj4ZZHMr--YSHLdU5WKLx-cT-T";
    private static final String API_RESULT_KEY = "nS-zLHC35Pzu47m-AZtRejrd-xd2SsudbpKmmfGgD-9-dJjRQpm8pPJM84uEZgkgq-CfJ9n--sHNHjKx7--Aw-56jEqLVDe-aT-f";
    private static final String MAC_KEY_REDIRECT = "VnFBty7us-7QubjwHcjFaq4hb5-5n5-VdaymsXfqukzd3nhYhsLvuL5nFs-sCrJT--n9-J7zghhUYpFUw6q4KxCQ-XND6dV—abW";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/cardData?consumerId=3b350c34-d923-4552-91bf-67bc4f99da92";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";

    private static final String BASE64HTML = "PGh0bWw+CiAgICA8Ym9keT4KICAgICAgICAKICAgIDwvYm9keT4KPC9odG1sPg==";

    public static void main(String[] args) throws COFException {
        VPOSClient vposClient = new VPOSSimpleClient(MAC_KEY_VPOS, API_RESULT_KEY);


        System.out.println(vposClient.getHtmlPaymentDocument(buildPaymentTest(), URL_REDIRECT));
    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId("s564c564c6as54as654c65as4");
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("D");
        paymentInfo.setAuthorMode("I");
        paymentInfo.addOption("GM");
        return paymentInfo;
    }
}
