package it.reply.cof.client;

import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.HTMLGenerator;
import it.reply.cof.utils.exception.COFException;

import java.util.logging.Logger;

public abstract class VPOSClientAbstract implements VPOSClient {

    protected HTMLGenerator htmlTool;

    public VPOSClientAbstract() {
        htmlTool = new HTMLGenerator();
    }

    public abstract void injectMasterTemplate();

    public String getPaymentHtmlDocument(PaymentInfo paymentInfo) {

    }

    public abstract void verifyResponse() throws COFException;
}
