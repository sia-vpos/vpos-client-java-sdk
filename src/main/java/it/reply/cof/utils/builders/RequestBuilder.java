package it.reply.cof.utils.builders;

import it.reply.cof.apos.request.*;
import it.reply.cof.apos.response.Operation;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Operations;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.Encoder;
import it.reply.cof.utils.mac.MacAlgorithms;

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
     * @throws COFException in case of failure (see exception message for more info)
     */
    public RequestBuilder(String key) throws COFException {
        this(key, MacAlgorithms.HMAC_SHA_256);
    }

    /**
     * Creates an instance of the builder using the specified algorithm for MAC calculation
     *
     * @param key       used to encode the MAC
     * @param algorithm used to calculate the MAC
     * @throws COFException in case of failure (see exception message for more info)
     */
    public RequestBuilder(String key, MacAlgorithms algorithm) throws COFException {
        this.key = key;
        encoder = new Encoder(algorithm);
    }

    /**
     * Build the XML request for REFUND operations
     *
     * @param dtoRequest object containing the necessary infos to perform a REFUND
     * @return the XML request
     * @throws COFException
     */
    public BPWXmlRequest buildRefundRequest(RefundRequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.REFUND, reqDate);

        RefundRequest refund = new RefundRequest(reqDate);
        //HEADER
        refund.getHeader().setOperatorId(dtoRequest.getOperatorId());
        refund.getHeader().setShopId(dtoRequest.getShopId());

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
     * Build the XML request for CONFIRM operations
     *
     * @param dtoRequest object containing the necessary infos to perform a CONFIRM payment
     * @return the XML request
     * @throws COFException
     */
    public BPWXmlRequest buildConfirmRequest(ConfirmRequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.DEFERREDREQUEST, reqDate);

        ConfirmRequest confirm = new ConfirmRequest(reqDate);
        //HEADER
        confirm.getHeader().setShopId(dtoRequest.getShopId());
        confirm.getHeader().setOperatorId(dtoRequest.getOperatorId());

        //CONFIRM REQUEST
        confirm.setTransactionId(dtoRequest.getTransactionId());
        confirm.setOrderId(dtoRequest.getOrderId());
        confirm.setAmount(dtoRequest.getAmount());
        confirm.setCurrency(dtoRequest.getCurrency());
        confirm.setExponent(dtoRequest.getExponent());
        confirm.setAccountingMode(dtoRequest.getAccountingMode());
        confirm.setCloseOrder(dtoRequest.getCloseOrder());

        Data data = new Data();
        data.setConfirmRequest(confirm);
        request.setData(data);

        request.getRequest().setMac(encoder.getMac(MapBuilder.getConfirmMap(request), key));
        return request;
    }

    /**
     * Build the XML request for accounting operations
     *
     * @param dtoRequest object containing the necessary infos to perform the accounting of an existing transaction
     * @return the XML request
     * @throws COFException
     */
    public BPWXmlRequest buildBookingRequest(BookingRequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.ACCOUNTING, reqDate);

        GeneralRequest bookingRequest = new GeneralRequest(reqDate);

        //HEADER
        bookingRequest.getHeader().setShopId(dtoRequest.getShopId());
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
     * @throws COFException
     */
    public BPWXmlRequest buildOrderStatusRequest(OrderStatusRequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.ORDERSTATUS, reqDate);

        StatusRequest status = new StatusRequest(reqDate);
        //HEADER
        status.getHeader().setShopId(dtoRequest.getShopId());
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

    /**
     * Build the XML request for Verify operation
     *
     * @param dtoRequest object containing the necessary infos to perform a Verify request
     * @return the xml request
     * @throws COFException
     */
    public BPWXmlRequest buildVerifyRequest(VerifyRequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.VERIFY, reqDate);

        StatusRequest statusRequest = new StatusRequest(reqDate);
        //HEADER
        statusRequest.getHeader().setShopId(dtoRequest.getShopId());
        statusRequest.getHeader().setOperatorId(dtoRequest.getOperatorId());

        //VERIFY REQUEST
        statusRequest.setOriginalReqRefNum(dtoRequest.getOriginalReqRefNum());
        statusRequest.setOptions(dtoRequest.getOptions());

        Data data = new Data();
        data.setVerifyRequest(statusRequest);
        request.setData(data);
        request.getRequest().setMac(encoder.getMac(MapBuilder.getVerifyMap(request), key));
        return request;
    }

    /**
     * Build the XML request for step 1 of a 3DS authorization
     *
     * @param dtoRequest object containing the necessary infos to perform the step 1 of a 3DS authorization
     * @return the XML request
     * @throws COFException
     */
    public BPWXmlRequest build3DSAuthRequest(Auth3DSDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DSSTEP1, reqDate);

        Authorization3DSRequest authorization3DSRequest = new Authorization3DSRequest(reqDate);
        //HEADER
        authorization3DSRequest.getHeader().setShopId(dtoRequest.getShopId());
        authorization3DSRequest.getHeader().setOperatorId(dtoRequest.getOperatorId());

        if (dtoRequest.isMasterpass()) {
            //3DS DATA
            Data3DS data3DS = new Data3DS();
            data3DS.setService(dtoRequest.getService());
            data3DS.setEci(dtoRequest.getEci());
            data3DS.setXid(dtoRequest.getxId());
            data3DS.setCavv(dtoRequest.getCavv());
            data3DS.setParesStatus(dtoRequest.getParesStatus());
            data3DS.setScEnrollStatus(dtoRequest.getScenRollStatus());
            data3DS.setSignatureVerifytion(dtoRequest.getSignatureVerification());
            authorization3DSRequest.setData3DS(data3DS);

            // MASTERPASS DATA
            MasterpassData masterpassData = new MasterpassData();
            masterpassData.setPpAuthenticationMethod(dtoRequest.getPpAuthenticateMethod());
            masterpassData.setPpCardEnrollMethod(dtoRequest.getCardEnrollMethod());
            authorization3DSRequest.setMasterpassData(masterpassData);
        }

        //AUTH 3DS REQUEST
        authorization3DSRequest.setOrderId(dtoRequest.getOrderId());
        authorization3DSRequest.setPan(dtoRequest.getPan());
        authorization3DSRequest.setCvv2(dtoRequest.getCvv2());
        authorization3DSRequest.setExpDate(dtoRequest.getExpDate());
        authorization3DSRequest.setAmount(dtoRequest.getAmount());
        authorization3DSRequest.setCurrency(dtoRequest.getCurrency());
        authorization3DSRequest.setExponent(dtoRequest.getExponent());
        authorization3DSRequest.setAccountingMode(dtoRequest.getAccountingMode());
        authorization3DSRequest.setNetwork(dtoRequest.getNetwork());
        authorization3DSRequest.setEmailCH(dtoRequest.getEmailCh());
        authorization3DSRequest.setUserId(dtoRequest.getUserId());
        authorization3DSRequest.setAcquirer(dtoRequest.getAcquirer());
        authorization3DSRequest.setIpAddress(dtoRequest.getIpAddress());
        authorization3DSRequest.setUsrAuthFlag(dtoRequest.getUsrAuthFlag());
        authorization3DSRequest.setOpDescr(dtoRequest.getOpDescr());
        authorization3DSRequest.setAntifraud(dtoRequest.getAntifraud());
        authorization3DSRequest.setInPerson(dtoRequest.getInPerson());
        authorization3DSRequest.setMerchantURL(dtoRequest.getMerchantUrl());
        authorization3DSRequest.setProductRef(dtoRequest.getProductRef());
        authorization3DSRequest.setName(dtoRequest.getName());
        authorization3DSRequest.setSurname(dtoRequest.getSurname());
        authorization3DSRequest.setTaxId(dtoRequest.getTaxId());
        authorization3DSRequest.setCreatePanAlias(dtoRequest.getCreatePanAlias());

        Data data = new Data();
        data.setAuthorizationRequest(authorization3DSRequest);
        request.setData(data);

        request.getRequest().setMac(encoder.getMac(MapBuilder.get3DSAuthMap(request), key));

        return request;
    }

    /**
     * Build the XML request for step 2 of a 3DS authorization
     *
     * @param dtoRequest object containing the necessary infos to perform the step 2 of a 3DS authorization
     * @return the XML request
     * @throws COFException
     */
    public BPWXmlRequest build3DSStep2AuthRequest(Auth3DSStep2RequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = getBPWXmlRequest(Operations.PARAMETERS.AUTHORIZATION3DSSTEP2, reqDate);

        Auth3DSStep2Request auth3DSStep2Request = new Auth3DSStep2Request(reqDate);

        //HEADER
        auth3DSStep2Request.getHeader().setOperatorId(dtoRequest.getOperatorId());
        auth3DSStep2Request.getHeader().setShopId(dtoRequest.getShopId());

        //AUTH3DS STEP2 REQUEST
        auth3DSStep2Request.setOriginalReqRefNum(dtoRequest.getOriginalRefReqNum());
        auth3DSStep2Request.setPaRes(dtoRequest.getPaRes());
        auth3DSStep2Request.setAcquirer(dtoRequest.getAcquirer());

        Data data = new Data();
        data.setAuth3DSStep2Request(auth3DSStep2Request);
        request.setData(data);

        request.getRequest().setMac(encoder.getMac(MapBuilder.get3DSStep2AuthMap(request), key));

        return request;
    }

    private BPWXmlRequest getBPWXmlRequest(String operation, Date date) {
        BPWXmlRequest request = new BPWXmlRequest();
        request.setRelease(AposConstants.RELEASE);
        request.setRequest(new Request(operation, date));
        return request;
    }


}
