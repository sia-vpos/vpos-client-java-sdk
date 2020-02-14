package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPOSSimpleClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.Utils;
import it.reply.cof.utils.exception.COFException;

public class Application {
    public static final String BASE64TEMPLATE = "PGh0bWw+Cgo8Ym9keT4KICAgIDxzdHlsZT4KICAgICAgICBib2R5IHsKICAgICAgICAgICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKCdodHRwczovL2kuZ2lwaHkuY29tL21lZGlhLzNvRWpJNlNJSUhCZFJ4WEk0MC9naXBoeS53ZWJwJyk7CiAgICAgICAgICAgIGJhY2tncm91bmQtcmVwZWF0OiBuby1yZXBlYXQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtYXR0YWNobWVudDogZml4ZWQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtcG9zaXRpb246IGNlbnRlcjsKICAgICAgICB9CiAgICA8L3N0eWxlPgo8L2JvZHk+Cgo8L2h0bWw+";
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
    private static final String PARES = "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";
    private static final String PROXYNAME = "proxy-dr.reply.it";
    private static final Integer PROXYPORT = 8080;

    public static void main(String[] args) throws COFException {
        VPOSClient vposClient = new VPOSSimpleClient(URL_WEB_API, MAC_KEY_VPOS, API_RESULT_KEY);

        try {
          vposClient.verifyRequest(buildVerifyTest());

//            vposClient.start3DSAuth(buildAuth3DS());
//            vposClient.start3DSAuthStep2(buildAuth3DSStep2());
//            vposClient.getOrderStatus(buildOrderStatusTest());
//            vposClient.refundPayment(buildRefundTest());
            //vposClient.confirmTransaction(buildBookingTest());
//           injectHtmlTemplate(vposClient);
            System.out.println(vposClient.tokenize(SHOP_ID, URL_BACK, URL_DONE, URLMS, URL_REDIRECT));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void injectHtmlTemplate(VPOSClient client) throws COFException {
        client.injectHtmlTemplate(BASE64TEMPLATE, 5000);
    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId(Utils.generateRandomDigits(12));
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("I");
        paymentInfo.setAuthorMode("I");
        paymentInfo.addOption(PaymentInfo.OptionName.G);
        paymentInfo.addOption(PaymentInfo.OptionName.M);
        return paymentInfo;
    }


    private static RefundRequestDto buildRefundTest() {
        return new RefundRequestDto(SHOP_ID, "Giammaicol",
                "8032112928AT1ep4n2gzown54", "726527839399",
                "100", "978", "2", "jopokokok");
    }

    private static Auth3DSDto buildAuth3DS() {
        Auth3DSDto dto = new Auth3DSDto();
        dto.setMasterpass(false);
        dto.setOrderId(Utils.generateRandomDigits(12));
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
        dto.setInPerson("S");
        dto.setMerchantUrl("http://jnfjdshjfhjd.it");
        return dto;
    }

    private static Auth3DSStep2RequestDto buildAuth3DSStep2() {
        Auth3DSStep2RequestDto dto = new Auth3DSStep2RequestDto();
        dto.setOperatorId(OPERATOR_ID);
        dto.setShopId(SHOP_ID);
        dto.setPaRes(PARES);
        dto.setOriginalRefReqNum("20200213610815106958682563120538");
        return dto;
    }

    private static VerifyRequestDto buildVerifyTest() {
        VerifyRequestDto dto = new VerifyRequestDto();
        dto.setOriginalReqRefNum("20200213518369234510468968607811");
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
        return new OrderStatusRequestDto(SHOP_ID, "operator",
                "042221867378323197573301", null, null);
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
