package eu.sia.vpos.client.utils.builders;

import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.request.RefundRequest;

import eu.sia.vpos.client.request.xml.*;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.constants.VPosConstants;
import eu.sia.vpos.client.utils.encryption.AESEncoder;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.Encoder;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
     * @param dtoRequest object containing the necessary infos to perform an ORDERSTATUS request
     * @return the xml request
     * @throws VPosClientException
     */
    public BPWXmlRequest buildOrderStatusRequest(OrderStatusRequest dtoRequest,String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.ORDERSTATUS, reqDate);

        StatusRequest status = new StatusRequest(reqDate);
        //HEADER
        status.getHeader().setShopId(shopId);
        status.getHeader().setOperatorId(dtoRequest.getOperatorId());

        //ORDER STATUS REQUEST
        status.setOrderId(dtoRequest.getOrderId());
        status.setProductRef(dtoRequest.getProductRef());

        Data data = new Data();
        data.setOrderStatusRequest(status);
        request.setData(data);

        request.getRequest().setMac(encoder.getMac(MapBuilder.getOrderStatusMap(request), key));
        return request;

    }


    public BPWXmlRequest buildThreeDS2Authorize0(ThreeDSAuthorization0Request dtoRequest, String shopId) throws VPosClientException, UnsupportedEncodingException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DS2STEP0, reqDate);
        Auth3DS2AuthorizationStep0Request auth3DSStep0 = new Auth3DS2AuthorizationStep0Request(reqDate);

        //Header
        auth3DSStep0.getHeader().setOperatorId(dtoRequest.getOperatorId());
        auth3DSStep0.getHeader().setShopId(shopId);

        //
        auth3DSStep0.setOrderId(dtoRequest.getOrderId());
        auth3DSStep0.setPan(dtoRequest.getPan());
        auth3DSStep0.setCvv2(dtoRequest.getCvv2());
        auth3DSStep0.setExpDate(dtoRequest.getExpDate());
        auth3DSStep0.setAmount(dtoRequest.getAmount());
        auth3DSStep0.setCurrency(dtoRequest.getCurrency());
        auth3DSStep0.setExponent(dtoRequest.getExponent());
        auth3DSStep0.setAccountingMode(dtoRequest.getAccountingMode());
        auth3DSStep0.setNetwork(dtoRequest.getNetwork());
        auth3DSStep0.setEmailCH(dtoRequest.getEmailCh());
        auth3DSStep0.setNameCH(dtoRequest.getNameCh());
        auth3DSStep0.setUserId(dtoRequest.getUserId());
        auth3DSStep0.setAcquirer(dtoRequest.getAcquirer());
        auth3DSStep0.setIpAddress(dtoRequest.getIpAddress());
        auth3DSStep0.setUsrAuthFlag(dtoRequest.getUsrAuthFlag());
        auth3DSStep0.setOpDescr(dtoRequest.getOpDescr());
        auth3DSStep0.setAntifraud(dtoRequest.getAntifraud());
        auth3DSStep0.setNotifyUrl(dtoRequest.getNotifyUrl());
        auth3DSStep0.setProductRef(dtoRequest.getProductRef());
        auth3DSStep0.setName(dtoRequest.getName());
        auth3DSStep0.setSurname(dtoRequest.getSurname());
        auth3DSStep0.setTaxId(dtoRequest.getTaxId());
        auth3DSStep0.setCreatePanAlias(dtoRequest.getCreatePanAlias());
        //3DSDATAJSON
        auth3DSStep0.setThreeDSData(AESEncoder.encode3DSData(dtoRequest.getMerchantKey(),dtoRequest.getThreeDSData().toString()));
        auth3DSStep0.setCprof(dtoRequest.getcProf());
        auth3DSStep0.setThreeDSMtdNotifyUrl(dtoRequest.getThreeDSMtdNotifyUrl());
        auth3DSStep0.setChallengeWinSize(dtoRequest.getChallengeWinSize());
        auth3DSStep0.setOptions(dtoRequest.getOptions());

        Data data = new Data();
        data.setAuth3DS2AuthorizationStep0Request(auth3DSStep0);
        request.setData(data);
        request.getRequest().setMac(encoder.getMac(MapBuilder.getThreeDS2Authorize0Map(request), key));
        auth3DSStep0.setThreeDSData(auth3DSStep0.getThreeDSData());
        return request;
    }

    public BPWXmlRequest buildThreeDS2Authorize1(ThreeDSAuthorization1Request dtoRequest, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DS2STEP1, reqDate);
        Auth3DS2AuthorizationStep1Request auth3DSStep1 = new Auth3DS2AuthorizationStep1Request(reqDate);

        //Header
        auth3DSStep1.getHeader().setOperatorId(dtoRequest.getOperatorId());
        auth3DSStep1.getHeader().setShopId(shopId);

        auth3DSStep1.setThreeDSMtdComplInd(dtoRequest.getThreeDSMtdComplInd());
        auth3DSStep1.setThreeDSTransID(dtoRequest.getThreeDSTransId());

        Data data = new Data();
        data.setAuth3DS2AuthorizationStep1Request(auth3DSStep1);
        request.setData(data);
        request.getRequest().setMac(encoder.getMac(MapBuilder.getThreeDS2Authorize1Map(request), key));
        return request;
    }

    public BPWXmlRequest buildThreeDS2Authorize2(ThreeDSAuthorization2Request requestDto, String shopId) throws VPosClientException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DS2STEP2, reqDate);
        Auth3DS2AuthorizationStep2Request auth3DSStep2 = new Auth3DS2AuthorizationStep2Request(reqDate);

        //Header
        auth3DSStep2.getHeader().setOperatorId(requestDto.getOperatorId());
        auth3DSStep2.getHeader().setShopId(shopId);

        auth3DSStep2.setThreeDSTransID(requestDto.getThreeDSTransId());

        Data data = new Data();
        data.setAuth3DS2AuthorizationStep2Request(auth3DSStep2);
        request.setData(data);
        request.getRequest().setMac(encoder.getMac(MapBuilder.getThreeDS2Authorize2Map(request), key));
        return request;
    }

    private BPWXmlRequest getBPWXmlRequest(String operation, Date date) {
        BPWXmlRequest request = new BPWXmlRequest();
        request.setRelease(VPosConstants.RELEASE);
        request.setRequest(new Request(operation, date));
        return request;
    }


}
