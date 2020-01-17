package it.reply.cof.client;

import com.sun.javafx.collections.MappingChange;
import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.response.Authorization;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.dto.response.Auth3DSResponseDto;
import it.reply.cof.dto.response.RefundResponseDto;
import it.reply.cof.utils.HTMLGenerator;
import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.ResponseMapper;
import it.reply.cof.utils.builders.MapBuilder;
import it.reply.cof.utils.builders.RequestBuilder;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.Encoder;
import it.reply.cof.utils.mac.ResponseMACCalculator;

import java.util.Map;

public abstract class VPOSClientAbstract implements VPOSClient {

    protected String startKey;
    protected String apiResultKey;
    protected AposPaymentClient aposClient;

    private HTMLGenerator htmlTool;
    private RequestBuilder requestBuilder;
    private Encoder hmacCalculator;
    private ResponseMapper responseMapper;
    private ResponseMACCalculator responseMACCalculator;

    private VPOSClientAbstract() {
        htmlTool = new HTMLGenerator();
        responseMapper = new ResponseMapper();
    }

    public VPOSClientAbstract(String startKey, String apiResultKey) throws COFException {
        this();
        this.requestBuilder = new RequestBuilder(startKey);
        this.hmacCalculator = new Encoder();
        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);
        this.startKey = startKey;
        this.apiResultKey = apiResultKey;
    }

    public VPOSClientAbstract(String startKey, String apiResultKey, MacAlgorithms algorithm) throws COFException {
        this();
        this.requestBuilder = new RequestBuilder(startKey, algorithm);
        this.hmacCalculator = new Encoder(algorithm);
        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);
        this.startKey = startKey;
        this.apiResultKey = apiResultKey;
    }

    @Override
    public String getHtmlPaymentDocument(PaymentInfo paymentInfo) throws COFException {
        //TODO
        return htmlTool.htmlToBase64("C:\\Users\\gab.marini\\Documents\\java-library\\test.html", MapBuilder.getRedirectMap(paymentInfo, hmacCalculator, startKey));
    }


    @Override
    public void setProxy(String proxyName, Integer proxyPort) {
        this.aposClient.setProxy(proxyName, proxyPort);
    }


    @Override
    public Auth3DSResponseDto start3DSAuth(Auth3DSDto dto) throws COFException {
        BPWXmlRequest request = requestBuilder.build3DSAuthRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);

        Auth3DSResponseDto response = new Auth3DSResponseDto();
        return response;
    }

    @Override
    public void confirmPayment(ConfirmRequestDto dto) {
        //TODO
    }

    @Override
    public RefundResponseDto refundPayment(RefundRequestDto dto) throws COFException {
        BPWXmlRequest request = requestBuilder.buildRefundRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);

        //check response MACs validity
        verifyMacResponse(xmlResponse);

        return responseMapper.mapRefundResponseDto(xmlResponse);
    }

    @Override
    public void verifyPayment(VerifyRequestDto dto) {
        //TODO
    }

    @Override
    public void getOrderStatus(OrderStatusRequestDto dto) {
        //TODO
    }

    private void verifyMacResponse(BPWXmlResponse response) throws COFException {
        final String NEUTRAL_MAC_VALUE = "NULL";

        String responseMac = responseMACCalculator.getBPWXmlResponseMac(response, apiResultKey);
        if (!response.getMac().equals(NEUTRAL_MAC_VALUE) && !response.getMac().equals(responseMac))
            throw new COFException("Response MAC is not valid");


        if (response.getData().getOperation() != null) {
            String operationMac = responseMACCalculator.getOperationMac(response.getData().getOperation(), apiResultKey);
            if (!response.getData().getOperation().getMac().equals(NEUTRAL_MAC_VALUE) && !response.getData().getOperation().getMac().equals(operationMac))
                throw new COFException("Operation MAC is not valid");

        }

        if (response.getData().getAuthorization() != null) {
            for (Authorization authorization : response.getData().getAuthorization()) {
                String authorizationMac = responseMACCalculator.getAuthorizationMac(authorization, apiResultKey);
                if (!authorization.getMac().equals(NEUTRAL_MAC_VALUE) && !authorization.getMac().equals(authorizationMac))
                    throw new COFException("Authorization MAC is not valid");
            }
        }
    }

    @Override
    public void verifyURL(Map<String, String> values, String receivedMac) throws COFException {
        String calculatedMAc= hmacCalculator.getMac(MapBuilder.getOutcomeMap(values),apiResultKey);
        if(!receivedMac.equals(calculatedMAc))
            throw new COFException("Authorization MAC is not valid");

    }
}
