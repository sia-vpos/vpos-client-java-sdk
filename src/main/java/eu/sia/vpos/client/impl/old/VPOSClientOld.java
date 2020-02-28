package eu.sia.vpos.client.impl.old;

import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.response.*;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.request.PaymentInfo;

import java.util.Map;

/**
 * Client interface used to perform common requests to SIA VPOS.
 * Different implementations are provided to offer a full spectre of tools fitting security and
 * and common scenarios requirements.
 *
 * @author Gabriel Raul Marini
 */
public interface VPOSClientOld {

    /**
     * Perform the injection of a custom HTML redirect template
     *
     * @param base64 encoded string of the HTML custom template
     * @param delay  milliseconds to wait before redirecting to SIA VPOS page
     * @throws VPosClientException
     */
    void injectHtmlTemplate(String base64, Integer delay) throws VPosClientException;

    /**
     * Create an HTML document ready to use for payment initiation. The method returns
     * the custom template with an hidden form containing all the payment parameters in case of
     * precedent HTML injection. Default template is returned otherwise.
     *
     * @param paymentInfo data transfer object containing all the payment parameters
     * @param urlApos     VPOS redirect base path
     * @return the base64 format of the HTML document
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    String getHtmlPaymentDocument(PaymentInfo paymentInfo, String urlApos) throws VPosClientException;

    /**
     * Tokenize a credit card
     *
     * @param shopId  identifier of the merchant
     * @param urlBack redirect url in case of payment failure
     * @param urlDone redirect url in case of success
     * @param urlms   endpoint of merchant backend
     * @param urlApos endpoint of SIA VPOS systems
     * @return the base64 format of the HTML document
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    String tokenize(String shopId, String urlBack, String urlDone, String urlms, String urlApos) throws VPosClientException;

    /**
     * Validate the result of a payment initiation verifying the integrity of the data contained in URMLS/URLDONE
     *
     * @param values      parameters received from SIA VPOS
     * @param receivedMac to compare with the calculated one
     * @throws VPosClientException in case of data integrity validation failure
     */
    void verifyURL(Map<String, String> values, String receivedMac) throws VPosClientException;

    /**
     * @param dto data transfer object containing all the required parameters to perform the first step of a
     *            3DS authorization
     * @return the outcome of the operation with the relative additional infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    Auth3DSResponseDto start3DSAuth(Auth3DSDto dto) throws VPosClientException;

    /**
     * @param dto data transfer object containing all the required parameters to perform the second step of a
     *            3DS authorization
     * @return the outcome of the operation with the relative additional infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    Auth3DSStep2ResponseDto start3DSAuthStep2(Auth3DSStep2RequestDto dto) throws VPosClientException;

    /**
     * @param dto data transfer object containing all the required parameters to perform a
     *            payment confirmation
     * @return the outcome of the operation with the relative additional infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    CaptureResponse confirmTransaction(CaptureRequest dto) throws VPosClientException;

    /**
     * @param dto data transfer object containing all the required parameters to perform a
     *            payment refund
     * @return the outcome of the operation with the relative additional infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    RefundResponse refundPayment(RefundRequest dto) throws VPosClientException;

    /**
     * @param dto data transfer object containing all the required parameters to perform a
     *            verify request
     * @return the outcome of the operation with the relative additional infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    VerifyResponseDto verifyRequest(VerifyRequestDto dto) throws VPosClientException;

    /**
     * @param dto data transfer object containing all the required parameters to perform an
     *            order status request
     * @return the outcome of the operation with the relative additional infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    OrderStatusResponse getOrderStatus(OrderStatusRequest dto) throws VPosClientException;

    /**
     * Set a basic proxy configuration
     *
     * @param proxyName url of the proxy location
     * @param proxyPort port on which proxy is enabled
     */
    void setProxy(String proxyName, Integer proxyPort);
}
