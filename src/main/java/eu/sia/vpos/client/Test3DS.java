package eu.sia.vpos.client;

import com.google.gson.Gson;
import eu.sia.vpos.client.impl.VPosClient;
import eu.sia.vpos.client.impl.VPosConfig;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.response.OrderStatusResponse;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.exception.VPosClientException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test3DS {

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
    private static final String jsonString = "{\n" +
            "\"addrMatch\":\"N\",\n" +
            "\"chAccAgeInd\":\"04\",\n" +
            "\"chAccChange\":\"20190211\",\n" +
            "\"chAccChangeInd\":\"03\",\n" +
            "\"chAccDate\":\"20190210\",\n" +
            "\"chAccPwChange\":\"20190214\",\n" +
            "\"chAccPwChangeInd\":\"04\",\n" +
            "\"nbPurchaseAccount\":\"1000\",\n" +
            "\"txnActivityDay\":\"100\",\n" +
            "\"txnActivityYear\":\"100\",\n" +
            "\"shipAddressUsage\":\"20181220\",\n" +
            "\"shipAddressUsageInd\":\"03\",\n" +
            "\"shipNameIndicator\":\"01\",\n" +
            "\"billAddrCity\":\"billAddrCity\",\n" +
            "\"billAddrCountry\":\"004\",\n" +
            "\"billAddrLine1\":\"billAddrLine1\",\n" +
            "\"billAddrLine2\":\"billAddrLine2\",\n" +
            "\"billAddrLine3\":\"billAddrLine3\",\n" +
            "\"billAddrPostCode\":\"billAddrPostCode\",\n" +
            "\"billAddrState\":\"11\",\n" +
            "\"homePhone\":\"039-321818198111\",\n" +
            "\"mobilePhone\":\"33-312\",\n" +
            "\"shipAddrCity\":\"zio\",\n" +
            "\"shipAddrCountry\":\"008\",\n" +
            "\"shipAddrLine1\":\"shipAddrLine1\",\n" +
            "\"shipAddrLine2\":\"shipAddrLine2\",\n" +
            "\"shipAddrLine3\":\"shipAddrLine3\",\n" +
            "\"shipAddrPostCode\":\"shipAddrPostCode\",\n" +
            "\"shipAddrState\":\"222\",\n" +
            "\"workPhone\":\"39-0321818198\",\n" +
            "\"deliveryEmailAddress\":\"a-b@example.com\",\n" +
            "\"deliveryTimeframe\":\"02\",\n" +
            "\"preOrderDate\":\"20181220\",\n" +
            "\"preOrderPurchaseInd\":\"01\",\n" +
            "\"reorderItemsInd\":\"02\",\n" +
            "\"shipIndicator\":\"01\",\n" +
            "\"browserAcceptHeader\":\"text/html,application/xhtml+xml,application/xml;\",\n" +
            "\"browserIP\":\"10.42.195.152\",\n" +
            "\"browserJavaEnabled\":\"true\",\n" +
            "\"browserLanguage\":\"it-IT\",\n" +
            "\"browserColorDepth\":\"16\",\n" +
            "\"browserScreenHeight\":\"1024\",\n" +
            "\"browserScreenWidth\":\"1920\",\n" +
            "\"browserTZ\":\"-120\",\n" +
            "\"browserUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0\"\n" +
            "}\n" +
            " \n";


    public static void main(String[] args) {
        VPosConfig config = new VPosConfig();
        config.setApiKey(API_RESULT_KEY);
        config.setShopID(SHOP_ID);
        config.setRedirectKey(REDIRECT_KEY_VPOS);

        config.setAlgorithm("HMAC_SHA_256");
        config.setApiUrl(URL_WEB_API);
        config.setRedirectUrl("https://atpostest.ssb.it/atpos/pagamenti/main");
        //config.setProxyHost("proxy-dr.reply.it");
        //config.setProxyPort(8080);

        VPosClient client = null;
        try {
            client = new VPosClient(config);

            String b = "OK";
            //ThreeDSAuthorization0Response resp = client.threeDSAuthorize0(build3DSRequest());
            //client.authorize(buildAuthorizationRequest());
            //client.threeDSAuthorize1(buildThreeDSAuthorizationRequest1());
            System.out.println(client.buildHTMLRedirectFragment(buildPaymentWithTokenTest()));
            //OrderStatusResponse resp= client.getOrderStatus(buildOrderStatusRequest("010101010"));
            //client.capture(buildCaptureRequest("8032112928AT1m7jef362ea44","12345676912345649719"));
            //client.refund(buildRefundRequest("8032112928SL21gibxxw3ue54", "12345676912345649938", "50"));
            //boolean b=client.verifyMAC("http://localhost:8080/payment-gateway/vpos/tokenize?ORDERID=12345676912345649229&SHOPID=129281292800109&AUTHNUMBER=NULL&AMOUNT=10000&CURRENCY=978&TRANSACTIONID=8032112928SL11m99f7dkliz4&ACCOUNTINGMODE=D&AUTHORMODE=D&RESULT=00&TRANSACTIONTYPE=TT01&PANTAIL=0027&PANEXPIRYDATE=2112&NETWORK=01&MAC=97561492b40f189ea617cb992cf8a8fab825674a45ccb45407257442394bc39e");
            System.out.println(b);
        } catch (VPosClientException e) {
            System.out.println(e.getExceptionMessage());
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
        request3DS0.setMerchantKey(API_RESULT_KEY);

        return request3DS0;
    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("100");
        paymentInfo.setCurrency("978");
        Random rand = new Random();
        paymentInfo.setOrderId("Redirect" + rand.nextInt(1000));
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);

        Map<PaymentInfo.FieldName, String> optionalFields = new HashMap<>();
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TOKEN.NAME), "0000500550493297466");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EXPDATE.NAME), "2112");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME), "98");
        optionalFields.put(PaymentInfo.FieldName.NAME,"Name");
        optionalFields.put(PaymentInfo.FieldName.SURNAME,"Surname");

        //optionalFields.put(PaymentInfo.FieldName.OPTIONS, "B");
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
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);


        Map<PaymentInfo.FieldName, String> optionalFields = new HashMap<>();
        optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TOKEN.NAME), "0000500550493297466");
        optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EXPDATE.NAME), "2112");
        optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME), "98");
        optionalFields.put(PaymentInfo.FieldName.CRECURR, "743382131200325");
        optionalFields.put(PaymentInfo.FieldName.TRECURR, "U");
        //optionalFields.put(PaymentInfo.FieldName.SHOPEMAIL, "test.appls@ssb.it");
        optionalFields.put(PaymentInfo.FieldName.EMAIL, "test.appls@ssb.it");
        optionalFields.put(PaymentInfo.FieldName.NAMECH, "Mario");
        optionalFields.put(PaymentInfo.FieldName.SURNAMECH, "Rossi");

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
        return new OrderStatusRequest(SHOP_ID, "operator",
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
        req.setThreeDSTransId("1d7f69db-978e-43cb-a77a-5b9372ff1fae");
        return req;
    }

    private static PaymentInfo buildPaymentTestWithToken(){
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("100");
        paymentInfo.setCurrency("978");
        Random rand = new Random();
        paymentInfo.setOrderId("Redirect" + rand.nextInt(1000));
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);

        paymentInfo.setAccountingMode("I");
        paymentInfo.setAuthorMode("I");

        Map<PaymentInfo.FieldName, String> optionalFields = new HashMap<>();
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TOKEN.NAME), "0000938208853671399");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EXPDATE.NAME), "2112");
        //optionalFields.put(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME), "93");
        optionalFields.put(PaymentInfo.FieldName.NAMECH, "Mario Rossi");
        optionalFields.put(PaymentInfo.FieldName.TOKEN, "0000716798671971460");
        optionalFields.put(PaymentInfo.FieldName.TRECURR, "U");
        optionalFields.put(PaymentInfo.FieldName.CRECURR, "785619991244270");
        optionalFields.put(PaymentInfo.FieldName.EXPDATE, "2111");
        optionalFields.put(PaymentInfo.FieldName.NETWORK, "98");
        paymentInfo.setNotCompulsoryFields(optionalFields);
        //Gson g = new Gson();
        //Data3DSJson data3DSJson = g.fromJson(jsonString, Data3DSJson.class);
        //paymentInfo.setData3DSJson(data3DSJson);
        return paymentInfo;
    }
}
