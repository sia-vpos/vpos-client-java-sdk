package eu.sia.vpos.client;

import com.google.gson.Gson;
import eu.sia.vpos.client.impl.VPosClient;
import eu.sia.vpos.client.impl.VPosConfig;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;

import java.util.Random;

public class Test3DS {

    private static final String SHOP_ID = "129281292800013";
    private static final String MAC_KEY_VPOS = "qJS-dSZx7DG-dyetrvTyS-a4CGLkBkCxY-n-SuCb-sdUbhgv5Ghea7tuXap-m4cC-RM-q-a8JGRPA-zV-dSLwnpGs4VkkrNU-Jqz";
    private static final String API_RESULT_KEY = "dnC8ybnbPaBSNYHsN5vq-pcaf5QXV2YHpFStxjGfY3wftC-7-PZkL5dbhP--em-DV24-YeCKMKr-ENZ-nE3JHMvqeyYDKJ3wK8b2";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";
    private static final String OPERATOR_ID = "John Doe";
    private static final String PROXYNAME = "proxy-dr.reply.it";
    private static final Integer PROXYPORT = 8080;
    private static final String jsonString = "{\"browserAcceptHeader\":\"1024\",\"browserIP\":\"1.12.123.255\",\"browserJavaEnabled\":\"true\",\"browserLanguage\":\"it\",\"browserColorDepth\":\"16\",\"browserScreenHeight\":\"100\",\"browserScreenWidth\":\"100\",\"browserTZ\":\"-60\",\"browserUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 firefox/47.0\",\"addrmatch\":\"n\",\"chaccageind\":\"04\",\"chaccchange\":\"20190211\",\"chaccchangeind\":\"03\",\"chaccdate\":\"20190210\",\"chaccpwchange\":\"20190214\",\"chaccpwchangeind\":\"04\",\"nbpurchaseaccount\":\"1000\",\"txnactivityday\":\"100\",\"txnactivityyear\":\"100\",\"shipaddressusage\":\"20181220\",\"shipaddressusageind\":\"03\",\"shipnameindicator\":\"01\",\"billaddrcity\":\"billaddrcity\",\"billaddrcountry\":\"004\",\"billaddrline1\":\"billaddrline1\",\"billaddrline2\":\"billaddrline2\",\"billaddrline3\":\"billaddrline3\",\"billaddrpostcode\":\"billaddrpostcode\",\"billaddrstate\":\"11\",\"homephone\":\"39-321818198\",\"mobilephone\":\"33-312\",\"shipaddrcity\":\"zio\",\"shipaddrcountry\":\"008\",\"shipaddrline1\":\"shipaddrline1\",\"shipaddrline2\":\"shipaddrline2\",\"shipaddrline3\":\"shipaddrline3\",\"shipaddrpostcode\":\"shipaddrpostcode\",\"shipaddrstate\":\"222\",\"workphone\":\"39-0321818198\",\"deliveryemailaddress\":\"a-b@example.com\",\"deliveryTimeframe\":\"02\",\"preOrderDate\":\"20181220\",\"preOrderPurchaseInd\":\"01\",\"reorderItemsInd\":\"02\",\"shipIndicator\":\"01\"}";

    public static void main(String[] args) {
        VPosConfig config = new VPosConfig();
        config.setApiKey(API_RESULT_KEY);
        config.setShopID(SHOP_ID);
        config.setRedirectKey(MAC_KEY_VPOS);

        config.setAlgorithm("HMAC_SHA_256");
        config.setApiUrl(URL_WEB_API);
        config.setRedirectUrl("https://atpostest.ssb.it/atpos/pagamenti/main");
        //config.setProxyHost("proxy-dr.reply.it");
        //config.setProxyPort(8080);

        VPosClient client = null;
        try {
            client = new VPosClient(config);


            //client.threeDSAuthorize0(build3DSRequest());
            client.authorize(buildAuthorizationRequest());
            //System.out.println(client.buildHTMLRedirectFragment(buildPaymentTest()));
            //client.getOrderStatus(buildOrderStatusRequest("12345676912345649849"));
            //client.capture(buildCaptureRequest("12345676912345649938"));
            //client.refund(buildRefundRequest("8032112928SL21gibxxw3ue54", "12345676912345649938", "50"));
            //boolean b=client.verifyMAC("http://localhost:8080/payment-gateway/vpos/tokenize?ORDERID=12345676912345649229&SHOPID=129281292800109&AUTHNUMBER=NULL&AMOUNT=10000&CURRENCY=978&TRANSACTIONID=8032112928SL11m99f7dkliz4&ACCOUNTINGMODE=D&AUTHORMODE=D&RESULT=00&TRANSACTIONTYPE=TT01&PANTAIL=0027&PANEXPIRYDATE=2112&NETWORK=01&MAC=97561492b40f189ea617cb992cf8a8fab825674a45ccb45407257442394bc39e");
            //System.out.println(b);
        } catch (VPosClientException e) {
            System.out.println(e.getExceptionMessage());
        }

    }

    public static ThreeDSAuthorization0Request build3DSRequest() {
        ThreeDSAuthorization0Request request3DS0 = new ThreeDSAuthorization0Request();
        request3DS0.setAmount("6600");
        request3DS0.setAccountingMode("I");
        request3DS0.setPan("4118830900940017");
        request3DS0.setExpDate("2112");
        request3DS0.setCvv2("111");
        request3DS0.setCurrency("978");

        request3DS0.setNetwork("93");
        request3DS0.setEmailCh("asdas@fgd.id");
        request3DS0.setOrderId("API232043111428");
        request3DS0.setOperatorId("John Doe");
        request3DS0.setExponent("02");
        //request3DS0.setNameCh("Mario");
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
        paymentInfo.setOrderId("12345676912345649" + rand.nextInt(1000));
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("D");
        paymentInfo.setAuthorMode("I");
        //paymentInfo.addOption(PaymentInfo.OptionName.G);
        paymentInfo.addOption(PaymentInfo.OptionName.M);
        return paymentInfo;
    }

    private static OrderStatusRequest buildOrderStatusRequest(String orderId) {
        return new OrderStatusRequest(SHOP_ID, "operator",
                orderId, null, null);
    }

    private static CaptureRequest buildCaptureRequest(String orderId) {
        CaptureRequest req = new CaptureRequest();
        req.setAmount("500");
        req.setOperatorId("OPERATOR");
        req.setCurrency("978");
        req.setOrderId("12345676912345649938");
        req.setTransactionId("8032112928SL21gibxxw3ue54");
        return req;
    }

    private static RefundRequest buildRefundRequest(String transactionId, String orderId, String amount) {
        RefundRequest req = new RefundRequest("Giammaicol",
                transactionId, orderId,
                amount, "978", null, null);
        return req;
    }

    private static AuthorizationRequest buildAuthorizationRequest(){
        AuthorizationRequest req = new AuthorizationRequest();
        Random rand = new Random();

        req.setOrderId("12345676912345649" + rand.nextInt(1000));
        req.setOperatorId("OPERATOR");
        req.setPan("4598250000000027");
        req.setCvv2("111");
        req.setAmount("6000");
        req.setExpDate("2112");
        req.setCurrency("978");
        req.setAccountingMode("I");
        req.setNetwork("93");
        req.setEmailCh("dsdsd@gmail.it");
        return req;
    }
}
