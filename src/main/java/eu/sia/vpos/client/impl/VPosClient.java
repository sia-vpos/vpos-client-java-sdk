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
import eu.sia.vpos.client.utils.ResponseMapper;
import eu.sia.vpos.client.utils.builders.MapBuilder;
import eu.sia.vpos.client.utils.builders.RequestBuilder;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.Encoder;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;
import eu.sia.vpos.client.utils.mac.ResponseMACCalculator;

import java.util.Map;

public class VPosClient implements Client {

    private final Config config;
    protected VPosPaymentClient vPosPaymentClient;

    private HTMLGenerator htmlTool;
    private RequestBuilder requestBuilder;
    private ResponseMACCalculator responseMACCalculator;
    private ResponseMapper responseMapper;
    private Encoder hmacCalculator;
    private String startKey;
    private String apiResultKey;


    public VPosClient(Config config) throws VPosClientException {
        this.htmlTool = new HTMLGenerator();
        this.responseMapper = new ResponseMapper();
        this.config = config;
        this.startKey = config.getRedirectKey();
        this.apiResultKey = config.getApiKey();
        if(config.getAlgorithm()==null) {
            this.requestBuilder = new RequestBuilder(apiResultKey, MacAlgorithms.HMAC_SHA_256);
            this.hmacCalculator = new Encoder(MacAlgorithms.HMAC_SHA_256);
        }else {
            this.requestBuilder = new RequestBuilder(apiResultKey, config.getAlgorithm());
            this.hmacCalculator = new Encoder(config.getAlgorithm());
        }
        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);

    }

    public Config getConfig() {
        return config;
    }

    /*@Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) throws VPosClientException {
        return null;
    }
    */
    @Override
    public ThreeDSAuthorization0Response threeDSAuthorize0(ThreeDSAuthorization0Request threeDSAuthorization0Request) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize0(threeDSAuthorization0Request);
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //TO DO Verify MAC-Response
        //TO DO Response Mapping
        return new ThreeDSAuthorization0Response();
    }

    @Override
    public ThreeDSAuthorization1Response threeDSAuthorize1(ThreeDSAuthorization1Request threeDSAuthorization1Request)  throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize1(threeDSAuthorization1Request);
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //TO DO Verify MAC-Response
        //TO DO Response Mapping
        return null;
    }


    @Override
    public ThreeDSAuthorization2Response threeDSAuthorize2(ThreeDSAuthorization2Request threeDSAuthorization2Request) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize2(threeDSAuthorization2Request);
        return null;
    }

    @Override
    public CaptureResponse capture(CaptureRequest captureRequest) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildBookingRequest(captureRequest);
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.bookingResponseDto(xmlResponse);
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildRefundRequest(refundRequest);
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapRefundResponseDto(xmlResponse);
    }

    @Override
    public OrderStatusResponse getOrderStatus(OrderStatusRequest orderStatusRequest) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildOrderStatusRequest(orderStatusRequest);
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapOrderStatusResponse(xmlResponse);
    }

    @Override
    public boolean verifyMAC(Map<String, String> params) throws VPosClientException {
        // TODO: put here MAC parameters verify code against "MAC" parameter
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
        return htmlTool.buildHtmlPaymentDiv(paymentInfo.getDelay(), paymentInfo.getUrlApos(), MapBuilder.getRedirectMap(paymentInfo, hmacCalculator, startKey, apiResultKey));
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
    }

}
