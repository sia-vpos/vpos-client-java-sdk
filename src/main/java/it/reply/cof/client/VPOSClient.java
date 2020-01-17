package it.reply.cof.client;

import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.dto.response.RefundResponseDto;
import it.reply.cof.utils.exception.COFException;

import java.util.Map;

/**
 * Client interface used to perform common requests and validate returned results from SIA.
 * Different implementations are provided to offer a full spectre of tools fitting security and
 * and common scenarios requirements.
 *
 * @author Gabriel Raul Marini
 */
public interface VPOSClient {

    String getHtmlPaymentDocument(PaymentInfo paymentInfo) throws COFException;

    void verifyURL(Map<String, String> values) throws COFException;

    Auth3DSDto start3DSAuth(Auth3DSDto dto) throws COFException;

    void confirmPayment(ConfirmRequestDto dto);

    RefundResponseDto refundPayment(RefundRequestDto dto) throws COFException;

    void verifyPayment(VerifyRequestDto dto);

    void getOrderStatus(OrderStatusRequestDto dto);

    void setProxy(String proxyName, Integer proxyPort);
}
