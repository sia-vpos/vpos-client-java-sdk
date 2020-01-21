package it.reply.cof.client;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.response.Authorization;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.dto.response.*;
import it.reply.cof.utils.HTMLGenerator;
import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.ResponseMapper;
import it.reply.cof.utils.builders.MapBuilder;
import it.reply.cof.utils.builders.RequestBuilder;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.Encoder;
import it.reply.cof.utils.mac.ResponseMACCalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


/**
 * Abstract implementation of VPOSClient interface
 *
 * @author Gabriel Raul Marini
 */
public abstract class VPOSClientAbstract implements VPOSClient {

    private static final String HTML_FILE_PATH = "/src/main/resources/template.html";
    private static final String HTML_DEFAULT_PATH = "/src/main/resources/default.html";

    protected String startKey;
    protected String apiResultKey;
    protected AposPaymentClient aposClient;

    private Boolean customTemplate;
    private String filePath;
    private HTMLGenerator htmlTool;
    private RequestBuilder requestBuilder;
    private Encoder hmacCalculator;
    private ResponseMapper responseMapper;
    private ResponseMACCalculator responseMACCalculator;

    private VPOSClientAbstract() {
        filePath = new File("").getAbsolutePath();
        htmlTool = new HTMLGenerator();
        responseMapper = new ResponseMapper();
        customTemplate = false;
    }

    /**
     * Instantiate an abstract VPOS client object enabled to perform VPOSClient operations
     * with HMAC-SHA-256 MAC algorithm
     *
     * @param startKey     used to perform MAC calculation of the outcoming requests
     * @param apiResultKey used to perform MAC calculation of the incoming VPOS responses
     * @throws COFException in case of instantiation failure (see exception message for more infos)
     */
    public VPOSClientAbstract(String startKey, String apiResultKey) throws COFException {
        this();
        this.requestBuilder = new RequestBuilder(startKey);
        this.hmacCalculator = new Encoder();
        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);
        this.startKey = startKey;
        this.apiResultKey = apiResultKey;
    }

    /**
     * Instantiate an abstract VPOS client object enabled to perform VPOSClient operations
     * with the specified MAC algorithm
     *
     * @param startKey     used to perform MAC calculation of the outcoming requests
     * @param apiResultKey used to perform MAC calculation of the incoming VPOS responses
     * @param algorithm    used to perform MAC calculation (HMAC_SHA_256 used by default)
     * @throws COFException in case of instantiation failure (see exception message for more infos)
     */
    public VPOSClientAbstract(String startKey, String apiResultKey, MacAlgorithms algorithm) throws COFException {
        this();
        this.requestBuilder = new RequestBuilder(startKey, algorithm);
        this.hmacCalculator = new Encoder(algorithm);
        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);
        this.startKey = startKey;
        this.apiResultKey = apiResultKey;
    }

    @Override
    public void injectHtmlTemplate(String base64, Integer delay) throws COFException {
        String html = htmlTool.base64ToHtml(base64, delay);

        try (FileWriter fileWriter = new FileWriter(filePath.concat(HTML_FILE_PATH))) {
            fileWriter.write(html);
            customTemplate = true;
        } catch (IOException e) {
            throw new COFException("Error in saving HTML template. Default template will be used to redirect.");
        }
    }

    @Override
    public String getHtmlPaymentDocument(PaymentInfo paymentInfo, String urlApos) throws COFException {
        String path = customTemplate.booleanValue() ? filePath.concat(HTML_FILE_PATH) : filePath.concat(HTML_DEFAULT_PATH);
        return htmlTool.htmlToBase64(path, urlApos, MapBuilder.getRedirectMap(paymentInfo, hmacCalculator, startKey));
    }

    @Override
    public void verifyURL(Map<String, String> values, String receivedMac) throws COFException {
        String calculatedMAc = hmacCalculator.getMac(MapBuilder.getOutcomeMap(values), apiResultKey);
        if (!receivedMac.equals(calculatedMAc))
            throw new COFException("Authorization MAC is not valid");
    }

    @Override
    public void setProxy(String proxyName, Integer proxyPort) {
        this.aposClient.setProxy(proxyName, proxyPort);
    }


    @Override
    public Auth3DSResponseDto start3DSAuth(Auth3DSDto dto) throws COFException {
        BPWXmlRequest request = requestBuilder.build3DSAuthRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.auth3DSResponseDto(xmlResponse);
    }

    @Override
    public Auth3DSStep2ResponseDto start3DSAuthStep2Dto(Auth3DSStep2RequestDto dto) throws COFException{
        BPWXmlRequest request = requestBuilder.build3DSStep2AuthRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.auth3DSStep2ResponseDto(xmlResponse);
    }

    @Override
    public ConfirmationResponseDto confirmPayment(ConfirmRequestDto dto) throws COFException {
        BPWXmlRequest request = requestBuilder.buildConfirmRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapConfirmationResponse(xmlResponse);
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
    public VerifyResponseDto verifyPayment(VerifyRequestDto dto) throws COFException {
        BPWXmlRequest request = requestBuilder.buildVerifyRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);

        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapVerifyResponse(xmlResponse);
    }

    @Override
    public OrderStatusResponseListDto getOrderStatus(OrderStatusRequestDto dto) throws COFException {
        BPWXmlRequest request = requestBuilder.buildOrderStatusRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapOrderStatusResponse(xmlResponse);
    }

    private void verifyMacResponse(BPWXmlResponse response) throws COFException {
        final String NEUTRAL_MAC_VALUE = "NULL";

        String responseMac = responseMACCalculator.getBPWXmlResponseMac(response, apiResultKey);

        if (!response.getMac().equals(NEUTRAL_MAC_VALUE) && !response.getMac().equals(responseMac))
            throw new COFException("Response MAC is not valid");


        if (response.getData() != null && response.getData().getOperation() != null) {
            String operationMac = responseMACCalculator.getOperationMac(response.getData().getOperation(), apiResultKey);
            if (!response.getData().getOperation().getMac().equals(NEUTRAL_MAC_VALUE) && !response.getData().getOperation().getMac().equals(operationMac))
                throw new COFException("Operation MAC is not valid");

        }

        if (response.getData() != null && response.getData().getAuthorization() != null) {
            for (Authorization authorization : response.getData().getAuthorization()) {
                String authorizationMac = responseMACCalculator.getAuthorizationMac(authorization, apiResultKey);
                if (!authorization.getMac().equals(NEUTRAL_MAC_VALUE) && !authorization.getMac().equals(authorizationMac))
                    throw new COFException("Authorization MAC is not valid");
            }
        }
    }

}