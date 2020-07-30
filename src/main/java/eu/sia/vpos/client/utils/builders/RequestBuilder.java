package eu.sia.vpos.client.utils.builders;

import eu.sia.vpos.client.request.RefundRequest;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.request.xml.*;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.constants.VPosConstants;
import eu.sia.vpos.client.utils.encryption.AESEncoder;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.Encoder;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;

import java.util.Date;

/**
 * Utility class used to convert a DTO into its corresponding XML request format.
 *
 * @author gab.marini
 * @author a.simonetti
 */
public class RequestBuilder {

    private String key;
    private Encoder encoder;

    /**
     * Creates an instance of the builder with HMAC-SHA-256 as default algorithm for
     * MAC calculation
     *
     * @param key used to encode the MAC
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    public RequestBuilder(String key) throws VPosClientException {
        this(key, MacAlgorithms.HMAC_SHA_256);
    }

    /**
     * Creates an instance of the builder using the specified algorithm for MAC calculation
     *
     * @param key       used to encode the MAC
     * @param algorithm used to calculate the MAC
     * @throws VPosClientException in case of failure (see exception message for more info)
     */
    public RequestBuilder(String key, MacAlgorithms algorithm) throws VPosClientException {
        this.key = key;
        encoder = new Encoder(algorithm);
    }

