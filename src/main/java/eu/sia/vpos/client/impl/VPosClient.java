package eu.sia.vpos.client.impl;

import eu.sia.vpos.client.Client;
import eu.sia.vpos.client.Config;
import eu.sia.vpos.client.impl.util.VPosPaymentClient;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.request.xml.BPWXmlRequest;
import eu.sia.vpos.client.response.*;
import eu.sia.vpos.client.response.xml.Authorization;
import eu.sia.vpos.client.response.xml.BPWXmlResponse;
import eu.sia.vpos.client.utils.HTMLGenerator;
import eu.sia.vpos.client.utils.RequestValidator;
import eu.sia.vpos.client.utils.ResponseMapper;
import eu.sia.vpos.client.utils.builders.MapBuilder;
import eu.sia.vpos.client.utils.builders.RequestBuilder;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.Encoder;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;
import eu.sia.vpos.client.utils.mac.ResponseMACCalculator;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class VPosClient implements Client {

    private final Config config;
    protected VPosPaymentClient vPosPaymentClient;

    private HTMLGenerator htmlTool;
    private RequestBuilder requestBuilder;
    private ResponseMACCalculator responseMACCalculator;
    private ResponseMapper responseMapper;
    private Encoder hmacCalculator;
    private String redirectKey;
    private String redirectUrl;
    private String apiResultKey;


    public VPosClient(Config config) throws VPosClientException {
        this.htmlTool = new HTMLGenerator();
        this.responseMapper = new ResponseMapper();
        this.config = config;
        this.redirectKey = config.getRedirectKey();
        this.redirectUrl= "https://atpostest.ssb.it/atpos/pagamenti/main";
        this.apiResultKey = config.getApiKey();
        if (config.getAlgorithm() == null) {
            this.requestBuilder = new RequestBuilder(apiResultKey, MacAlgorithms.HMAC_SHA_256);
            this.hmacCalculator = new Encoder(MacAlgorithms.HMAC_SHA_256);
        } else {
            this.requestBuilder = new RequestBuilder(apiResultKey, config.getAlgorithm());
            this.hmacCalculator = new Encoder(config.getAlgorithm());
        }
        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);
        initPaymentClient();

    }

    private void initPaymentClient(){
        if (config.getProxyHost() == null || config.getProxyPort() == null) {
            this.vPosPaymentClient = new VPosPaymentClient(config.getUrl());
        }else{
            this.vPosPaymentClient = new VPosPaymentClient(config.getUrl(),config.getProxyHost(),config.getProxyPort());
        }
    }


    /*@Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) throws VPosClientException {
        return null;
    }
    */
    @Override
    public ThreeDSAuthorization0Response threeDSAuthorize0(ThreeDSAuthorization0Request threeDSAuthorization0Request) throws VPosClientException, UnsupportedEncodingException {
        RequestValidator.validateThreeDSAuthorization0Request(threeDSAuthorization0Request);
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize0(threeDSAuthorization0Request, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //TO DO Verify MAC-Response
        verifyMacResponse(xmlResponse);
        //TO DO Response Mapping
        return null;
    }

    @Override
    public ThreeDSAuthorization1Response threeDSAuthorize1(ThreeDSAuthorization1Request threeDSAuthorization1Request) throws VPosClientException {
        RequestValidator.validateThreeDSAuthorization1Request(threeDSAuthorization1Request);
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize1(threeDSAuthorization1Request, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //TO DO Verify MAC-Response
        verifyMacResponse(xmlResponse);
        //TO DO Response Mapping
        return null;
    }


    @Override
    public ThreeDSAuthorization2Response threeDSAuthorize2(ThreeDSAuthorization2Request threeDSAuthorization2Request) throws VPosClientException {
        RequestValidator.validateThreeDSAuthorization2Request(threeDSAuthorization2Request);
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize2(threeDSAuthorization2Request, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //TO DO Verify MAC-Response
        verifyMacResponse(xmlResponse);
        //TO DO Response Mapping
        return null;
    }

    @Override
    public CaptureResponse capture(CaptureRequest captureRequest) throws VPosClientException {
        RequestValidator.validateCaptureRequest(captureRequest);
        BPWXmlRequest request = requestBuilder.buildBookingRequest(captureRequest, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.bookingResponseDto(xmlResponse);
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) throws VPosClientException {
        RequestValidator.validateRefundRequest(refundRequest);
        BPWXmlRequest request = requestBuilder.buildRefundRequest(refundRequest, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapRefundResponseDto(xmlResponse);
    }

    @Override
    public OrderStatusResponse getOrderStatus(OrderStatusRequest orderStatusRequest) throws VPosClientException {
        RequestValidator.validateOrderStatusRequest(orderStatusRequest);
        BPWXmlRequest request = requestBuilder.buildOrderStatusRequest(orderStatusRequest, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapOrderStatusResponse(xmlResponse);
    }

    @Override
    public boolean verifyMAC(Map<String, String> params) throws VPosClientException {
        String calculatedMAc = hmacCalculator.getMac(MapBuilder.getOutcomeMap(params), apiResultKey);
        String receivedMac= params.get("MAC");
        if (!receivedMac.equals(calculatedMAc))
            throw new VPosClientException("Authorization MAC is not valid");
        return false;
    }

    /*
    @Override
    public boolean verifyMAC(HttpServletRequest httpServletRequest) throws VPosClientException {
        // TODO: put here MAC parameters verify code against "MAC" parameter
        return false;
    }*/

    @Override
    public String buildHTMLRedirectFragment(PaymentInfo paymentInfo) throws VPosClientException {
        return htmlTool.buildHtmlPaymentDiv(redirectUrl, MapBuilder.getRedirectMap(paymentInfo, hmacCalculator, redirectKey, apiResultKey));
    }

    private void verifyMacResponse(BPWXmlResponse response) throws VPosClientException {
        final String NEUTRAL_MAC_VALUE = "NULL";

        String responseMac = responseMACCalculator.getBPWXmlResponseMac(response, config.getApiKey());
        if (!response.getMac().equals(NEUTRAL_MAC_VALUE) && !response.getMac().equals(responseMac))
            throw new VPosClientException("Response MAC is not valid");


        if (response.getData() != null && response.getData().getOperation() != null) {
            String operationMac = responseMACCalculator.getOperationMac(response.getData().getOperation(), config.getApiKey());
            if (!response.getData().getOperation().getMac().equals(NEUTRAL_MAC_VALUE) && !response.getData().getOperation().getMac().equals(operationMac))
                throw new VPosClientException("Operation MAC is not valid");

        }

        if (response.getData() != null && response.getData().getAuthorization() != null) {
            for (Authorization authorization : response.getData().getAuthorization()) {
                String authorizationMac = responseMACCalculator.getAuthorizationMac(authorization, config.getApiKey());
                if (!authorization.getMac().equals(NEUTRAL_MAC_VALUE) && !authorization.getMac().equals(authorizationMac))
                    throw new VPosClientException("Authorization MAC is not valid");
            }
        }

        if (response.getData() != null && response.getData().getThreeDSMethod() != null) {
            String threeDSMethodMac = responseMACCalculator.getThreeDSMethodMac(response.getData().getThreeDSMethod(), config.getApiKey());
            if (!response.getData().getThreeDSMethod().getMac().equals(NEUTRAL_MAC_VALUE) && !response.getData().getThreeDSMethod().getMac().equals(threeDSMethodMac))
                throw new VPosClientException("threeDSMethod MAC is not valid");
        }

        if (response.getData() != null && response.getData().getThreeDSchallenge() != null) {
            String threeDSChallengeMac = responseMACCalculator.getThreeDSChallengeMac(response.getData().getThreeDSchallenge(), config.getApiKey());
            if (!response.getData().getThreeDSchallenge().getMac().equals(NEUTRAL_MAC_VALUE) && !response.getData().getThreeDSchallenge().getMac().equals(threeDSChallengeMac))
                throw new VPosClientException("threeDSChallenge MAC is not valid");
        }
    }

}
