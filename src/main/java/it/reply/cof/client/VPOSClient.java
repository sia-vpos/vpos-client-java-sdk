package it.reply.cof.client;

import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.dto.response.*;
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

    /**
     * Perform the injection of a custom HTML redirect template
     *
     * @param base64 encoded string of the HTML custom template
     * @param delay  milliseconds to wait before redirecting to SIA VPOS page
     * @throws COFException
     */
    void injectHtmlTemplate(String base64, Integer delay) throws COFException;

    /**
     * Create an HTML document ready to use for payment initiation. The method returns
     * the custom template with an embedded hidden form containing all the payment parameters in case of
     * precedent HTML injection. Default template is returned otherwise.
     *
     * @param paymentInfo data transfer object containing all the payment parameters
     * @param urlApos     VPOS redirect base path
     * @return the base64 encoded HTML document
     * @throws COFException in case of failure (see exception message for more info)
     */
    String getHtmlPaymentDocument(PaymentInfo paymentInfo, String urlApos) throws COFException;

    /**
     * Validate the result of a payment initiation verifying the integrity of the data contained in URMLS/URLDONE
     *
     * @param values      parameters received from SIA VPOS
     * @param receivedMac to compare with the calculated one
     * @throws COFException in case of data integrity validation failure
     */
    void verifyURL(Map<String, String> values, String receivedMac) throws COFException;

    /**
     * @param dto data transfer object containing all the required parameters to perform the first step of a
     *            3DS authorization
     * @return the outcome of the operation with the relative additional infos
     * @throws COFException in case of failure (see exception message for more info)
     */
    Auth3DSResponseDto start3DSAuth(Auth3DSDto dto) throws COFException;

    /**
     * @param dto data transfer object containing all the required parameters to perform the second step of a
     *            3DS authorization
     * @return the outcome of the operation with the relative additional infos
     * @throws COFException in case of failure (see exception message for more info)
     */
    Auth3DSStep2ResponseDto start3DSAuthStep2Dto(Auth3DSStep2RequestDto dto) throws COFException;

    /**
     * @param dto data transfer object containing all the required parameters to perform a
     *            payment confirmation
     * @return the outcome of the operation with the relative additional infos
     * @throws COFException in case of failure (see exception message for more info)
     */
    ConfirmationResponseDto confirmPayment(ConfirmRequestDto dto) throws COFException;

    /**
     * @param dto data transfer object containing all the required parameters to perform a
     *            payment refund
     * @return the outcome of the operation with the relative additional infos
     * @throws COFException in case of failure (see exception message for more info)
     */
    RefundResponseDto refundPayment(RefundRequestDto dto) throws COFException;

    /**
     * @param dto data transfer object containing all the required parameters to perform a
     *            verify payment operation
     * @return the outcome of the operation with the relative additional infos
     * @throws COFException in case of failure (see exception message for more info)
     */
    VerifyResponseDto verifyPayment(VerifyRequestDto dto) throws COFException;

    /**
     * @param dto data transfer object containing all the required parameters to perform an
     *            order status request
     * @return the outcome of the operation with the relative additional infos
     * @throws COFException in case of failure (see exception message for more info)
     */
    OrderStatusResponseListDto getOrderStatus(OrderStatusRequestDto dto) throws COFException;

    /**
     * Set a basic proxy configuration
     *
     * @param proxyName url of the proxy location
     * @param proxyPort port on which proxy is enabled
     */
    void setProxy(String proxyName, Integer proxyPort);
}