    /**
     * Build the XML request for REFUND operations
     *
     * @param dtoRequest object containing the necessary infos to perform a REFUND
     * @return the XML request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildRefundRequest(RefundRequest dtoRequest, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.REFUND, reqDate);

        eu.sia.vpos.client.request.xml.RefundRequest refund = new eu.sia.vpos.client.request.xml.RefundRequest(reqDate);
        //HEADER
        refund.getHeader().setOperatorId(dtoRequest.getOperatorId());
        refund.getHeader().setShopId(shopId);

        refund.setAmount(dtoRequest.getAmount());
        refund.setCurrency(dtoRequest.getCurrency());
        refund.setExponent(dtoRequest.getExponent());
        refund.setOpDescr(dtoRequest.getOpDescr());
        refund.setOptions(dtoRequest.getOptions());
        refund.setOrderId(dtoRequest.getOrderId());
        refund.setTransactionId(dtoRequest.getTransactionId());

        Data data = new Data();
        data.setRefundRequest(refund);
        request.setData(data);

        request.getRequest().setMac(encoder.getMac(MapBuilder.getRefundMap(request), key));
        return request;
    }

    /**
     * Build the XML request for accounting operations
     *
     * @param dtoRequest object containing the necessary infos to perform the accounting of an existing transaction
     * @return the XML request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildBookingRequest(CaptureRequest dtoRequest, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.ACCOUNTING, reqDate);

        GeneralRequest bookingRequest = new GeneralRequest(reqDate);

        //HEADER
        bookingRequest.getHeader().setShopId(shopId);
        bookingRequest.getHeader().setOperatorId(dtoRequest.getOperatorId());

        //BOOKING REQUEST
        bookingRequest.setTransactionId(dtoRequest.getTransactionId());
        bookingRequest.setOrderId(dtoRequest.getOrderId());
        bookingRequest.setAmount(dtoRequest.getAmount());
        bookingRequest.setCurrency(dtoRequest.getCurrency());
        bookingRequest.setExponent(dtoRequest.getExponent());
        bookingRequest.setOpDescr(dtoRequest.getOpDescr());

        Data data = new Data();
        data.setAccounting(bookingRequest);
        request.setData(data);

        request.getRequest().setMac(encoder.getMac(MapBuilder.getBookingMap(request), key));
        return request;
    }

    /**
     * Build the XML request for ORDERSTATUS operation
     *
     * @param statusRequest object containing the necessary infos to perform an ORDERSTATUS request
     * @return the xml request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildOrderStatusRequest(OrderStatusRequest statusRequest, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.ORDERSTATUS, reqDate);

        StatusRequest status = new StatusRequest(reqDate);
        //HEADER
        status.getHeader().setShopId(shopId);
        status.getHeader().setOperatorId(statusRequest.getOperatorId());

        //ORDER STATUS REQUEST
        status.setOrderId(statusRequest.getOrderId());
        status.setProductRef(statusRequest.getProductRef());

        Data data = new Data();
        data.setOrderStatusRequest(status);
        request.setData(data);

        request.getRequest().setMac(encoder.getMac(MapBuilder.getOrderStatusMap(request), key));
        return request;

    }

    /**
     * Build the XML request for AUTHORIZATION operation
     *
     * @param request object containing the necessary infos to perform an AUTHORIZATION request
     * @return the xml request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildOnlineAuthorizationRequest(AuthorizationRequest request, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest bpwXmlRequest = getBPWXmlRequest(Operations.PARAMETERS.ONLINEAUTHORIZATION, reqDate);
        OnlineAuthorizationRequest authRequest = new OnlineAuthorizationRequest(reqDate);

        //Header
        authRequest.getHeader().setOperatorId(request.getOperatorId());
        authRequest.getHeader().setShopId(shopId);

        //
        authRequest.setOrderId(request.getOrderId());
        authRequest.setPan(request.getPan());
        authRequest.setCvv2(request.getCvv2());
        authRequest.setExpDate(request.getExpDate());
        authRequest.setAmount(request.getAmount());
        authRequest.setCurrency(request.getCurrency());
        authRequest.setExponent(request.getExponent());
        authRequest.setAccountingMode(request.getAccountingMode());
        authRequest.setNetwork(request.getNetwork());
        authRequest.setEmailCH(request.getEmailCh());
        authRequest.setUserId(request.getUserId());
        authRequest.setAcquirer(request.getAcquirer());
        authRequest.setUsrAuthFlag(request.getUsrAuthFlag());
        authRequest.setIpAddress(request.getIpAddress());
        authRequest.setOpDescr(request.getOpDescr());
        authRequest.setCreatePanAlias(request.getCreatePanAlias());
        authRequest.setAntiFraud(request.getAntiFraud());
        authRequest.setProductRef(request.getProductRef());
        authRequest.setName(request.getName());
        authRequest.setSurname(request.getSurname());
        authRequest.setTaxId(request.getTaxId());
        authRequest.settRecurr(request.gettRecurr());
        authRequest.setcRecurr(request.getcRecurr());
        authRequest.setInstallmentsNumber(request.getInstallmentsNumber());
        Data data = new Data();
        data.setOnlineAuthorizationRequest(authRequest);

        bpwXmlRequest.setData(data);
        bpwXmlRequest.getRequest().setMac(encoder.getMac(MapBuilder.getOnlineAuthorizationMap(bpwXmlRequest), key));

        return bpwXmlRequest;
    }

    /**
     * Build the XML request for ThreeDSAuthorization step 0 operation
     *
     * @param authRequest object containing the necessary infos to perform a ThreeDS2Authorize0 request
     * @return the xml request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildThreeDS2Authorize0(ThreeDSAuthorization0Request authRequest, String shopId, String apiResultKey) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DS2STEP0, reqDate);
        Auth3DS2AuthorizationStep0Request auth3DSStep0 = new Auth3DS2AuthorizationStep0Request(reqDate);

        //Header
        auth3DSStep0.getHeader().setOperatorId(authRequest.getOperatorId());
        auth3DSStep0.getHeader().setShopId(shopId);

        //
        auth3DSStep0.setOrderId(authRequest.getOrderId());
        auth3DSStep0.setPan(authRequest.getPan());
        auth3DSStep0.setCvv2(authRequest.getCvv2());
        auth3DSStep0.setExpDate(authRequest.getExpDate());
        auth3DSStep0.setAmount(authRequest.getAmount());
        auth3DSStep0.setCurrency(authRequest.getCurrency());
        auth3DSStep0.setExponent(authRequest.getExponent());
        auth3DSStep0.setAccountingMode(authRequest.getAccountingMode());
        auth3DSStep0.setNetwork(authRequest.getNetwork());
        auth3DSStep0.setEmailCH(authRequest.getEmailCh());
        auth3DSStep0.setNameCH(authRequest.getNameCh());
        auth3DSStep0.setUserId(authRequest.getUserId());
        auth3DSStep0.setAcquirer(authRequest.getAcquirer());
        auth3DSStep0.setIpAddress(authRequest.getIpAddress());
        auth3DSStep0.setUsrAuthFlag(authRequest.getUsrAuthFlag());
        auth3DSStep0.setOpDescr(authRequest.getOpDescr());
        auth3DSStep0.setAntifraud(authRequest.getAntifraud());
        auth3DSStep0.setNotifyUrl(authRequest.getNotifyUrl());
        auth3DSStep0.setProductRef(authRequest.getProductRef());
        auth3DSStep0.setName(authRequest.getName());
        auth3DSStep0.setSurname(authRequest.getSurname());
        auth3DSStep0.setTaxId(authRequest.getTaxId());
        auth3DSStep0.setCreatePanAlias(authRequest.getCreatePanAlias());
        //3DSDATAJSON
        //System.out.println("URL ENCODED: "+URLEncoder.encode(AESEncoder.encode3DSData(dtoRequest.getMerchantKey(),dtoRequest.getThreeDSData().toString()), StandardCharsets.UTF_8.toString()));
        //System.out.println("NORMAL : "+AESEncoder.encode3DSData(dtoRequest.getMerchantKey(),dtoRequest.getThreeDSData().toString()));

        auth3DSStep0.setThreeDSData(AESEncoder.encode3DSData(apiResultKey, authRequest.getThreeDSData().toString()));
        auth3DSStep0.setCprof(authRequest.getcProf());
        auth3DSStep0.setThreeDSMtdNotifyUrl(authRequest.getThreeDSMtdNotifyUrl());
        auth3DSStep0.setChallengeWinSize(authRequest.getChallengeWinSize());
        auth3DSStep0.setOptions(authRequest.getOptions());

        auth3DSStep0.settRecurr(authRequest.gettRecurr());
        auth3DSStep0.setcRecurr(authRequest.getcRecurr());
        auth3DSStep0.setInstallmentsNumber(authRequest.getInstallmentsNumber());

        Data data = new Data();
        data.setAuth3DS2AuthorizationStep0Request(auth3DSStep0);
        request.setData(data);
        request.getRequest().setMac(encoder.getMac(MapBuilder.getThreeDS2Authorize0Map(request), key));
        /*try {
            request.getData().getAuth3DS2AuthorizationStep0Request().setThreeDSData(URLEncoder.encode(AESEncoder.encode3DSData(dtoRequest.getMerchantKey(),dtoRequest.getThreeDSData().toString()), StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            throw new VPosClientException("Error while building authorization request");
        }*/
        return request;
    }

    /**
     * Build the XML request for ThreeDSAuthorization step 1 operation
     *
     * @param request object containing the necessary infos to perform a ThreeDS2Authorize1 request
     * @return the xml request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildThreeDS2Authorize1(ThreeDSAuthorization1Request request, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest bpwXmlRequest = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DS2STEP1, reqDate);
        Auth3DS2AuthorizationStep1Request auth3DSStep1 = new Auth3DS2AuthorizationStep1Request(reqDate);

        //Header
        auth3DSStep1.getHeader().setOperatorId(request.getOperatorId());
        auth3DSStep1.getHeader().setShopId(shopId);

        auth3DSStep1.setThreeDSMtdComplInd(request.getThreeDSMtdComplInd());
        auth3DSStep1.setThreeDSTransID(request.getThreeDSTransId());

        Data data = new Data();
        data.setAuth3DS2AuthorizationStep1Request(auth3DSStep1);
        bpwXmlRequest.setData(data);
        bpwXmlRequest.getRequest().setMac(encoder.getMac(MapBuilder.getThreeDS2Authorize1Map(bpwXmlRequest), key));
        return bpwXmlRequest;
    }

    /**
     * Build the XML request for ThreeDSAuthorization step 2 operation
     *
     * @param request object containing the necessary infos to perform a ThreeDS2Authorize2 request
     * @return the xml request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildThreeDS2Authorize2(ThreeDSAuthorization2Request request, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest bpwXmlRequest = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DS2STEP2, reqDate);
        Auth3DS2AuthorizationStep2Request auth3DSStep2 = new Auth3DS2AuthorizationStep2Request(reqDate);

        //Header
        auth3DSStep2.getHeader().setOperatorId(request.getOperatorId());
        auth3DSStep2.getHeader().setShopId(shopId);

        auth3DSStep2.setThreeDSTransID(request.getThreeDSTransId());

        Data data = new Data();
        data.setAuth3DS2AuthorizationStep2Request(auth3DSStep2);
        bpwXmlRequest.setData(data);
        bpwXmlRequest.getRequest().setMac(encoder.getMac(MapBuilder.getThreeDS2Authorize2Map(bpwXmlRequest), key));
        return bpwXmlRequest;
    }

    private BPWXmlRequest getBPWXmlRequest(String operation, Date date) {
        BPWXmlRequest request = new BPWXmlRequest();
        request.setRelease(VPosConstants.RELEASE);
        request.setRequest(new Request(operation, date));
        return request;
    }


}
