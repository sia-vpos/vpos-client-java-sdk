package eu.sia.vpos.client.utils.builders;


import eu.sia.vpos.client.request.PaymentInfo;
import eu.sia.vpos.client.request.xml.*;
import eu.sia.vpos.client.utils.constants.AposConstants;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.Encoder;
import eu.sia.vpos.client.utils.encryption.AESEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Useful methods generating maps to be fed during MAC generation operations
 */
public class MapBuilder {

    private MapBuilder() {
    }

    public static Map<String, String> getOutcomeMap(Map<String, String> values) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Operations.PARAMETERS.ORDERID.NAME, values.get(Operations.PARAMETERS.ORDERID.NAME));
        map.put(Operations.PARAMETERS.SHOPID.NAME, values.get(Operations.PARAMETERS.SHOPID.NAME));

        if (values.get(AposConstants.AUTHNUMBER) == null)
            map.put(AposConstants.AUTHNUMBER, "NULL");
        else
            map.put(AposConstants.AUTHNUMBER, values.get(AposConstants.AUTHNUMBER));

        map.put(Operations.PARAMETERS.AMOUNT.NAME, values.get(Operations.PARAMETERS.AMOUNT.NAME));
        map.put(Operations.PARAMETERS.CURRENCY.NAME, values.get(Operations.PARAMETERS.CURRENCY.NAME));
        map.put(Operations.PARAMETERS.EXPONENT.NAME, values.get(Operations.PARAMETERS.EXPONENT.NAME));
        map.put(Operations.PARAMETERS.TRANSACTIONID.NAME, values.get(Operations.PARAMETERS.TRANSACTIONID.NAME));
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, values.get(Operations.PARAMETERS.ACCOUNTINGMODE.NAME));
        map.put(AposConstants.AUTHORMODE, values.get(AposConstants.AUTHORMODE));
        map.put(AposConstants.RESULT, values.get(AposConstants.RESULT));
        map.put(Operations.CONFIRMATION.TRANSACTIONTYPE, values.get(Operations.CONFIRMATION.TRANSACTIONTYPE));
        map.put(AposConstants.ISSUERCOUNTRY, values.get(AposConstants.ISSUERCOUNTRY));
        map.put(AposConstants.AUTHCODE, values.get(AposConstants.AUTHCODE));
        map.put(AposConstants.PAYERID, values.get(AposConstants.PAYERID));
        map.put(AposConstants.PAYER, values.get(AposConstants.PAYER));
        map.put(AposConstants.PAYERSTATUS, values.get(AposConstants.PAYERSTATUS));
        map.put(AposConstants.HASHPAN, values.get(AposConstants.HASHPAN));
        map.put(Operations.CONFIRMATION.PANALIASREV, values.get(Operations.CONFIRMATION.PANALIASREV));
        map.put(Operations.CONFIRMATION.PANALIAS, values.get(Operations.CONFIRMATION.PANALIAS));
        map.put(Operations.CONFIRMATION.PANALIASEXPDATE, values.get(Operations.CONFIRMATION.PANALIASEXPDATE));
        map.put(Operations.CONFIRMATION.PANALIASTAIL, values.get(Operations.CONFIRMATION.PANALIASTAIL));

        map.put(AposConstants.MASKEDPAN, values.get(AposConstants.MASKEDPAN));
        map.put(Operations.AUTHORIZATION.PANTAIL, values.get(Operations.AUTHORIZATION.PANTAIL));
        map.put(Operations.AUTHORIZATION.PANEXPIRYDATE, values.get(Operations.AUTHORIZATION.PANEXPIRYDATE));

        map.put(AposConstants.ACCOUNTHOLDER, values.get(AposConstants.ACCOUNTHOLDER));
        map.put(Operations.PARAMETERS.IBAN.NAME, values.get(Operations.PARAMETERS.IBAN.NAME));
        map.put(AposConstants.ALIASSTR, values.get(AposConstants.ALIASSTR));
        map.put(Operations.AUTHORIZATION.ACQUIRERBIN, values.get(Operations.AUTHORIZATION.ACQUIRERBIN));
        map.put(Operations.AUTHORIZATION.MERCHANTID, values.get(Operations.AUTHORIZATION.MERCHANTID));
        map.put(Operations.AUTHORIZATION.CARDTYPE, values.get(Operations.AUTHORIZATION.CARDTYPE));
        return map;
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

    public static Map<String, String> getBookingMap(BPWXmlRequest request) {
        Map<String, String> map = getGeneralMap(request);
        GeneralRequest bookingRequest = request.getData().getAccounting();
        map.put(Operations.PARAMETERS.OPDESCR.NAME, bookingRequest.getOpDescr());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, bookingRequest.getOptions());
        return map;
    }

    public static Map<String, String> getVerifyMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        StatusRequest verifyRequest = request.getData().getVerifyRequest();
        map.put(Operations.PARAMETERS.OPERATION, request.getRequest().getOperation());
        map.put(Operations.PARAMETERS.TIMESTAMP, request.getRequest().getTimestamp());
        map.put(AposConstants.SHOPID, verifyRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, verifyRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, verifyRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.ORIGINALREQREFNUM.NAME, verifyRequest.getOriginalReqRefNum());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, verifyRequest.getOptions());

        return map;
    }

    public static Map<String, String> getOrderStatusMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        StatusRequest statusRequest = request.getData().getOrderStatusRequest();
        map.put(AposConstants.SHOPID, statusRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, statusRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, statusRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.ORDERID.NAME, statusRequest.getOrderId());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, statusRequest.getOptions());
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, statusRequest.getProductRef());
        return map;
    }

    public static Map<String, String> get3DSAuthMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        Authorization3DSRequest authorization3DSRequest = request.getData().getAuthorizationRequest();

        map.put(AposConstants.SHOPID, authorization3DSRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.ORDERID.NAME, authorization3DSRequest.getOrderId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, authorization3DSRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, authorization3DSRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.PAN.NAME, authorization3DSRequest.getPan());
        map.put(Operations.PARAMETERS.CVV2.NAME, authorization3DSRequest.getCvv2());
        map.put(Operations.PARAMETERS.EXPDATE.NAME, authorization3DSRequest.getExpDate());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, authorization3DSRequest.getAmount());
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
        map.put(Operations.PARAMETERS.INPERSON.NAME, authorization3DSRequest.getInPerson());
        map.put(Operations.PARAMETERS.MERCHANTURL.NAME, authorization3DSRequest.getMerchantURL());

        if (authorization3DSRequest.getData3DS() != null) {
            map.put(Operations.PARAMETERS.SERVICE.NAME, authorization3DSRequest.getData3DS().getService());
            map.put(Operations.PARAMETERS.XID.NAME, authorization3DSRequest.getData3DS().getXid());
            map.put(Operations.PARAMETERS.CAVV.NAME, authorization3DSRequest.getData3DS().getCavv());
            map.put(Operations.PARAMETERS.ECI.NAME, authorization3DSRequest.getData3DS().getEci());
            map.put(Operations.PARAMETERS.PP_AUTHENTICATEMETHOD.NAME, authorization3DSRequest.getMasterpassData().getPpAuthenticationMethod());
            map.put(Operations.PARAMETERS.PP_CARDENROLLMETHOD.NAME, authorization3DSRequest.getMasterpassData().getPpCardEnrollMethod());
            map.put(Operations.PARAMETERS.PARESSTATUS.NAME, authorization3DSRequest.getData3DS().getParesStatus());
            map.put(Operations.PARAMETERS.SCENROLLSTATUS.NAME, authorization3DSRequest.getData3DS().getScEnrollStatus());
            map.put(Operations.PARAMETERS.SIGNATUREVERIFICATION.NAME, authorization3DSRequest.getData3DS().getSignatureVerifytion());
        }

        return map;
    }

    public static Map<String, String> get3DSStep2AuthMap(BPWXmlRequest request) throws VPosClientException {

        Map<String, String> map = getStdMap(request);

        Auth3DSStep2Request auth3DSStep2Request = request.getData().getAuth3DSStep2Request();
        map.put(Operations.PARAMETERS.SHOPID.NAME, auth3DSStep2Request.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, auth3DSStep2Request.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, auth3DSStep2Request.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.ORIGINALREQREFNUM.NAME, auth3DSStep2Request.getOriginalReqRefNum());

        try {
            String decodedPares = URLDecoder.decode(auth3DSStep2Request.getPaRes(), StandardCharsets.UTF_8.toString());
            map.put(Operations.PARAMETERS.PARES, decodedPares);
        } catch (UnsupportedEncodingException e) {
            throw new VPosClientException(e.getMessage(), e);
        }

        return map;
    }

    public static Map<String, String> getRedirectMap(PaymentInfo info, Encoder encoder, String macKey, String apiKey) throws VPosClientException {
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
        map.put(Operations.PARAMETERS.OPTIONS.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.OPTIONS.NAME)));
        map.put(Operations.PARAMETERS.NAME.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NAME.NAME)));
        map.put(Operations.PARAMETERS.SURNAME.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.SURNAME.NAME)));
        map.put(Operations.PARAMETERS.TAXID.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TAXID.NAME)));
        map.put(Operations.PARAMETERS.LOCKCARD.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.LOCKCARD.NAME)));
        map.put(Operations.PARAMETERS.COMMIS.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.COMMIS.NAME)));
        map.put(Operations.PARAMETERS.ORDDESCR.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.ORDDESCR.NAME)));
        map.put(Operations.PARAMETERS.VSID.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.VSID.NAME)));
        map.put(Operations.PARAMETERS.OPDESCR.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.OPDESCR.NAME)));
        map.put(Operations.PARAMETERS.REMAININGDURATION.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.REMAININGDURATION.NAME)));
        map.put(Operations.PARAMETERS.USERID.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.USERID.NAME)));
        map.put(Operations.PARAMETERS.BP_POSTEPAY.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.BP_POSTEPAY.NAME)));
        map.put(Operations.PARAMETERS.BP_CARDS.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.BP_CARDS.NAME)));
        map.put(Operations.PARAMETERS.PHONENUMBER.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.PHONENUMBER.NAME)));
        map.put(Operations.PARAMETERS.CAUSATION.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.CAUSATION.NAME)));
        map.put(Operations.PARAMETERS.USER.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.USER.NAME)));
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.PRODUCTREF.NAME)));
        map.put(Operations.PARAMETERS.ANTIFRAUD.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.ANTIFRAUD.NAME)));

        //setting data 3DS JSON
        if (info.getData3DSJson() != null)
            map.put(Operations.PARAMETERS.DATA3DS.NAME, AESEncoder.encode3DSData(apiKey, info.getData3DSJson().toString()));

        map.put(Operations.PARAMETERS.MAC.NAME, encoder.getMac(map, macKey));
        map.put(Operations.PARAMETERS.URLBACK.NAME, info.getUrlBack());

        //NOT COMPULSORY
        map.put(Operations.PARAMETERS.LANG.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.LANG.NAME)));
        map.put(Operations.PARAMETERS.EMAIL.SHOPNAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EMAIL.SHOPNAME)));
        return map;
    }

    private static Map<String, String> getGeneralMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        //Get refund request
        GeneralRequest generalRequest = null;
        if (request.getRequest().getOperation() != null && request.getRequest().getOperation().equals(Operations.PARAMETERS.REFUND))
            generalRequest = request.getData().getRefundRequest();
        else if (request.getRequest().getOperation().equals(Operations.PARAMETERS.DEFERREDREQUEST))
            generalRequest = request.getData().getConfirmRequest();
        else
            generalRequest = request.getData().getAccounting();

        if (generalRequest != null && generalRequest.getHeader() != null) {
            map.put(AposConstants.SHOPID, generalRequest.getHeader().getShopId());
            map.put(Operations.PARAMETERS.OPERATORID.NAME, generalRequest.getHeader().getOperatorId());
            map.put(Operations.PARAMETERS.REQREFNUM.NAME, generalRequest.getHeader().getReqRefNum());
            map.put(Operations.PARAMETERS.TRANSACTIONID.NAME, generalRequest.getTransactionId());
            map.put(Operations.PARAMETERS.ORDERID.NAME, generalRequest.getOrderId());
            map.put(Operations.PARAMETERS.AMOUNT.NAME, generalRequest.getAmount());
            map.put(Operations.PARAMETERS.CURRENCY.NAME, generalRequest.getCurrency());
            map.put(Operations.PARAMETERS.EXPONENT.NAME, generalRequest.getExponent());
        }
        return map;
    }

    public static Map<String,String> getThreeDS2Authorize0Map(BPWXmlRequest request){
        Map<String, String> map = getStdMap(request);
        Auth3DS2AuthorizationStep0Request authorization3DSRequest = request.getData().getAuth3DS2AuthorizationStep0Request();

        map.put(AposConstants.SHOPID, authorization3DSRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.ORDERID.NAME, authorization3DSRequest.getOrderId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, authorization3DSRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, authorization3DSRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.PAN.NAME, authorization3DSRequest.getPan());
        map.put(Operations.PARAMETERS.CVV2.NAME, authorization3DSRequest.getCvv2());
        map.put(Operations.PARAMETERS.EXPDATE.NAME, authorization3DSRequest.getExpDate());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, authorization3DSRequest.getAmount());
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
        map.put(Operations.PARAMETERS.THREEDSDATA.NAME, authorization3DSRequest.getThreeDSData().toString());
        map.put(Operations.PARAMETERS.NOTIFURL.NAME, authorization3DSRequest.getNotifyUrl());
        map.put(Operations.PARAMETERS.THREEDSMTDNOTIFURL.NAME, authorization3DSRequest.getThreeDSMtdNotifyUrl());
        map.put(Operations.PARAMETERS.CHALLENGEWINSIZE.NAME, authorization3DSRequest.getChallengeWinSize());

        return map;
    }

    public static Map<String,String> getThreeDS2Authorize1Map(BPWXmlRequest request){
        Map<String, String> map = getStdMap(request);
        Auth3DS2AuthorizationStep1Request step1Request = request.getData().getAuth3DS2AuthorizationStep1Request();
        map.put(AposConstants.SHOPID, step1Request.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, step1Request.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, step1Request.getHeader().getReqRefNum());

        map.put(Operations.PARAMETERS.THREEDSTRANSID.NAME, step1Request.getThreeDSTransID());
        map.put(Operations.PARAMETERS.THREEDSMTDCOMPLIND.NAME, step1Request.getThreeDSMtdComplInd());
        return map;
    }

    public static Map<String,String> getThreeDS2Authorize2Map(BPWXmlRequest request){
        Map<String, String> map = getStdMap(request);
        Auth3DS2AuthorizationStep2Request step2Request = request.getData().getAuth3DS2AuthorizationStep2Request();
        map.put(AposConstants.SHOPID, step2Request.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, step2Request.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, step2Request.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.THREEDSTRANSID.NAME, step2Request.getThreeDSTransID());
        return map;
    }

    private static Map<String, String> getStdMap(BPWXmlRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Operations.PARAMETERS.OPERATION, request.getRequest().getOperation());
        map.put(Operations.PARAMETERS.TIMESTAMP, request.getRequest().getTimestamp());
        return map;
    }
}
