package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPOSSimpleClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.exception.COFException;

public class Application {
    private static final String SHOP_ID = "129281292800109";
    private static final String PAN_ALIAS = "0000847379064699692";
    private static final String MAC_KEY_VPOS = "fU-9et-s-Sj8W---E8uhUDu9fEzqr8hH3L95s48r9nq-cq3cBXbp-tZsvGQU--t-nqmtaW-7x-7-C2PdcuFdbHuShQ-pYVWnr-4-";
    private static final String API_RESULT_KEY = "E-vmE-GHXmx73-Lfg24LztZ-7-yCyVsKn4QXphL5qzf-Kr-Cf-JWpZwZgaZRA5dR9V677xL4uCbc-Ce--8h2-tdrSu--QKjF-nZh";
    private static final String MAC_KEY_REDIRECT = "VnFBty7us-7QubjwHcjFaq4hb5-5n5-VdaymsXfqukzd3nhYhsLvuL5nFs-sCrJT--n9-J7zghhUYpFUw6q4KxCQ-XND6dV—abW";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/cardData?consumerId=3b350c34-d923-4552-91bf-67bc4f99da92";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";
    private static final String OPERATOR_ID = "John Doe";

    private static final String BASE64HTML = "PGh0bWw+CiAgICA8Ym9keT4KICAgICAgICAKICAgIDwvYm9keT4KPC9odG1sPg==";

    public static void main(String[] args) throws COFException {
        VPOSClient vposClient = new VPOSSimpleClient(URL_WEB_API, MAC_KEY_VPOS, API_RESULT_KEY);

        System.out.println(vposClient.getHtmlPaymentDocument(buildPaymentTest(), URL_REDIRECT));

        try {

            vposClient.confirmPayment(buildConfirmTest());
        } catch (Exception e) {

            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId("416454645646");
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("D");
        paymentInfo.setAuthorMode("D");
        paymentInfo.addOption("GM");
        return paymentInfo;
    }


   private static RefundRequestDto buildRefundTest(){
       return new RefundRequestDto("129281292800104", "a caso",
               "8032112928SL11dk36s2gdj34", "ygaedwwdauigougui777",
               "10", "978", "2", "jopokokok");

    }

    private static Auth3DSDto buildAuth3DSTest1() {
        Auth3DSDto dto = new Auth3DSDto();
        dto.setMasterpass(false);
        dto.setOrderId("dfbdfbdfbdbdfb");
        dto.setPan(PAN_ALIAS);
        dto.setExpDate("2102");
        dto.setAmount("2000");
        dto.setCurrency("978");
        dto.setCvv2("111");
        dto.setAccountingMode("D");
        dto.setNetwork("98");
        dto.setShopId(SHOP_ID);
        dto.setOperatorId(OPERATOR_ID);
        return dto;
    }

    private static VerifyRequestDto buildVerifyTest() {
        VerifyRequestDto dto = new VerifyRequestDto();
        dto.setOriginalReqRefNum("20200121747989345505071695860709");
        dto.setShopId(SHOP_ID);
        dto.setOperatorId(OPERATOR_ID);
        return dto;
    }

    private static ConfirmRequestDto buildConfirmTest() {
        ConfirmRequestDto dto = new ConfirmRequestDto();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId(OPERATOR_ID);
        dto.setTransactionId("8032112928SL13com5thq72y4");
        dto.setOrderId("416454645646");
        dto.setAmount("10000");
        dto.setCurrency("978");
        dto.setExponent("2");
        dto.setAccountingMode("D");
        dto.setCloseOrder("S");

        return dto;
    }

    private static OrderStatusRequestDto buildOrderStatusTest() {
        return new OrderStatusRequestDto(SHOP_ID, OPERATOR_ID,
                "0oiujh6rd3tbhberwwww3g4ui777", "dedetv435t4ff4f", "");

    }


}
