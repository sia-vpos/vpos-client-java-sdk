package eu.sia.vpos.client;

import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.response.*;
import eu.sia.vpos.client.utils.exception.VPosClientException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Client interface used to perform common requests to SIA VPOS.
 *
 * @since  1.0.0
 */
public interface Client {

    /**
     * This operation allows to forward authorization requests to the circuits
     *
     * @param authorizationRequest authorization request object containing all the parameters to perform the operation
     * @return response object with the operation outcome infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) throws VPosClientException;

    /**
     * The 3DS 2.x authorization request message permits to send authorization requests to the circuits.
     * @param threeDSAuthorization0Request authorization request object containing all the parameters to perform the operation
     * @return response object with the operation outcome infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    ThreeDSAuthorization0Response threeDSAuthorize0(ThreeDSAuthorization0Request threeDSAuthorization0Request) throws VPosClientException;

    /**
     *The 3DS 2.x authorization request message step 1 permits to forward authentication requests to the circuits once a call to the ACS 3DS method has been performed.
     * The message THREEDSAUTHORIZATION1 must arrive within 8 minutes from the time the original message THREEDSAUTHORIZATION0 is sent.
     * @param threeDSAuthorization1Request authorization request object containing all the parameters to perform the operation
     * @return response object with the operation outcome infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    ThreeDSAuthorization1Response threeDSAuthorize1(ThreeDSAuthorization1Request threeDSAuthorization1Request) throws VPosClientException;

    /**
     * The 3DS 2.x authorization request message step 2 permits to forward authentication requests to the circuits once an user authentication challenge has been performed.
     * The message THREEDSAUTHORIZATION2 must arrive within 8 minutes from the time the original message THREEDSAUTHORIZATION0 or THREEDSAUTHORIZATION1 are sent.
     * @param threeDSAuthorization2Request authorization request object containing all the parameters to perform the operation
     * @return response object with the operation outcome infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    ThreeDSAuthorization2Response threeDSAuthorize2(ThreeDSAuthorization2Request threeDSAuthorization2Request) throws VPosClientException;

    /**
     * A booking request transaction permits the SIA VPOS system to forward to the competent acquirer the request for the booking of
     * an authorization previously granted with a deferred booking.
     * @param captureRequest capture request object containing all the parameters to perform the operation
     * @return response object with the operation outcome infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    CaptureResponse capture(CaptureRequest captureRequest) throws VPosClientException;

    /**
     * Refund request
     * @param refundRequest refund request object containing all the parameters to perform the operation
     * @return response object with the operation outcome infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    RefundResponse refund(RefundRequest refundRequest) throws VPosClientException;

    /**
     * This request returns the current status of an order, including all the related authorization transactions
     * @param orderStatusRequest object containing all the required parameters to perform an order status request
     * @return object with the operation outcome infos
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    OrderStatusResponse getOrderStatus(OrderStatusRequest orderStatusRequest) throws VPosClientException;

    /**
     * This function verifies the mac of the urlDone/UrlMs generated after a redirect operation.
     * @param url urlDone/urlMs string
     * @return true if the mac is verified, false otherwise.
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    boolean verifyMAC(String url) throws VPosClientException;

    /**
     * This function verifies the mac of the urlDone/UrlMs generated after a redirect operation.
     * @param httpServletRequest httpServletRequest Object  of the redirect request
     * @return true if the mac is verified, false otherwise.
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    boolean verifyMAC(HttpServletRequest httpServletRequest) throws VPosClientException;

    /**
     * Create an HTML fragment to use for payment initiation.
     * @param PaymentInfo object containing all the payment parameters
     * @return the HTML fragment
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    String buildHTMLRedirectFragment(PaymentInfo PaymentInfo) throws VPosClientException;
}
