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
import eu.sia.vpos.client.utils.Utils;
import eu.sia.vpos.client.utils.builders.MapBuilder;
import eu.sia.vpos.client.utils.builders.RequestBuilder;
import eu.sia.vpos.client.utils.constants.ConfigConstants;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.Encoder;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;
import eu.sia.vpos.client.utils.mac.ResponseMACCalculator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Instantiate a VPosClient object enabled to perform VPOSClient operations
     *
     * @param config It is a config object used to configure the VPosClient
     * @throws VPosClientException in case of instantiation failure (see exception message for more infos)
     */
    public VPosClient(Config config) throws VPosClientException {
        this.config = config;
        validateConfig();
        this.htmlTool = new HTMLGenerator();
        this.responseMapper = new ResponseMapper();
        this.redirectKey = config.getRedirectKey();
        this.redirectUrl = config.getRedirectUrl();
        this.apiResultKey = config.getApiKey();

        MacAlgorithms algorithm;
        if (config.getAlgorithm() == null) {
            algorithm = MacAlgorithms.HMAC_SHA_256;
        } else {
            algorithm = MacAlgorithms.valueOf(config.getAlgorithm());
        }
        this.requestBuilder = new RequestBuilder(apiResultKey, algorithm);
        this.hmacCalculator = new Encoder(algorithm);

        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);
        initPaymentClient();
        Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Client correctly initialized");
    }

    private void validateConfig() throws VPosClientException {
        List<String> fields = new ArrayList<>();
        if (this.config.getShopID() == null || !this.config.getShopID().matches(Operations.PARAMETERS.SHOPID.PATTERN)) {
            fields.add(Operations.PARAMETERS.SHOPID.NAME);
        }
        if (this.config.getApiKey() == null) {
            fields.add(ConfigConstants.APIRESULTKEY);
        }
        if (this.config.getApiUrl() == null) {
            fields.add(ConfigConstants.APIURL);
        }
        if (this.config.getRedirectKey() == null) {
            fields.add(ConfigConstants.REDIRECTKEY);
        }
        if (this.config.getRedirectUrl() == null) {
            fields.add(ConfigConstants.REDIRECTURL);
        }
        if (this.config.getTimeout() == null) {
            fields.add(ConfigConstants.TIMEOUT);
        }
        if (!fields.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid or missing configuration params: ");
            for (String field : fields) {
                sb.append(field);
                sb.append(" ");
            }
            throw new VPosClientException("Invalid or missing configuration param: " + sb.toString());
        }
    }

    private void initPaymentClient() {
        this.vPosPaymentClient = new VPosPaymentClient(config.getApiUrl(), Integer.parseInt(config.getTimeout()));
        if (config.getProxyHost() != null && config.getProxyPort() == null) {
            this.vPosPaymentClient.setProxy(config.getProxyHost(), config.getProxyPort(), config.getProxyUsername(), config.getProxyPassword());
        }
        if (config.getSslContext() != null)
            this.vPosPaymentClient.setSslContext(config.getSslContext());
    }

    @Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) throws VPosClientException {
        RequestValidator.validateAuthorizationRequest(authorizationRequest);
        BPWXmlRequest request = requestBuilder.buildOnlineAuthorizationRequest(authorizationRequest, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        verifyMacResponse(xmlResponse);
        return responseMapper.authorizationResponse(xmlResponse);

    }

    @Override
    public ThreeDSAuthorization0Response threeDSAuthorize0(ThreeDSAuthorization0Request threeDSAuthorization0Request) throws VPosClientException {

        RequestValidator.validateThreeDSAuthorization0Request(threeDSAuthorization0Request);
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize0(threeDSAuthorization0Request, config.getShopID(), this.apiResultKey);
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        //Verify MAC-Response
        verifyMacResponse(xmlResponse);
        //Response Mapping
        return responseMapper.threeDSAuthorization0Response(xmlResponse);
    }

    @Override
    public ThreeDSAuthorization1Response threeDSAuthorize1(ThreeDSAuthorization1Request threeDSAuthorization1Request) throws VPosClientException {
        RequestValidator.validateThreeDSAuthorization1Request(threeDSAuthorization1Request);
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize1(threeDSAuthorization1Request, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        // Verify MAC-Response
        verifyMacResponse(xmlResponse);
        // Response Mapping
        return responseMapper.threeDSAuthorization1Response(xmlResponse);
    }

    @Override
    public ThreeDSAuthorization2Response threeDSAuthorize2(ThreeDSAuthorization2Request threeDSAuthorization2Request) throws VPosClientException {
        RequestValidator.validateThreeDSAuthorization2Request(threeDSAuthorization2Request);
        BPWXmlRequest request = requestBuilder.buildThreeDS2Authorize2(threeDSAuthorization2Request, config.getShopID());
        BPWXmlResponse xmlResponse = vPosPaymentClient.executeCall(request);
        // Verify MAC-Response
        verifyMacResponse(xmlResponse);
        // Response Mapping
        return responseMapper.threeDSAuthorization2Response(xmlResponse);
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
    public boolean verifyMAC(String url) throws VPosClientException {
        Map<String, String> params = Utils.splitQuery(url);
        String calculatedMAc = hmacCalculator.getMac(MapBuilder.getOutcomeMap(params), apiResultKey);
        String receivedMac = params.get("MAC");
        return receivedMac.equals(calculatedMAc);
    }

    @Override
    public boolean verifyMAC(HttpServletRequest httpServletRequest) throws VPosClientException {
        return verifyMAC(httpServletRequest.getRequestURL().toString());
    }

    @Override
    public String buildHTMLRedirectFragment(PaymentInfo paymentInfo) throws VPosClientException {
        RequestValidator.validateHTMLRedirectFragmentRequest(paymentInfo);
        Map<String,String> map = MapBuilder.getRedirectMap(paymentInfo, hmacCalculator, redirectKey, apiResultKey, config.getShopID());
        return htmlTool.buildHtmlPaymentDiv(redirectUrl, map);
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

        if (response.getData() != null && response.getData().getPanAliasData() != null) {
            String panAliasDataMAc = responseMACCalculator.getPanAliasDataMac(response.getData().getPanAliasData(), config.getApiKey());
            if (!response.getData().getPanAliasData().getMac().equals(NEUTRAL_MAC_VALUE) && !response.getData().getPanAliasData().getMac().equals(panAliasDataMAc))
                throw new VPosClientException("PanAliasDAta MAC is not valid");
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
