package it.reply.cof.client;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.request.RefundRequest;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.RefundRequestDto;
import it.reply.cof.utils.HTMLGenerator;
import it.reply.cof.utils.RequestBuilder;
import it.reply.cof.utils.exception.COFException;

public abstract class VPOSClientAbstract implements VPOSClient {

    protected HTMLGenerator htmlTool;

    public VPOSClientAbstract() {
        htmlTool = new HTMLGenerator();
    }

    public abstract void injectMasterTemplate();

    public String getPaymentHtmlDocument(PaymentInfo paymentInfo) throws COFException {
        if (paymentInfo.getAccountingMode() == null || paymentInfo.getAmount() == null ||
                paymentInfo.getAuthorMode() == null || paymentInfo.getCurrency() == null ||
                paymentInfo.getExponent() == null || paymentInfo.getOrderId() == null ||
                paymentInfo.getShopId() == null || paymentInfo.getUrlBack() == null ||
                paymentInfo.getUrlDone() == null|| paymentInfo.getUrlMs() == null /*||
               paymentInfo.getMac() == null*/
        )
            throw new COFException("One or more mandatory field were not specified");


        return null;
    }

    public abstract void verifyResponse() throws COFException;

    @Override
    public String refund(RefundRequestDto dtoRequest) throws COFException {
        BPWXmlRequest request= RequestBuilder.buildRefundRequest(dtoRequest);




        return null;
    }
}
