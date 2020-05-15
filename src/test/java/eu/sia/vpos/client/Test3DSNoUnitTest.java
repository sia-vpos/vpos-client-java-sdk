package eu.sia.vpos.client;

import com.google.gson.Gson;
import eu.sia.vpos.client.impl.VPosClient;
import eu.sia.vpos.client.impl.VPosConfig;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.response.CaptureResponse;
import eu.sia.vpos.client.response.OrderStatusResponse;
import eu.sia.vpos.client.response.RefundResponse;
import eu.sia.vpos.client.response.ThreeDSAuthorization0Response;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.exception.VPosClientException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test3DSNoUnitTest {

    private static final String SHOP_ID = "129289999900002";
    private static final String REDIRECT_KEY_VPOS = "au-PA-B2AAHsQSG-UuaVNcHFpBk3GJBNWqR3--Tyf-Fa-wav--ySqz9f24-yvP-RvbMQx-VYz9jVDNe-uMwTSt3-tvPukbJTTt-U";
    private static final String API_RESULT_KEY = "hSAc7sg-z-vZ-296FuwwUaqHmzQ-eQ-E--2pXV-mEGh6YQtBdDK-NH9KeCyQrtBtmwFv-m6kEUtn27-6ATfkB-x2Dy3F4G-9t4sp";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";
    private static final String OPERATOR_ID = "John Doe";
    private static final String PROXYNAME = "proxy-dr.reply.it";
    private static final Integer PROXYPORT = 8080;
    private static final String dataTest = "{\"addrMatch\":\"N\",\"txnActivityYear\":\"100\",\"billAddrLine1\":\"billAddrLine1\",\"billAddrCountry\":\"004\",\"workPhone\":\"39-0321818198\",\"preOrderPurchaseInd\":\"01\",\"shipAddrState\":\"222\",\"shipAddressUsageInd\":\"03\",\"mobilePhone\":\"33-312\",\"billAddrState\":\"MI\",\"shipAddrLine2\":\"shipAddrLine2\",\"chAccPwChange\":\"20190214\",\"shipAddrLine1\":\"shipAddrLine1\",\"shipAddrLine3\":\"shipAddrLine3\",\"chAccAgeInd\":\"04\",\"billAddrPostCode\":\"billAddrPostCode\",\"txnActivityDay\":\"100\",\"shipAddressUsage\":\"20181220\",\"billAddrCity\":\"billAddrCity\",\"chAccDate\":\"20190210\",\"billAddrLine3\":\"billAddrLine3\",\"chAccPwChangeInd\":\"04\",\"shipNameIndicator\":\"01\",\"nbPurchaseAccount\":\"1000\",\"chAccChange\":\"20190211\",\"billAddrLine2\":\"billAddrLine2\",\"deliveryEmailAddress\":\"a-b@example.com\",\"preOrderDate\":\"20181220\",\"shipAddrCountry\":\"008\",\"shipAddrCity\":\"zio\",\"shipAddrPostCode\":\"shipAddrPostCode\",\"homePhone\":\"39-321818198\"}";
    private static final String data3dsRedirect = "{\"addrMatch\":\"N\",\"chAccAgeInd\":\"04\",\"chAccChange\":\"20190211\",\"chAccChangeInd\":\"03\",\"chAccDate\":\"20190210\",\"chAccPwChange\":\"20190214\",\"chAccPwChangeInd\":\"04\",\"nbPurchaseAccount\":\"1000\",\"txnActivityDay\":\"100\",\"txnActivityYear\":\"100\",\"shipAddressUsage\":\"20181220\",\"shipAddressUsageInd\":\"03\",\"shipNameIndicator\":\"01\",\"billAddrCity\":\"billAddrCity\",\"billAddrCountry\":\"004\",\"billAddrLine1\":\"billAddrLine1\",\"billAddrLine2\":\"billAddrLine2\",\"billAddrLine3\":\"billAddrLine3\",\"billAddrPostCode\":\"billAddrPostCode\",\"billAddrState\":\"MI\",\"homePhone\":\"39-321818198\",\"mobilePhone\":\"33-312\",\"shipAddrCity\":\"zio\",\"shipAddrCountry\":\"008\",\"shipAddrLine1\":\"shipAddrLine1\",\"shipAddrLine2\":\"shipAddrLine2\",\"shipAddrLine3\":\"shipAddrLine3\",\"shipAddrPostCode\":\"shipAddrPostCode\",\"shipAddrState\":\"222\",\"workPhone\":\"39-0321818198\",\"deliveryEmailAddress\":\"a-b@example.com\",\"deliveryTimeframe\":\"02\",\"preOrderDate\":\"20181220\",\"preOrderPurchaseInd\":\"01\",\"reorderItemsInd\":\"02\",\"shipIndicator\":\"01\"}";
    private static final String jsonString = "{\"addrMatch\":\"N\",\"chAccAgeInd\":\"04\",\"chAccChange\":\"20190211\",\"chAccChangeInd\":\"03\",\"chAccDate\":\"20190210\",\"chAccPwChange\":\"20190214\",\"chAccPwChangeInd\":\"04\",\"nbPurchaseAccount\":\"1000\",\"txnActivityDay\":\"100\",\"txnActivityYear\":\"100\",\"shipAddressUsage\":\"20181220\",\"shipAddressUsageInd\":\"03\"," +
            "\"shipNameIndicator\":\"01\",\"billAddrCity\":\"billAddrCity\",\"billAddrCountry\":\"004\",\"billAddrLine1\":\"billAddrLine1\",\"billAddrLine2\":\"billAddrLine2\",\"billAddrLine3\":\"billAddrLine3\",\"billAddrPostCode\":\"billAddrPostCode\",\"billAddrState\":\"11\",\"homePhone\":\"039-321818198111\",\"mobilePhone\":\"33-312\"," +
            "\"shipAddrCity\":\"zio\",\"shipAddrCountry\":\"008\",\"shipAddrLine1\":\"shipAddrLine1\",\"shipAddrLine2\":\"shipAddrLine2\",\"shipAddrLine3\":\"shipAddrLine3\",\"shipAddrPostCode\":\"shipAddrPostCode\"," +
            "\"shipAddrState\":\"222\",\"workPhone\":\"39-0321818198\",\"deliveryEmailAddress\":\"a-b@example.com\",\"deliveryTimeframe\":\"02\",\"preOrderDate\":\"20181220\",\"preOrderPurchaseInd\":\"01\",\"reorderItemsInd\":\"02\",\"shipIndicator\":\"01\",\"browserAcceptHeader\":\"text/html,application/xhtml+xml,application/xml;\"," +
            "\"browserIP\":\"10.42.195.152\",\"browserJavaEnabled\":\"true\",\"browserLanguage\":\"it-IT\",\"browserColorDepth\":\"16\",\"browserScreenHeight\":\"1024\",\"browserScreenWidth\":\"1920\",\"browserTZ\":\"-120\",\"browserUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0\"\n" +
            "}" +
            " \n";


    public static void main(String[] args) {
        VPosConfig config = new VPosConfig();
        config.setApiKey(API_RESULT_KEY);
        config.setShopID(SHOP_ID);
        config.setRedirectKey(REDIRECT_KEY_VPOS);
        config.setTimeout("10000");
        config.setAlgorithm("HMAC_SHA_256");
        config.setApiUrl(URL_WEB_API);
        config.setRedirectUrl(URL_REDIRECT);
        //config.setProxyHost("proxy-dr.reply.it");
        //config.setProxyPort(8080);

        VPosClient client = null;
        try {
            client = new VPosClient(config);

            //String b = "OK";
            //ThreeDSAuthorization0Response resp = client.threeDSAuthorize0(build3DSRequest());
            //client.authorize(buildAuthorizationRequest());
            //client.threeDSAuthorize1(buildThreeDSAuthorizationRequest1());
            //client.threeDSAuthorize2(buildThreeDSAuthorizationRequest2());
            System.out.println(client.buildHTMLRedirectFragment(buildPaymentTest()));
            //System.out.println(client.buildHTMLRedirectFragment(buildPaymentWithTokenTest()));
            //OrderStatusResponse resp= client.getOrderStatus(buildOrderStatusRequest("AUTH123456769123429"));
            //CaptureResponse resp = client.capture(buildCaptureRequest("8032112928SL2y94x1frnoyq4","1585841834944140177762812878779680802931184"));
            //RefundResponse resp = client.refund(buildRefundRequest("8032112928SL243jga1ewbdr4", "Redirect30533", "50"));
            //boolean b=client.verifyMAC("http://localhost:8080/payment-gateway/vpos/tokenize?ORDERID=1585919322092143568728681910679428531949566&SHOPID=129289999900002&AUTHNUMBER=413889&AMOUNT=10&CURRENCY=978&TRANSACTIONID=8032112928SL211ntcm0icwf4&ACCOUNTINGMODE=D&AUTHORMODE=I&RESULT=00&TRANSACTIONTYPE=TT07&TRECURR=U&CRECURR=899107067200401&NETWORK=02&MAC=105e962d0727ef0d30a1ce21d14e6813449daa6375c433d2cc2fa631bc3bf680");
            //System.out.println(b);
        } catch (VPosClientException e) {
            System.out.println(e);
        }
    }

    public static ThreeDSAuthorization0Request build3DSRequest() {
        ThreeDSAuthorization0Request request3DS0 = new ThreeDSAuthorization0Request();
        request3DS0.setAmount("6600");
        request3DS0.setAccountingMode("D");
        request3DS0.setPan("4118830900940017");
        request3DS0.setExpDate("2112");
        request3DS0.setCvv2("111");
        request3DS0.setCurrency("978");

        request3DS0.setNetwork("01");
        request3DS0.setEmailCh("asdas@fgd.id");
        Random rand = new Random();

        request3DS0.setOrderId("12345676912345649" + rand.nextInt(1000));
        request3DS0.setOperatorId("JohnDoee");
        request3DS0.setExponent("2");
        //request3DS0.setName("Mario");
        //request3DS0.setSurname("Rossi");
        request3DS0.setNameCh("Mario");
        request3DS0.setNotifyUrl("https://atpostest.ssb.it/atpos/apibo/en/3ds-notification.html");
        request3DS0.setThreeDSMtdNotifyUrl("https://atpostest.ssb.it/atpos/apibo/en/3ds-notification.html");

        Gson g = new Gson();
        Data3DSJson data3DSJson = g.fromJson(jsonString, Data3DSJson.class);
        request3DS0.setThreeDSData(data3DSJson);

        return request3DS0;
    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("100");
        paymentInfo.setCurrency("978");
        Random rand = new Random();
        paymentInfo.setOrderId("Redirect" + rand.nextInt(100000));
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);

        Map<PaymentInfo.FieldName, String> optionalFields = new HashMap<>();
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TOKEN.NAME), "0000500550493297466");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EXPDATE.NAME), "2112");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME), "98");
        optionalFields.put(PaymentInfo.FieldName.NAME,"Name");
        optionalFields.put(PaymentInfo.FieldName.SURNAME,"Surname");

        optionalFields.put(PaymentInfo.FieldName.OPTIONS, "M");
        optionalFields.put(PaymentInfo.FieldName.TRECURR, "U");
        paymentInfo.setNotCompulsoryFields(optionalFields);

        paymentInfo.setAccountingMode("I");
        paymentInfo.setAuthorMode("I");

        Gson g = new Gson();
        Data3DSJson data3DSJson = g.fromJson(data3dsRedirect, Data3DSJson.class);
        paymentInfo.setData3DSJson(data3DSJson);
        //paymentInfo.addOption(PaymentInfo.OptionName.G);
        //paymentInfo.addOption(PaymentInfo.OptionName.M);
        return paymentInfo;
    }

    private static PaymentInfo buildPaymentWithTokenTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("100");
        paymentInfo.setCurrency("978");
        Random rand = new Random();
        paymentInfo.setOrderId("Redirect" + rand.nextInt(1000));
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setExponent("2");


        Map<PaymentInfo.FieldName, String> optionalFields = new HashMap<>();
        optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TOKEN.NAME), "0000096382207580565");
        optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EXPDATE.NAME), "2112");
        optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME), "98");
        //optionalFields.put(PaymentInfo.FieldName.CRECURR, "899107067200401");
        //optionalFields.put(PaymentInfo.FieldName.TRECURR, "U");
        //optionalFields.put(PaymentInfo.FieldName.SHOPEMAIL, "test.appls@ssb.it");
        optionalFields.put(PaymentInfo.FieldName.EMAIL, "test.appls@ssb.it");
        //optionalFields.put(PaymentInfo.FieldName.NAMECH, "Mario");
        //optionalFields.put(PaymentInfo.FieldName.SURNAMECH, "Rossi");

        paymentInfo.setNotCompulsoryFields(optionalFields);

        paymentInfo.setAccountingMode("I");
        paymentInfo.setAuthorMode("I");
        //Gson g = new Gson();
        //Data3DSJson data3DSJson = g.fromJson(data3dsRedirect, Data3DSJson.class);
        //paymentInfo.setData3DSJson(data3DSJson);
        //paymentInfo.addOption(PaymentInfo.OptionName.G);
        //paymentInfo.addOption(PaymentInfo.OptionName.M);
        return paymentInfo;
    }

    private static OrderStatusRequest buildOrderStatusRequest(String orderId) {
        return new OrderStatusRequest( "operator",
                orderId, null, null);
    }

    private static CaptureRequest buildCaptureRequest(String transactionId, String orderId) {
        CaptureRequest req = new CaptureRequest();
        req.setAmount("500");
        req.setOperatorId("OPERATOR");
        req.setCurrency("978");
        req.setOrderId(orderId);
        req.setOpDescr("oper descr");
        req.setExponent("2");
        req.setTransactionId(transactionId);
        return req;
    }

    private static RefundRequest buildRefundRequest(String transactionId, String orderId, String amount) {
        RefundRequest req = new RefundRequest("Giammaicol",
                transactionId, orderId,
                amount, "978", null, null);
        return req;
    }

    private static AuthorizationRequest buildAuthorizationRequest() {
        AuthorizationRequest req = new AuthorizationRequest();
        Random rand = new Random();

        req.setOrderId("AUTH123456769123" + rand.nextInt(1000));
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

    private static ThreeDSAuthorization1Request buildThreeDSAuthorizationRequest1() {
        ThreeDSAuthorization1Request req = new ThreeDSAuthorization1Request();
        req.setOperatorId("operatorID");
        req.setThreeDSMtdComplInd("N");
        req.setThreeDSTransId("b76e13ac-ba8c-4f90-b00c-9cffe9cbc049");
        return req;
    }

    private static ThreeDSAuthorization2Request buildThreeDSAuthorizationRequest2() {
        ThreeDSAuthorization2Request req = new ThreeDSAuthorization2Request();
        req.setOperatorId("operatorID");
        req.setThreeDSTransId("b76e13ac-ba8c-4f90-b00c-9cffe9cbc049");
        return req;
    }

    private static PaymentInfo buildPaymentTestWithToken(){
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("100");
        paymentInfo.setCurrency("978");
        Random rand = new Random();
        paymentInfo.setOrderId("Redirect" + rand.nextInt(1000));
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);

        paymentInfo.setAccountingMode("I");
        paymentInfo.setAuthorMode("I");

        Map<PaymentInfo.FieldName, String> optionalFields = new HashMap<>();
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TOKEN.NAME), "0000938208853671399");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EXPDATE.NAME), "2112");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME), "93");
        //optionalFields.put(PaymentInfo.FieldName.NAMECH, "Mario Rossi");
        optionalFields.put(PaymentInfo.FieldName.TOKEN, "0000308796664784358");
        //optionalFields.put(PaymentInfo.FieldName.TRECURR, "U");
        //optionalFields.put(PaymentInfo.FieldName.CRECURR, "785619991244270");
        optionalFields.put(PaymentInfo.FieldName.EXPDATE, "2111");
        optionalFields.put(PaymentInfo.FieldName.NETWORK, "98");
        paymentInfo.setNotCompulsoryFields(optionalFields);
        //Gson g = new Gson();
        //Data3DSJson data3DSJson = g.fromJson(jsonString, Data3DSJson.class);
        //paymentInfo.setData3DSJson(data3DSJson);
        return paymentInfo;
    }
}
