package it.reply.cof.client;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.HTMLGenerator;
import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.builders.RequestBuilder;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.HmacCalculator;

public abstract class VPOSClientAbstract implements VPOSClient {

    protected HTMLGenerator htmlTool;
    protected String key;
    protected AposPaymentClient aposClient;
    protected RequestBuilder requestBuilder;
    protected HmacCalculator hmacCalculator;

    public VPOSClientAbstract(String key) throws COFException {
        requestBuilder = new RequestBuilder(key);
        hmacCalculator = new HmacCalculator();
        htmlTool = new HTMLGenerator();
        this.key = key;
    }

    public VPOSClientAbstract(String key, MacAlgorithms algorithm) throws COFException {
        requestBuilder = new RequestBuilder(key, algorithm);
        hmacCalculator = new HmacCalculator(algorithm);
        this.key = key;
    }

    public String getPaymentHtmlDocument(PaymentInfo paymentInfo) throws COFException {
        if (paymentInfo.getAccountingMode() == null || paymentInfo.getAmount() == null ||
                paymentInfo.getAuthorMode() == null || paymentInfo.getCurrency() == null ||
                paymentInfo.getExponent() == null || paymentInfo.getOrderId() == null ||
                paymentInfo.getShopId() == null || paymentInfo.getUrlBack() == null ||
                paymentInfo.getUrlDone() == null || paymentInfo.getUrlMs() == null /*||
               paymentInfo.getMac() == null*/
        )
            throw new COFException("One or more mandatory field were not specified");

        return null;
    }

    @Override
    public BPWXmlResponse refund(RefundRequestDto dtoRequest) throws COFException {
        BPWXmlRequest request = requestBuilder.buildRefundRequest(dtoRequest);
        BPWXmlResponse response = aposClient.executeCall(request);

        //TODO
        //Verify MAC
        verifyMacResponse(response);
        return response;
    }

    private void verifyMacResponse(BPWXmlResponse response) throws COFException {
        StringBuilder sb = new StringBuilder();
        sb.append(response.getTimestamp());
        sb.append("&");
        sb.append(response.getResult());
        String responseHmac = hmacCalculator.calculate(sb.toString(), key);
        if (!responseHmac.equals(response.getMac())) {
            throw new COFException("MAC Response not valid");
        }
    }

    @Override
    public void setProxy(String proxyName, Integer proxyPort) {
        this.aposClient.setProxy(proxyName, proxyPort);
    }


    @Override
    public String getHtmlPaymentDocument(PaymentInfo paymentInfo, Boolean onlyVirtualized) throws COFException {
        //TODO
        return null;
    }

    @Override
    public void verifyURLMS(String urlms) throws COFException {
        //TODO
    }

    @Override
    public void verifyURLDone(String urlDone) throws COFException {
        //TODO
    }

    @Override
    public void start3DSAuth(Auth3DSDto dto) {
        //TODO
    }

    @Override
    public void confirmPayment(ConfirmRequestDto dto) {
        //TODO
    }

    @Override
    public void refundPayment(RefundRequestDto dto) {
        //TODO
    }

    @Override
    public void verifyPayment(VerifyRequestDto dto) {
        //TODO
    }

    @Override
    public void getOrderStatus(OrderStatusRequestDto dto) {
        //TODO
    }
}
