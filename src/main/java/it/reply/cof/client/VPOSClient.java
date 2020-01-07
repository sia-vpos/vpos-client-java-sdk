package it.reply.cof.client;

import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.exception.COFException;

/**
 * VPOS Client interface used to perform common requests and validate returned results from SIA.
 * Different implementations are provided to offer a full spectre of tools fitting security and
 * and common scenarios requirements.
 *
 * @author gab.marini
 */
public interface VPOSClient {

    void injectMasterTemplate();

    String getPaymentHtmlDocument(PaymentInfo paymentInfo);

    void verifyResponse() throws COFException;

}
