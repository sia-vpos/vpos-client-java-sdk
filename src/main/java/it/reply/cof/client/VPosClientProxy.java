package it.reply.cof.client;

import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.RefundRequestDto;
import it.reply.cof.utils.exception.COFException;

public class VPosClientProxy extends VPOSClientAbstract{

    public VPosClientProxy(String proxyName, Integer port){

    }

    @Override
    public void injectMasterTemplate() {

    }

    @Override
    public String getPaymentHtmlDocument(PaymentInfo paymentInfo, Boolean onlyVirtualized) throws COFException {
        return null;
    }

    @Override
    public void verifyResponse() throws COFException {

    }

    @Override
    public void onlineAuthorizationRequest() {

    }

    @Override
    public void deferredAuthorizationRequest() {

    }

    @Override
    public void threeDSAuthorizationRequest() {

    }

    @Override
    public String refund(RefundRequestDto dtoRequest) throws COFException {
        return null;
    }
}
