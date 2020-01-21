package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPOSSimpleClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.dto.response.OrderStatusResponseDto;
import it.reply.cof.utils.exception.COFException;

public class Application {
    private static final String SHOP_ID = "129281292800104";
    private static final String PAN_ALIAS = "0000147162817302285";
    private static final String START_KEY = "CNCuDT5Vyws2v9t--RhDdtCwstaV2tqVeqfE-D8G-S-Ds--C-fFkSBxw-3wWBqBC--U9hwN-H-Mj4ZZHMr--YSHLdU5WKLx-cT-T";
    private static final String API_RESULT_KEY = "nS-zLHC35Pzu47m-AZtRejrd-xd2SsudbpKmmfGgD-9-dJjRQpm8pPJM84uEZgkgq-CfJ9n--sHNHjKx7--Aw-56jEqLVDe-aT-f";
    private static final String MAC_KEY_REDIRECT = "VnFBty7us-7QubjwHcjFaq4hb5-5n5-VdaymsXfqukzd3nhYhsLvuL5nFs-sCrJT--n9-J7zghhUYpFUw6q4KxCQ-XND6dV—abW";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/cardData?consumerId=3b350c34-d923-4552-91bf-67bc4f99da92";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";

    private static final String BASE64HTML = "PGh0bWw+CiAgICA8Ym9keT4KICAgICAgICAKICAgIDwvYm9keT4KPC9odG1sPg==";

    public static void main(String[] args) throws COFException {
        VPOSClient vposClient = new VPOSSimpleClient(URL_WEB_API, START_KEY, API_RESULT_KEY);

        System.out.println(vposClient.getHtmlPaymentDocument(buildPaymentTest(), URL_REDIRECT));

        try{
            vposClient.refundPayment(buildRefundTest());
        } catch (Exception e){

            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId("0oiujh6rd3tbhberwwww3g4ui777");
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("D");
        paymentInfo.setAuthorMode("I");
        paymentInfo.addOption("GM");
        return paymentInfo;
    }

   private static RefundRequestDto buildRefundTest(){
        RefundRequestDto dto = new RefundRequestDto("129281292800104", "a caso", "8032112928SL11dk36s2gdj34", "ygaedwwdauigougui777", "10", "978", "1", "jopokokok");

        return dto;
    }

    private static Auth3DSDto buildAuth3DSTest1() {
        Auth3DSDto dto = new Auth3DSDto();

        dto.setMasterpass(true);
        dto.setOrderId("0oiujh6rd3tbhberwwww3g4ui777");
        dto.setPan("0000916743400860385&");
        dto.setExpDate("2112");
        dto.setAmount("10");
        dto.setCurrency("978");
        dto.setExponent("1");
        dto.setAccountingMode("D");
        dto.setNetwork("98");
        //dto.setTimestamp();
        dto.setShopId("129281292800104");
        dto.setOperatorId("Giammaicol");
        dto.setOptions("GM");

        return dto;
    }

    private static VerifyRequestDto buildVerifyTest(){
        VerifyRequestDto dto = new VerifyRequestDto();

        dto.setOriginalReqRefNum("20200121747989345505071695860709");
        dto.setShopId("129281292800104");
        dto.setOperatorId("Giammaicol");


        return dto;
    }

    private static ConfirmRequestDto buildConfirmTest(){
        ConfirmRequestDto dto = new ConfirmRequestDto();


        return dto;
    }

    private static OrderStatusRequestDto buildOrderStatusTest(){
        OrderStatusRequestDto dto = new OrderStatusRequestDto("129281292800104", "Giammaicol",
                "0oiujh6rd3tbhberwwww3g4ui777", "dedetv435t4ff4f", "");




        return dto;
    }




}
