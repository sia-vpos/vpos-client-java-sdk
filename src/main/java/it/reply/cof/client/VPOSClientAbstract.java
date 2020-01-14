package it.reply.cof.client;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.request.Request;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.RefundRequestDto;
import it.reply.cof.utils.HTMLGenerator;
import it.reply.cof.utils.builders.RequestBuilder;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.HmacCalculator;

public abstract class VPOSClientAbstract implements VPOSClient {

    protected HTMLGenerator htmlTool;
    protected String key;
    protected AposPaymentClient aposClient;
    protected RequestBuilder requestBuilder;
    protected HmacCalculator hmacCalculator;

    public abstract void injectMasterTemplate();

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

    public abstract void verifyResponse() throws COFException;

    @Override
    public BPWXmlResponse refund(RefundRequestDto dtoRequest) throws COFException {
        BPWXmlRequest request = requestBuilder.buildRefundRequest(dtoRequest);
        BPWXmlResponse response = aposClient.executeCall(request);
        StringBuilder sb = new StringBuilder();
        sb.append(response.getTimestamp());
        sb.append("&");
        sb.append(response.getResult());
        hmacCalculator.calculate(sb.toString(), key);

        return response;
    }
}
