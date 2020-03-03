package eu.sia.vpos.client;

import com.google.gson.Gson;
import eu.sia.vpos.client.impl.VPosClient;
import eu.sia.vpos.client.impl.VPosConfig;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;
import org.apache.http.util.ExceptionUtils;

import java.util.Random;

public class Test3DS {

    public static final String BASE64TEMPLATE = "PGh0bWw+Cgo8Ym9keT4KICAgIDxzdHlsZT4KICAgICAgICBib2R5IHsKICAgICAgICAgICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKCdodHRwczovL2kuZ2lwaHkuY29tL21lZGlhLzNvRWpJNlNJSUhCZFJ4WEk0MC9naXBoeS53ZWJwJyk7CiAgICAgICAgICAgIGJhY2tncm91bmQtcmVwZWF0OiBuby1yZXBlYXQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtYXR0YWNobWVudDogZml4ZWQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtcG9zaXRpb246IGNlbnRlcjsKICAgICAgICB9CiAgICA8L3N0eWxlPgo8L2JvZHk+Cgo8L2h0bWw+";
    private static final String SHOP_ID = "129281292800109";
    private static final String MAC_KEY_VPOS = "fU-9et-s-Sj8W---E8uhUDu9fEzqr8hH3L95s48r9nq-cq3cBXbp-tZsvGQU--t-nqmtaW-7x-7-C2PdcuFdbHuShQ-pYVWnr-4-";
    private static final String API_RESULT_KEY = "E-vmE-GHXmx73-Lfg24LztZ-7-yCyVsKn4QXphL5qzf-Kr-Cf-JWpZwZgaZRA5dR9V677xL4uCbc-Ce--8h2-tdrSu--QKjF-nZh";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";
    private static final String OPERATOR_ID = "John Doe";
    private static final String PARES = "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";
    private static final String PROXYNAME = "proxy-dr.reply.it";
    private static final Integer PROXYPORT = 8080;
    private static final String  jsonString = "{\"browserAcceptHeader\":\"1024\",\"browserIP\":\"1.12.123.255\",\"browserJavaEnabled\":\"true\",\"browserLanguage\":\"it\",\"browserColorDepth\":\"16\",\"browserScreenHeight\":\"100\",\"browserScreenWidth\":\"100\",\"browserTZ\":\"-60\",\"browserUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0\",\"addrMatch\":\"N\",\"chAccAgeInd\":\"04\",\"chAccChange\":\"20190211\",\"chAccChangeInd\":\"03\",\"chAccDate\":\"20190210\",\"chAccPwChange\":\"20190214\",\"chAccPwChangeInd\":\"04\",\"nbPurchaseAccount\":\"1000\",\"txnActivityDay\":\"100\",\"txnActivityYear\":\"100\",\"shipAddressUsage\":\"20181220\",\"shipAddressUsageInd\":\"03\",\"shipNameIndicator\":\"01\",\"billAddrCity\":\"billAddrCity\",\"billAddrCountry\":\"004\",\"billAddrLine1\":\"billAddrLine1\",\"billAddrLine2\":\"billAddrLine2\",\"billAddrLine3\":\"billAddrLine3\",\"billAddrPostCode\":\"billAddrPostCode\",\"billAddrState\":\"11\",\"homePhone\":\"39-321818198\",\"mobilePhone\":\"33-312\",\"shipAddrCity\":\"zio\",\"shipAddrCountry\":\"008\",\"shipAddrLine1\":\"shipAddrLine1\",\"shipAddrLine2\":\"shipAddrLine2\",\"shipAddrLine3\":\"shipAddrLine3\",\"shipAddrPostCode\":\"shipAddrPostCode\",\"shipAddrState\":\"222\",\"workPhone\":\"39-0321818198\",\"deliveryEmailAddress\":\"a-b@example.com\",\"deliveryTimeframe\":\"02\",\"preOrderDate\":\"20181220\",\"preOrderPurchaseInd\":\"01\",\"reorderItemsInd\":\"02\",\"shipIndicator\":\"01\"}";
    public static void main(String[] args) throws VPosClientException {
        VPosConfig config = new VPosConfig();
        config.setApiKey(API_RESULT_KEY);
        config.setShopID(SHOP_ID);
        config.setRedirectKey(MAC_KEY_VPOS);
        config.setAlgorithm( MacAlgorithms.HMAC_SHA_256);
        config.setUrl(URL_WEB_API);
        VPosClient client = new VPosClient(config);
        try {
            //client.threeDSAuthorize0(build3DSRequest());
            //System.out.println(client.buildHTMLRedirectFragment(buildPaymentTest()));
            //client.getOrderStatus(buildOrderStatusRequest("12345676912345649849"));
            //client.capture(buildCaptureRequest("12345676912345649938"));
            client.refund(buildRefundRequest("8032112928SL21gibxxw3ue54","12345676912345649938","50"));
        }catch (VPosClientException e){
            System.out.println(e.getExceptionMessage());
            e.printStackTrace();
        }

    }

    public static ThreeDSAuthorization0Request build3DSRequest(){
        ThreeDSAuthorization0Request request3DS0= new ThreeDSAuthorization0Request();
        request3DS0.setAmount("6600");
        request3DS0.setAccountingMode("I");
        request3DS0.setPan("5342230500001234");
        request3DS0.setExpDate("2112");
        request3DS0.setCvv2("100");
        request3DS0.setCurrency("978");

        request3DS0.setNetwork("02");
        request3DS0.setEmailCh("asdas@fgd.id");
        request3DS0.setOrderId("API232043111089");
        request3DS0.setOperatorId("John Doe");
        request3DS0.setNameCh("Mario");
        request3DS0.setNotifyUrl("https://atpostest.ssb.it/atpos/apibo/en/3ds-notification.html");
        request3DS0.setThreeDSMtdNotifyUrl("https://atpostest.ssb.it/atpos/apibo/en/3ds-notification.html");
        Gson g = new Gson();
        Data3DSJson data3DSJson = g.fromJson(jsonString, Data3DSJson.class);
        request3DS0.setThreeDSData(data3DSJson);
        request3DS0.setMerchantKey(API_RESULT_KEY);

        return request3DS0;
    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        Random rand = new Random();
        paymentInfo.setOrderId("12345676912345649"+rand.nextInt(1000));
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("D");
        paymentInfo.setAuthorMode("D");
        paymentInfo.addOption(PaymentInfo.OptionName.G);
        paymentInfo.addOption(PaymentInfo.OptionName.M);
        return paymentInfo;
    }

    private static OrderStatusRequest buildOrderStatusRequest(String orderId){
        return new OrderStatusRequest(SHOP_ID, "operator",
                orderId, null, null);
    }

    private static CaptureRequest buildCaptureRequest(String orderId){
        CaptureRequest req = new CaptureRequest();
        req.setAmount("500");
        req.setOperatorId("OPERATOR");
        req.setCurrency("978");
        req.setOrderId("12345676912345649938");
        req.setTransactionId("8032112928SL21gibxxw3ue54");
        return req;
    }

    private static RefundRequest buildRefundRequest(String transactionId, String orderId, String amount){
        RefundRequest req = new RefundRequest( "Giammaicol",
                transactionId, orderId,
                amount, "978", null, null);
        return req;
    }
}
