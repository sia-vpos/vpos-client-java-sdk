package eu.sia.vpos.client.impl.old;

import eu.sia.vpos.client.request.xml.BPWXmlRequest;
import eu.sia.vpos.client.response.xml.Authorization;
import eu.sia.vpos.client.response.xml.BPWXmlResponse;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.response.*;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.impl.util.VPosPaymentClient;
import eu.sia.vpos.client.request.PaymentInfo;
import eu.sia.vpos.client.utils.HTMLGenerator;
import eu.sia.vpos.client.utils.ResponseMapper;
import eu.sia.vpos.client.utils.Utils;
import eu.sia.vpos.client.utils.builders.MapBuilder;
import eu.sia.vpos.client.utils.builders.RequestBuilder;
import eu.sia.vpos.client.utils.mac.Encoder;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;
import eu.sia.vpos.client.utils.mac.ResponseMACCalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


/**
 * Abstract implementation of VPOSClient interface
 *
 * @author Gabriel Raul Marini
 */
public abstract class VPOSClientAbstract implements VPOSClientOld {

    private static final String HTML_FILE_PATH = "/src/main/resources/template.html";
    private static final String HTML_DEFAULT_PATH = "/src/main/resources/default.html";

    protected String startKey;
    protected String apiResultKey;
    protected VPosPaymentClient aposClient;

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
     * @throws VPosClientException in case of instantiation failure (see exception message for more infos)
     */
    public VPOSClientAbstract(String startKey, String apiResultKey) throws VPosClientException {
        this();
        this.requestBuilder = new RequestBuilder(apiResultKey);
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
     * @throws VPosClientException in case of instantiation failure (see exception message for more infos)
     */
    public VPOSClientAbstract(String startKey, String apiResultKey, MacAlgorithms algorithm) throws VPosClientException {
        this();
        this.requestBuilder = new RequestBuilder(apiResultKey, algorithm);
        this.hmacCalculator = new Encoder(algorithm);
        this.responseMACCalculator = new ResponseMACCalculator(hmacCalculator);
        this.startKey = startKey;
        this.apiResultKey = apiResultKey;
    }

    @Override
    public void injectHtmlTemplate(String base64, Integer delay) throws VPosClientException {
        String html = htmlTool.base64ToHtml(base64, delay);

        try (FileWriter fileWriter = new FileWriter(filePath.concat(HTML_FILE_PATH))) {
            fileWriter.write(html);
            customTemplate = true;
        } catch (IOException e) {
            throw new VPosClientException("Error in saving HTML template. Default template will be used to redirect.");
        }
    }

    @Override
    public String getHtmlPaymentDocument(PaymentInfo paymentInfo, String urlApos) throws VPosClientException {
        String path = customTemplate ? filePath.concat(HTML_FILE_PATH) : filePath.concat(HTML_DEFAULT_PATH);
        return htmlTool.buildHtmlPaymentDiv(100, urlApos, MapBuilder.getRedirectMap(paymentInfo, hmacCalculator, startKey, apiResultKey));
    }

    @Override
    public String tokenize(String shopId, String urlBack, String urlDone, String urlms, String urlApos) throws VPosClientException {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId(String.valueOf(System.currentTimeMillis()).concat(Utils.generateRandomDigits(37)));
        paymentInfo.setShopId(shopId);
        paymentInfo.setUrlBack(urlBack);
        paymentInfo.setUrlDone(urlDone);
        paymentInfo.setUrlMs(urlms);
        paymentInfo.setAccountingMode("D");
        paymentInfo.setAuthorMode("I");
        paymentInfo.addOption(PaymentInfo.OptionName.G);
        paymentInfo.addOption(PaymentInfo.OptionName.M);
        return getHtmlPaymentDocument(paymentInfo, urlApos);
    }


    @Override
    public void verifyURL(Map<String, String> values, String receivedMac) throws VPosClientException {
        String calculatedMAc = hmacCalculator.getMac(MapBuilder.getOutcomeMap(values), apiResultKey);
        if (!receivedMac.equals(calculatedMAc))
            throw new VPosClientException("Authorization MAC is not valid");
    }

    @Override
    public void setProxy(String proxyName, Integer proxyPort) {
        this.aposClient.setProxy(proxyName, proxyPort);
    }


    @Override
    public Auth3DSResponseDto start3DSAuth(Auth3DSDto dto) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.build3DSAuthRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.auth3DSResponseDto(xmlResponse);
    }

    @Override
    public Auth3DSStep2ResponseDto start3DSAuthStep2(Auth3DSStep2RequestDto dto) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.build3DSStep2AuthRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.auth3DSStep2ResponseDto(xmlResponse);
    }

    @Override
    public CaptureResponse confirmTransaction(CaptureRequest dto) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildBookingRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.bookingResponseDto(xmlResponse);

    }

    @Override
    public RefundResponse refundPayment(RefundRequest dto) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildRefundRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapRefundResponseDto(xmlResponse);
    }

    @Override
    public VerifyResponseDto verifyRequest(VerifyRequestDto dto) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildVerifyRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapVerifyResponse(xmlResponse);
    }

    @Override
    public OrderStatusResponse getOrderStatus(OrderStatusRequest dto) throws VPosClientException {
        BPWXmlRequest request = requestBuilder.buildOrderStatusRequest(dto);
        BPWXmlResponse xmlResponse = aposClient.executeCall(request);
        //check response MACs validity
        verifyMacResponse(xmlResponse);
        return responseMapper.mapOrderStatusResponse(xmlResponse);
    }

    private void verifyMacResponse(BPWXmlResponse response) throws VPosClientException {
        final String NEUTRAL_MAC_VALUE = "NULL";

        String responseMac = responseMACCalculator.getBPWXmlResponseMac(response, apiResultKey);
        if (!response.getMac().equals(NEUTRAL_MAC_VALUE) && !response.getMac().equals(responseMac))
            throw new VPosClientException("Response MAC is not valid");


        if (response.getData() != null && response.getData().getOperation() != null) {
            String operationMac = responseMACCalculator.getOperationMac(response.getData().getOperation(), apiResultKey);
            if (!response.getData().getOperation().getMac().equals(NEUTRAL_MAC_VALUE) && !response.getData().getOperation().getMac().equals(operationMac))
                throw new VPosClientException("Operation MAC is not valid");

        }

        if (response.getData() != null && response.getData().getAuthorization() != null) {
            for (Authorization authorization : response.getData().getAuthorization()) {
                String authorizationMac = responseMACCalculator.getAuthorizationMac(authorization, apiResultKey);
                if (!authorization.getMac().equals(NEUTRAL_MAC_VALUE) && !authorization.getMac().equals(authorizationMac))
                    throw new VPosClientException("Authorization MAC is not valid");
            }
        }
    }

}