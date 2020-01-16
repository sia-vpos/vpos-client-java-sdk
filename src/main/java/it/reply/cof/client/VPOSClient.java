package it.reply.cof.client;

import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.exception.COFException;

/**
 * Client interface used to perform common requests and validate returned results from SIA.
 * Different implementations are provided to offer a full spectre of tools fitting security and
 * and common scenarios requirements.
 *
 * @author gab.marini
 */
public interface VPOSClient {

    String getHtmlPaymentDocument(PaymentInfo paymentInfo, Boolean onlyVirtualized) throws COFException;

    void verifyURLMS(String urlms) throws COFException;

    void verifyURLDone(String urlDone) throws COFException;

    void start3DSAuth(Auth3DSDto dto);

    void confirmPayment(ConfirmRequestDto dto);

    void refundPayment(RefundRequestDto dto);

    void verifyPayment(VerifyRequestDto dto);

    void getOrderStatus(OrderStatusRequestDto dto);

    BPWXmlResponse refund(RefundRequestDto dtoRequest) throws COFException;

    void setProxy(String proxyName, Integer proxyPort);
}
