package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPosClientWithProxy;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.dto.response.Auth3DSResponseDto;
import it.reply.cof.dto.response.ConfirmationResponseDto;
import it.reply.cof.utils.Utils;
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

    private static final String PROXYNAME = "proxy-dr.reply.it";
    private static final Integer PROXYPORT = 8080;

    public static void main(String[] args) throws COFException {
        VPOSClient vposClient = new VPosClientWithProxy(URL_WEB_API, MAC_KEY_VPOS, API_RESULT_KEY, PROXYNAME, PROXYPORT);
        System.out.println(vposClient.getHtmlPaymentDocument(buildPaymentTest(), URL_REDIRECT));

        try {
            //vposClient.verifyPayment(buildVerifyTest());
            //vposClient.start3DSAuth(buildAuth3DSTest1());
            //vposClient.getOrderStatus(buildOrderStatusTest());
           //vposClient.confirmPayment(buildConfirmTest());
            //vposClient.refundPayment(buildRefundTest());


        } catch (Exception e) {

            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId("416454645646b");
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("D");
        paymentInfo.setAuthorMode("I");
        paymentInfo.addOption("GM");
        return paymentInfo;
    }


    private static RefundRequestDto buildRefundTest() {
        return new RefundRequestDto(SHOP_ID, OPERATOR_ID,
                "8032112928SL1nb790z4qxrq4", "416454645646b",
                "-9000", "978", "2", "jopokokok");
    }

    private static Auth3DSDto buildAuth3DSTest1() {
        Auth3DSDto dto = new Auth3DSDto();
        dto.setMasterpass(false);
        dto.setOrderId(Utils.generateRandomDigits());
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
        dto.setOriginalReqRefNum("20200122076459634800948411102441");
        dto.setShopId(SHOP_ID);
        dto.setOperatorId(OPERATOR_ID);
        return dto;
    }

    private static ConfirmRequestDto buildConfirmTest() {
        ConfirmRequestDto dto = new ConfirmRequestDto();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId(OPERATOR_ID);
        dto.setTransactionId("8032112928SL1nb790z4qxrq4");
        dto.setOrderId("416454645646b");
        dto.setAmount("1000");
        dto.setCurrency("978");
        dto.setExponent("2");
        dto.setAccountingMode("D");
        dto.setCloseOrder("S");

        return dto;
    }

    private static OrderStatusRequestDto buildOrderStatusTest() {
        OrderStatusRequestDto dto = new OrderStatusRequestDto(SHOP_ID, OPERATOR_ID,
                "416454645646b", "", "");
        dto.setProductRef(null);
        return dto;
    }


}
