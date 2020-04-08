package eu.sia.vpos.client.utils.builders;


import eu.sia.vpos.client.request.PaymentInfo;
import eu.sia.vpos.client.request.xml.*;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.constants.VPosConstants;
import eu.sia.vpos.client.utils.encryption.AESEncoder;
import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.utils.mac.Encoder;

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

        if (values.get(VPosConstants.AUTHNUMBER) == null)
            map.put(VPosConstants.AUTHNUMBER, "NULL");
        else
            map.put(VPosConstants.AUTHNUMBER, values.get(VPosConstants.AUTHNUMBER));

        map.put(Operations.PARAMETERS.AMOUNT.NAME, values.get(Operations.PARAMETERS.AMOUNT.NAME));
        map.put(Operations.PARAMETERS.CURRENCY.NAME, values.get(Operations.PARAMETERS.CURRENCY.NAME));
        //map.put(Operations.PARAMETERS.EXPONENT.NAME, values.get(Operations.PARAMETERS.EXPONENT.NAME));
        map.put(Operations.PARAMETERS.TRANSACTIONID.NAME, values.get(Operations.PARAMETERS.TRANSACTIONID.NAME));
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, values.get(Operations.PARAMETERS.ACCOUNTINGMODE.NAME));
        map.put(VPosConstants.AUTHORMODE, values.get(VPosConstants.AUTHORMODE));
        map.put(VPosConstants.RESULT, values.get(VPosConstants.RESULT));
        map.put(Operations.CONFIRMATION.TRANSACTIONTYPE, values.get(Operations.CONFIRMATION.TRANSACTIONTYPE));
        map.put(VPosConstants.ISSUERCOUNTRY, values.get(VPosConstants.ISSUERCOUNTRY));
        map.put(VPosConstants.AUTHCODE, values.get(VPosConstants.AUTHCODE));
        map.put(VPosConstants.PAYERID, values.get(VPosConstants.PAYERID));
        map.put(VPosConstants.PAYER, values.get(VPosConstants.PAYER));
        map.put(VPosConstants.PAYERSTATUS, values.get(VPosConstants.PAYERSTATUS));
        map.put(VPosConstants.HASHPAN, values.get(VPosConstants.HASHPAN));
        map.put(Operations.CONFIRMATION.PANALIASREV, values.get(Operations.CONFIRMATION.PANALIASREV));
        map.put(Operations.CONFIRMATION.PANALIAS, values.get(Operations.CONFIRMATION.PANALIAS));
        map.put(Operations.CONFIRMATION.PANALIASEXPDATE, values.get(Operations.CONFIRMATION.PANALIASEXPDATE));
        map.put(Operations.CONFIRMATION.PANALIASTAIL, values.get(Operations.CONFIRMATION.PANALIASTAIL));

        map.put(Operations.PARAMETERS.TRECURR.NAME, values.get(Operations.PARAMETERS.TRECURR.NAME));
        map.put(Operations.PARAMETERS.CRECURR.NAME, values.get(Operations.PARAMETERS.CRECURR.NAME));

        map.put(VPosConstants.MASKEDPAN, values.get(VPosConstants.MASKEDPAN));
        map.put(Operations.AUTHORIZATION.PANTAIL, values.get(Operations.AUTHORIZATION.PANTAIL));
        map.put(Operations.AUTHORIZATION.PANEXPIRYDATE, values.get(Operations.AUTHORIZATION.PANEXPIRYDATE));

        map.put(VPosConstants.ACCOUNTHOLDER, values.get(VPosConstants.ACCOUNTHOLDER));
        map.put(Operations.PARAMETERS.IBAN.NAME, values.get(Operations.PARAMETERS.IBAN.NAME));
        map.put(VPosConstants.ALIASSTR, values.get(VPosConstants.ALIASSTR));
        map.put(VPosConstants.EMAILCH, values.get(VPosConstants.EMAILCH));
        map.put(VPosConstants.CFISC, values.get(VPosConstants.CFISC));
        map.put(Operations.AUTHORIZATION.ACQUIRERBIN, values.get(Operations.AUTHORIZATION.ACQUIRERBIN));
        map.put(Operations.AUTHORIZATION.MERCHANTID, values.get(Operations.AUTHORIZATION.MERCHANTID));
        map.put(Operations.AUTHORIZATION.CARDTYPE, values.get(Operations.AUTHORIZATION.CARDTYPE));
        map.put(Operations.AUTHORIZATION.AMAZONAUTHID, values.get(Operations.AUTHORIZATION.AMAZONAUTHID));
        map.put(Operations.AUTHORIZATION.AMAZONCAPTUREID, values.get(Operations.AUTHORIZATION.AMAZONCAPTUREID));
        map.put(VPosConstants.CHINFO, values.get(VPosConstants.CHINFO));
        return map;
    }

    public static Map<String, String> getRefundMap(BPWXmlRequest request) {
        Map<String, String> map = getGeneralMap(request);
        RefundRequest refundRequest = request.getData().getRefundRequest();
        map.put(Operations.PARAMETERS.OPDESCR.NAME, refundRequest.getOpDescr());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, refundRequest.getOptions());
        return map;
    }

    public static Map<String, String> getBookingMap(BPWXmlRequest request) {
        Map<String, String> map = getGeneralMap(request);
        GeneralRequest bookingRequest = request.getData().getAccounting();
        map.put(Operations.PARAMETERS.OPDESCR.NAME, bookingRequest.getOpDescr());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, bookingRequest.getOptions());
        return map;
    }

    public static Map<String, String> getOrderStatusMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        StatusRequest statusRequest = request.getData().getOrderStatusRequest();
        map.put(VPosConstants.SHOPID, statusRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, statusRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, statusRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.ORDERID.NAME, statusRequest.getOrderId());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, statusRequest.getOptions());
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, statusRequest.getProductRef());
        return map;
    }

    public static Map<String, String> getRedirectMap(PaymentInfo info, Encoder encoder, String macKey, String apiKey, String shopId) throws VPosClientException {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Operations.PARAMETERS.URLMS.NAME, info.getUrlMs());
        map.put(Operations.PARAMETERS.URLDONE.NAME, info.getUrlDone());
        map.put(Operations.PARAMETERS.ORDERID.NAME, info.getOrderId());
        map.put(VPosConstants.SHOPID, shopId);
        map.put(Operations.PARAMETERS.AMOUNT.NAME, info.getAmount());
        map.put(Operations.PARAMETERS.CURRENCY.NAME, info.getCurrency());
        map.put(Operations.PARAMETERS.EXPONENT.NAME, info.getExponent());
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, info.getAccountingMode());
        map.put(VPosConstants.AUTHORMODE, info.getAuthorMode());
        String options = info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.OPTIONS.NAME));
        map.put(Operations.PARAMETERS.OPTIONS.NAME, options);
        if (options != null && options.contains(Operations.PARAMETERS.OPTIONS.B)) {
            map.put(Operations.PARAMETERS.NAME.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NAME.NAME)));
            map.put(Operations.PARAMETERS.SURNAME.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.SURNAME.NAME)));
        }
        map.put(Operations.PARAMETERS.TAXID.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TAXID.NAME)));
        map.put(Operations.PARAMETERS.LOCKCARD.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.LOCKCARD.NAME)));
        if (options != null && options.contains(Operations.PARAMETERS.OPTIONS.F)) {
            map.put(Operations.PARAMETERS.COMMIS.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.COMMIS.NAME)));
        }
        if (options != null && (options.contains(Operations.PARAMETERS.OPTIONS.O) || options.contains(Operations.PARAMETERS.OPTIONS.V))) {
            map.put(Operations.PARAMETERS.ORDDESCR.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.ORDDESCR.NAME)));
        }
        map.put(Operations.PARAMETERS.VSID.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.VSID.NAME)));
        map.put(Operations.PARAMETERS.OPDESCR.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.OPDESCR.NAME)));
        if (options != null && options.contains(Operations.PARAMETERS.OPTIONS.D)) {
            map.put(Operations.PARAMETERS.REMAININGDURATION.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.REMAININGDURATION.NAME)));
        }
        map.put(Operations.PARAMETERS.USERID.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.USERID.NAME)));
        map.put(Operations.PARAMETERS.BP_POSTEPAY.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.BP_POSTEPAY.NAME)));
        map.put(Operations.PARAMETERS.BP_CARDS.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.BP_CARDS.NAME)));
        String network = info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME));
        if (network != null && network.equals(Operations.PARAMETERS.NETWORK.JIFFY)) {
            map.put(Operations.PARAMETERS.PHONENUMBER.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.PHONENUMBER.NAME)));
            map.put(Operations.PARAMETERS.CAUSATION.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.CAUSATION.NAME)));
            map.put(Operations.PARAMETERS.USER.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.USER.NAME)));
        }
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.PRODUCTREF.NAME)));
        map.put(Operations.PARAMETERS.ANTIFRAUD.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.ANTIFRAUD.NAME)));

        //setting data 3DS JSON
        if (info.getData3DSJson() != null)
            map.put(Operations.PARAMETERS.DATA3DS.NAME, AESEncoder.encode3DSData(apiKey, info.getData3DSJson().toString()));

        map.put(Operations.PARAMETERS.TRECURR.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TRECURR.NAME)));
        map.put(Operations.PARAMETERS.CRECURR.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.CRECURR.NAME)));
        map.put(Operations.PARAMETERS.TOKEN.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.TOKEN.NAME)));

        map.put(Operations.PARAMETERS.EXPDATE.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EXPDATE.NAME)));
        map.put(Operations.PARAMETERS.NETWORK.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NETWORK.NAME)));
        map.put(Operations.PARAMETERS.IBAN.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.IBAN.NAME)));
        map.put(Operations.PARAMETERS.MAC.NAME, encoder.getMac(map, macKey));

        map.put(Operations.PARAMETERS.URLBACK.NAME, info.getUrlBack());

        //NOT COMPULSORY
        map.put(Operations.PARAMETERS.LANG.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.LANG.NAME)));
        map.put(Operations.PARAMETERS.EMAIL.SHOPNAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EMAIL.SHOPNAME)));
        map.put(Operations.PARAMETERS.EMAIL.NAME, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.EMAIL.NAME)));
        map.put(Operations.PARAMETERS.NAME.NAMECH, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.NAME.NAMECH)));
        map.put(Operations.PARAMETERS.SURNAME.NAMECH, info.getNotCompulsoryFields().get(PaymentInfo.FieldName.valueOf(Operations.PARAMETERS.SURNAME.NAMECH)));
        return map;
    }

    private static Map<String, String> getGeneralMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);

        //Get refund request
        GeneralRequest generalRequest = null;
        if (request.getRequest().getOperation() != null && request.getRequest().getOperation().equals(Operations.PARAMETERS.REFUND))
            generalRequest = request.getData().getRefundRequest();
        else
            generalRequest = request.getData().getAccounting();

        if (generalRequest != null && generalRequest.getHeader() != null) {
            map.put(VPosConstants.SHOPID, generalRequest.getHeader().getShopId());
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

    public static Map<String, String> getOnlineAuthorizationMap(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);
        OnlineAuthorizationRequest authorizationRequest = request.getData().getOnlineAuthorizationRequest();
        map.put(VPosConstants.SHOPID, authorizationRequest.getHeader().getShopId());
        map.put(Operations.PARAMETERS.ORDERID.NAME, authorizationRequest.getOrderId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, authorizationRequest.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, authorizationRequest.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.PAN.NAME, authorizationRequest.getPan());
        map.put(Operations.PARAMETERS.CVV2.NAME, authorizationRequest.getCvv2());
        map.put(Operations.PARAMETERS.EXPDATE.NAME, authorizationRequest.getExpDate());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, authorizationRequest.getAmount());
        map.put(Operations.PARAMETERS.CURRENCY.NAME, authorizationRequest.getCurrency());
        map.put(Operations.PARAMETERS.EXPONENT.NAME, authorizationRequest.getExponent());
        map.put(Operations.PARAMETERS.ACCOUNTINGMODE.NAME, authorizationRequest.getAccountingMode());
        map.put(Operations.PARAMETERS.NETWORK.NAME, authorizationRequest.getNetwork());
        map.put(Operations.PARAMETERS.EMAIL.NAMECH, authorizationRequest.getEmailCH());
        map.put(Operations.PARAMETERS.USERID.NAME, authorizationRequest.getUserId());
        map.put(Operations.PARAMETERS.ACQUIRER.NAME, authorizationRequest.getAcquirer());
        map.put(Operations.PARAMETERS.IPADDRESS.NAME, authorizationRequest.getIpAddress());
        map.put(Operations.PARAMETERS.OPDESCR.NAME, authorizationRequest.getOpDescr());
        map.put(Operations.PARAMETERS.USRAUTHFLAG.NAME, authorizationRequest.getUsrAuthFlag());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, authorizationRequest.getOptions());
        map.put(Operations.PARAMETERS.ANTIFRAUD.NAME, authorizationRequest.getAntifraud());
        map.put(Operations.PARAMETERS.PRODUCTREF.NAME, authorizationRequest.getProductRef());
        map.put(Operations.PARAMETERS.NAME.NAME, authorizationRequest.getName());
        map.put(Operations.PARAMETERS.SURNAME.NAME, authorizationRequest.getSurname());
        map.put(Operations.PARAMETERS.TAXID.NAME, authorizationRequest.getTaxId());
        return map;

    }

    public static Map<String, String> getThreeDS2Authorize0Map(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);
        Auth3DS2AuthorizationStep0Request authorization3DSRequest = request.getData().getAuth3DS2AuthorizationStep0Request();

        map.put(VPosConstants.SHOPID, authorization3DSRequest.getHeader().getShopId());
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
        map.put(Operations.PARAMETERS.THREEDSDATA.NAME, authorization3DSRequest.getThreeDSData());
        map.put(Operations.PARAMETERS.NAME.NAMECH, authorization3DSRequest.getNameCH());
        map.put(Operations.PARAMETERS.NOTIFURL.NAME, authorization3DSRequest.getNotifyUrl());
        map.put(Operations.PARAMETERS.THREEDSMTDNOTIFURL.NAME, authorization3DSRequest.getThreeDSMtdNotifyUrl());
        map.put(Operations.PARAMETERS.CHALLENGEWINSIZE.NAME, authorization3DSRequest.getChallengeWinSize());

        return map;
    }

    public static Map<String, String> getThreeDS2Authorize1Map(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);
        Auth3DS2AuthorizationStep1Request step1Request = request.getData().getAuth3DS2AuthorizationStep1Request();
        map.put(VPosConstants.SHOPID, step1Request.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, step1Request.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM.NAME, step1Request.getHeader().getReqRefNum());

        map.put(Operations.PARAMETERS.THREEDSTRANSID.NAME, step1Request.getThreeDSTransID());
        map.put(Operations.PARAMETERS.THREEDSMTDCOMPLIND.NAME, step1Request.getThreeDSMtdComplInd());
        return map;
    }

    public static Map<String, String> getThreeDS2Authorize2Map(BPWXmlRequest request) {
        Map<String, String> map = getStdMap(request);
        Auth3DS2AuthorizationStep2Request step2Request = request.getData().getAuth3DS2AuthorizationStep2Request();
        map.put(VPosConstants.SHOPID, step2Request.getHeader().getShopId());
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
