package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPOSSimpleClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.Utils;
import it.reply.cof.utils.exception.COFException;

public class Application {
    private static final String SHOP_ID = "129281292800109";
    private static final String PAN_ALIAS = "0000409500729966732";
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
        VPOSClient vposClient = new VPOSSimpleClient(URL_WEB_API, MAC_KEY_VPOS, API_RESULT_KEY);

        System.out.println(vposClient.getHtmlPaymentDocument(buildPaymentTest(), URL_REDIRECT));
        try {
//            vposClient.verifyPayment(buildVerifyTest());
//            vposClient.start3DSAuth(buildAuth3DSTest1());
//            vposClient.getOrderStatus(buildOrderStatusTest());
//            vposClient.refundPayment(buildRefundTest());
//            vposClient.confirmPayment(buildConfirmTest());
            vposClient.confirmTransaction(buildBookingTest());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId(Utils.generateRandomDigits().substring(0, 12));
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("I");
        paymentInfo.setAuthorMode("I");
        paymentInfo.addOption("GM");
        return paymentInfo;
    }


    private static RefundRequestDto buildRefundTest() {
        return new RefundRequestDto(SHOP_ID, "Giammaicol",
                "8032112928AT1ep4n2gzown54", "726527839399",
                "100", "978", "2", "jopokokok");
    }

    private static Auth3DSDto buildAuth3DSTest1() {
        Auth3DSDto dto = new Auth3DSDto();
        dto.setMasterpass(false);
        dto.setOrderId(Utils.generateRandomDigits().substring(0, 12));
        dto.setPan(PAN_ALIAS);
        dto.setExpDate("2112");
        dto.setAmount("2000");
        dto.setCurrency("978");
        dto.setCvv2("111");
        dto.setAccountingMode("D");
        dto.setNetwork("98");
        //dto.setTimestamp();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        return dto;
    }

    private static VerifyRequestDto buildVerifyTest() {
        VerifyRequestDto dto = new VerifyRequestDto();
        dto.setOriginalReqRefNum("20200128979407875088471785269188");
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        return dto;
    }

    private static ConfirmRequestDto buildConfirmTest() {
        ConfirmRequestDto dto = new ConfirmRequestDto();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        dto.setTransactionId("8032112928AT1i3e29q9u4yv4");
        dto.setOrderId("424882487593");
        dto.setAmount("200");
        dto.setCurrency("978");
        dto.setExponent("2");
        dto.setAccountingMode("I");
        dto.setCloseOrder("S");
        return dto;
    }

    private static OrderStatusRequestDto buildOrderStatusTest() {
        OrderStatusRequestDto dto = new OrderStatusRequestDto(SHOP_ID, "Giammaicol",
                "054829546191", null, null);
        return dto;
    }

    private static BookingRequestDto buildBookingTest() {
        BookingRequestDto dto = new BookingRequestDto();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        dto.setTransactionId("8032112928AT2156zcs1dkcb4");
        dto.setOrderId("969206344798");
        dto.setAmount("2000");
        dto.setCurrency("978");
        dto.setExponent("2");
        return dto;
    }

}
