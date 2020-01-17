package it.reply.cof.utils.builders;

import it.reply.cof.apos.request.*;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Operations;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.Encoder;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder {

    private MapBuilder() {
    }

    public static Map<String, String> getOutcomeMap(Map<String, String> values){
        Map<String, String> map = new LinkedHashMap<>();


        map.put(Operations.PARAMETERS.ORDERID.NAME, values.get(Operations.PARAMETERS.ORDERID.NAME));
        map.put();
        map.put(AposConstants.SHOPID, info.getShopId());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, info.getAmount());
        map.put(Operations.PARAMETERS.CURRENCY.NAME, info.getCurrency());
        map.put(Operations.PARAMETERS.EXPONENT.NAME, info.getExponent());
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, info.getAccountingMode());
        map.put(AposConstants.AUTHORMODE, info.getAuthorMode());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.OPTIONS.NAME));
        map.put(Operations.PARAMETERS.NAME.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.NAME.NAME));
        map.put(Operations.PARAMETERS.SURNAME.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.SURNAME.NAME));
        map.put(Operations.PARAMETERS.TAXID.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.TAXID.NAME));
        map.put(Operations.PARAMETERS.LOCKCARD.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.LOCKCARD.NAME));
        map.put(Operations.PARAMETERS.COMMIS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.COMMIS.NAME));
        map.put(Operations.PARAMETERS.ORDDESCR.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.ORDDESCR.NAME));
        map.put(Operations.PARAMETERS.VSID.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.VSID.NAME));
        map.put(Operations.PARAMETERS.OPDESCR.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.OPDESCR.NAME));
        map.put(Operations.PARAMETERS.REMAININGDURATION.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.REMAININGDURATION.NAME));
        map.put(Operations.PARAMETERS.USERID.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.USERID.NAME));
        map.put(Operations.PARAMETERS.BP_POSTEPAY.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.BP_POSTEPAY.NAME));
        map.put(Operations.PARAMETERS.BP_CARDS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.BP_CARDS.NAME));
        map.put(Operations.PARAMETERS.PHONENUMBER.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.PHONENUMBER.NAME));
        map.put(Operations.PARAMETERS.CAUSATION.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.CAUSATION.NAME));
        map.put(Operations.PARAMETERS.USER.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.USER.NAME));
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.PRODUCTREF.NAME));
        map.put(Operations.PARAMETERS.ANTIFRAUD.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.ANTIFRAUD.NAME));
        map.put(Operations.PARAMETERS.DATA3DS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.DATA3DS.NAME));
        map.put(Operations.PARAMETERS.MAC.NAME, encoder.getMac(map, key));
        map.put(Operations.PARAMETERS.URLBACK.NAME, info.getUrlBack());
        //NOT COMPULSORY
    }

    public static Map<String, String> getRefundMap(BPWXmlRequest request) {
        Map<String, String> map = getGeneralMap(request);

        RefundRequest refundRequest = request.getData().getRefundRequest();
        map.put(Operations.PARAMETERS.OPDESCR.NAME, refundRequest.getOpDescr());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, refundRequest.getOptions());

        return map;
    }

    public static Map<String, String> getConfirmMap(BPWXmlRequest request) {
        Map<String, String> map = getGeneralMap(request);

        ConfirmRequest confirmRequest = request.getData().getConfirmRequest();
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, confirmRequest.getAccountingMode());
        map.put(Operations.PARAMETERS.CLOSEORDER.NAME, confirmRequest.getCloseOrder());

        return map;
    }

    public static Map<String, String> getVerifyMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        StatusRequest verifyRequest = request.getData().getVerifyRequest();
        map.put(AposConstants.SHOPID, verifyRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, verifyRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM, verifyRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.ORIGINALREQREFNUM, verifyRequest.getOriginalReqRefNum());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, verifyRequest.getOptions());

        return map;
    }

    public static Map<String, String> getOrderStatusMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        StatusRequest statusRequest = request.getData().getOrderStatusRequest();
        map.put(AposConstants.SHOPID, statusRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, statusRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM, statusRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.ORDERID.NAME, statusRequest.getOrderId());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, statusRequest.getOptions());
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, statusRequest.getProductRef());
        return map;
    }

    private static Map<String, String> getGeneralMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        //Get refund request
        GeneralRequest generalRequest = null;
        if (request.getData().getOperation().equals(Operations.PARAMETERS.REFUND))
            generalRequest = request.getData().getRefundRequest();
        else if (request.getData().getOperation().equals(Operations.PARAMETERS.DEFERREDREQUEST))
            generalRequest = request.getData().getConfirmRequest();

        map.put(AposConstants.SHOPID, generalRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, generalRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM, generalRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.TRANSACTIONID.NAME, generalRequest.getTransactionId());
        map.put(Operations.PARAMETERS.ORDERID.NAME, generalRequest.getOrderId());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, generalRequest.getAmount());
        map.put(Operations.PARAMETERS.CURRENCY.NAME, generalRequest.getCurrency());
        map.put(Operations.PARAMETERS.EXPONENT.NAME, generalRequest.getExponent());

        return map;
    }

    public static Map<String, String> get3DSAuthMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        Authorization3DSRequest authorization3DSRequest = request.getData().getAuthorizationRequest();

        map.put(AposConstants.SHOPID, authorization3DSRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.ORDERID.NAME, authorization3DSRequest.getOrderId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, authorization3DSRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM, authorization3DSRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.PAN.NAME, authorization3DSRequest.getPan());
        map.put(Operations.PARAMETERS.CVV2.NAME, authorization3DSRequest.getCvv2());
        map.put(Operations.PARAMETERS.EXPDATE.NAME, authorization3DSRequest.getExpDate());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, authorization3DSRequest.getAcquirer());
        map.put(Operations.PARAMETERS.CURRENCY.NAME, authorization3DSRequest.getCurrency());
        map.put(Operations.PARAMETERS.EXPONENT.NAME, authorization3DSRequest.getExponent());
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, authorization3DSRequest.getAccountingMode());
        map.put(Operations.PARAMETERS.NETWORK.NAME, authorization3DSRequest.getNetwork());
        map.put(Operations.PARAMETERS.EMAIL.NAMECH, authorization3DSRequest.getEmailCH());
        map.put(Operations.PARAMETERS.USERID.NAME, authorization3DSRequest.getUserId());
        map.put(Operations.PARAMETERS.ACQUIRER.NAME, authorization3DSRequest.getAcquirer());
        map.put(Operations.PARAMETERS.IPADDRESS.NAME, authorization3DSRequest.getIpAddress());
        map.put(Operations.PARAMETERS.OPDESCR.NAME, authorization3DSRequest.getOpDescr());
        map.put(Operations.PARAMETERS.USRAUTHFLAG.NAME, authorization3DSRequest.getUsrAuthFlag());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, authorization3DSRequest.getOptions());
        map.put(Operations.PARAMETERS.ANTIFRAUD.NAME, authorization3DSRequest.getAntifraud());
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, authorization3DSRequest.getProductRef());
        map.put(Operations.PARAMETERS.NAME.NAME, authorization3DSRequest.getName());
        map.put(Operations.PARAMETERS.SURNAME.NAME, authorization3DSRequest.getSurname());
        map.put(Operations.PARAMETERS.TAXID.NAME, authorization3DSRequest.getTaxId());
        map.put(Operations.PARAMETERS.INPERSON, authorization3DSRequest.getInPerson());
        map.put(Operations.PARAMETERS.MERCHANTURL, authorization3DSRequest.getMerchantURL());
        map.put(Operations.PARAMETERS.SERVICE, authorization3DSRequest.getData3DS().getService());
        map.put(Operations.PARAMETERS.XID.NAME, authorization3DSRequest.getData3DS().getXid());
        map.put(Operations.PARAMETERS.CAVV, authorization3DSRequest.getData3DS().getCavv());
        map.put(Operations.PARAMETERS.ECI, authorization3DSRequest.getData3DS().getEci());
        map.put(Operations.PARAMETERS.PP_AUTHENTICATEMETHOD, authorization3DSRequest.getMasterpassData().getPpAuthenticationMethod());
        map.put(Operations.PARAMETERS.PP_CARDENROLLMETHOD, authorization3DSRequest.getMasterpassData().getPpCardEnrollMethod());
        map.put(Operations.PARAMETERS.PARESSTATUS, authorization3DSRequest.getData3DS().getParesStatus());
        map.put(Operations.PARAMETERS.SCENROLLSTATUS, authorization3DSRequest.getData3DS().getScEnrollStatus());
        map.put(Operations.PARAMETERS.SIGNATUREVERIFICATION, authorization3DSRequest.getData3DS().getSignatureVerifytion());

        return map;
    }

    public static Map<String, String> getRedirectMap(PaymentInfo info, Encoder encoder, String key) throws COFException {
        Map<String, String> map = new LinkedHashMap<>();

        map.put(Operations.PARAMETERS.URLMS.NAME, info.getUrlMs());
        map.put(Operations.PARAMETERS.URLDONE.NAME, info.getUrlDone());
        map.put(Operations.PARAMETERS.ORDERID.NAME, info.getOrderId());
        map.put(AposConstants.SHOPID, info.getShopId());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, info.getAmount());
        map.put(Operations.PARAMETERS.CURRENCY.NAME, info.getCurrency());
        map.put(Operations.PARAMETERS.EXPONENT.NAME, info.getExponent());
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, info.getAccountingMode());
        map.put(AposConstants.AUTHORMODE, info.getAuthorMode());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.OPTIONS.NAME));
        map.put(Operations.PARAMETERS.NAME.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.NAME.NAME));
        map.put(Operations.PARAMETERS.SURNAME.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.SURNAME.NAME));
        map.put(Operations.PARAMETERS.TAXID.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.TAXID.NAME));
        map.put(Operations.PARAMETERS.LOCKCARD.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.LOCKCARD.NAME));
        map.put(Operations.PARAMETERS.COMMIS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.COMMIS.NAME));
        map.put(Operations.PARAMETERS.ORDDESCR.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.ORDDESCR.NAME));
        map.put(Operations.PARAMETERS.VSID.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.VSID.NAME));
        map.put(Operations.PARAMETERS.OPDESCR.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.OPDESCR.NAME));
        map.put(Operations.PARAMETERS.REMAININGDURATION.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.REMAININGDURATION.NAME));
        map.put(Operations.PARAMETERS.USERID.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.USERID.NAME));
        map.put(Operations.PARAMETERS.BP_POSTEPAY.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.BP_POSTEPAY.NAME));
        map.put(Operations.PARAMETERS.BP_CARDS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.BP_CARDS.NAME));
        map.put(Operations.PARAMETERS.PHONENUMBER.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.PHONENUMBER.NAME));
        map.put(Operations.PARAMETERS.CAUSATION.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.CAUSATION.NAME));
        map.put(Operations.PARAMETERS.USER.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.USER.NAME));
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.PRODUCTREF.NAME));
        map.put(Operations.PARAMETERS.ANTIFRAUD.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.ANTIFRAUD.NAME));
        map.put(Operations.PARAMETERS.DATA3DS.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.DATA3DS.NAME));
        map.put(Operations.PARAMETERS.MAC.NAME, encoder.getMac(map, key));
        map.put(Operations.PARAMETERS.URLBACK.NAME, info.getUrlBack());
        //NOT COMPULSORY
        map.put(Operations.PARAMETERS.LANG.NAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.LANG.NAME));
        map.put(Operations.PARAMETERS.EMAIL.SHOPNAME, info.getNotCompulsoryFields().get(Operations.PARAMETERS.EMAIL.SHOPNAME));

        return map;
    }

    private static Map<String, String> getStdMap(BPWXmlRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Operations.PARAMETERS.OPERATION, request.getRequest().getOperation());
        map.put(Operations.PARAMETERS.TIMESTAMP, request.getRequest().getTimestamp());
        return map;
    }
}
