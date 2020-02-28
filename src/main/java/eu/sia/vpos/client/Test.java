package eu.sia.vpos.client;

import eu.sia.vpos.client.impl.VPOSClient;
import eu.sia.vpos.client.request.PaymentInfo;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.utils.Utils;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.dto.request.*;
import eu.sia.vpos.client.impl.old.VPOSSimpleClient;
import it.reply.cof.dto.request.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Test {
    public static final String BASE64TEMPLATE = "PGh0bWw+Cgo8Ym9keT4KICAgIDxzdHlsZT4KICAgICAgICBib2R5IHsKICAgICAgICAgICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKCdodHRwczovL2kuZ2lwaHkuY29tL21lZGlhLzNvRWpJNlNJSUhCZFJ4WEk0MC9naXBoeS53ZWJwJyk7CiAgICAgICAgICAgIGJhY2tncm91bmQtcmVwZWF0OiBuby1yZXBlYXQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtYXR0YWNobWVudDogZml4ZWQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtcG9zaXRpb246IGNlbnRlcjsKICAgICAgICB9CiAgICA8L3N0eWxlPgo8L2JvZHk+Cgo8L2h0bWw+";
    private static final String SHOP_ID = "129281292800109";
    private static final String MAC_KEY_VPOS = "fU-9et-s-Sj8W---E8uhUDu9fEzqr8hH3L95s48r9nq-cq3cBXbp-tZsvGQU--t-nqmtaW-7x-7-C2PdcuFdbHuShQ-pYVWnr-4-";
    private static final String API_RESULT_KEY = "E-vmE-GHXmx73-Lfg24LztZ-7-yCyVsKn4QXphL5qzf-Kr-Cf-JWpZwZgaZRA5dR9V677xL4uCbc-Ce--8h2-tdrSu--QKjF-nZh";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/cardData?consumerId=3b350c34-d923-4552-91bf-67bc4f99da92";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";
    private static final String OPERATOR_ID = "John Doe";
    private static final String PARES = "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";
    private static final String PROXYNAME = "proxy-dr.reply.it";
    private static final Integer PROXYPORT = 8080;

    public static void main(String[] args) throws VPosClientException {
        VPOSClient vposClient = new VPOSSimpleClient(URL_WEB_API, MAC_KEY_VPOS, API_RESULT_KEY);

        try {
          //vposClient.verifyRequest(buildVerifyTest());

//            vposClient.start3DSAuth(buildAuth3DS());
//            vposClient.start3DSAuthStep2(buildAuth3DSStep2());
//            vposClient.getOrderStatus(buildOrderStatusTest());
           // vposClient.refundPayment(buildRefundTest());
//            vposClient.confirmTransaction(buildBookingTest());
//            injectHtmlTemplate(vposClient);
//            System.out.println(vposClient.tokenize(SHOP_ID, URL_BACK, URL_DONE, URLMS, URL_REDIRECT));
//            System.out.println(vposClient.getHtmlPaymentDocument(buildPaymentTest(), URL_REDIRECT));
            //vposClient.confirmTransaction(buildBookingTest());
//           injectHtmlTemplate(vposClient);
            System.out.println(URLEncoder.encode("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", StandardCharsets.UTF_8.toString()));
            //System.out.println(vposClient.tokenize(SHOP_ID, URL_BACK, URL_DONE, URLMS, URL_REDIRECT));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void injectHtmlTemplate(VPOSClient client) throws VPosClientException {
        client.injectHtmlTemplate(BASE64TEMPLATE, 5000);
    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId("123456789123456789");
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

    private static RefundRequest buildRefundTest() {
        return new RefundRequest(SHOP_ID, "Giammaicol",
                "8032112928AT11s1tn027bbh4", "083300549215",
                "100", "978", "2", "test");
    }

    private static Auth3DSDto buildAuth3DS() {
        Auth3DSDto dto = new Auth3DSDto();
        dto.setMasterpass(false);
        dto.setOrderId(Utils.generateRandomDigits(24));
        dto.setPan("5255900260000031");
        dto.setExpDate("2112");
        dto.setAmount("2000");
        dto.setCurrency("978");
        dto.setCvv2("111");
        dto.setAccountingMode("I");
        dto.setNetwork("02");
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
        dto.setOrderId("956773226298");
        dto.setAmount("200");
        dto.setCurrency("978");
        dto.setExponent("2");
        dto.setAccountingMode("I");
        dto.setCloseOrder("S");
        return dto;
    }

    private static OrderStatusRequest buildOrderStatusTest() {
        return new OrderStatusRequest(SHOP_ID, "operator",
                "042221867378323197573301", null, null);
    }

    private static CaptureRequest buildBookingTest() {
        CaptureRequest dto = new CaptureRequest();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("John Doe");
        dto.setTransactionId("8032112928AT11jxrxirc90x4");
        dto.setOrderId("956773226298");
        dto.setAmount("2000");
        dto.setCurrency("978");
        dto.setExponent("2");
        return dto;
    }

}
