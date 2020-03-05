	package eu.sia.vpos.client;

import eu.sia.vpos.client.request.*;

import eu.sia.vpos.client.response.*;

import eu.sia.vpos.client.utils.exception.VPosClientException;

//import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Put here Javadoc documentation. All public interface, classes and methods must have a Javadoc comment
 * @since put here version number, e.g. 1.0.0
 */
public interface Client {
	
    //AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) throws VPosClientException;

    ThreeDSAuthorization0Response threeDSAuthorize0(ThreeDSAuthorization0Request threeDSAuthorization0Request) throws VPosClientException, UnsupportedEncodingException;

    ThreeDSAuthorization1Response threeDSAuthorize1(ThreeDSAuthorization1Request threeDSAuthorization1Request) throws VPosClientException;

    ThreeDSAuthorization2Response threeDSAuthorize2(ThreeDSAuthorization2Request threeDSAuthorization2Request) throws VPosClientException;

    //Confirm
    CaptureResponse capture(CaptureRequest captureRequest) throws VPosClientException;

    RefundResponse refund(RefundRequest refundRequest) throws  VPosClientException;

    OrderStatusResponse getOrderStatus(OrderStatusRequest orderStatusRequest) throws VPosClientException;

    boolean verifyMAC(String url) throws VPosClientException;

    //boolean verifyMAC(HttpServletRequest httpServletRequest) throws VPosClientException;

    String buildHTMLRedirectFragment(PaymentInfo PaymentInfo) throws VPosClientException;
}
