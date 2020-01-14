package it.reply.cof.utils.builders;

import it.reply.cof.apos.request.*;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Operations;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder {

    private MapBuilder() {
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

    private static Map<String, String> getStdMap(BPWXmlRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Operations.PARAMETERS.OPERATION, request.getRequest().getOperation());
        map.put(Operations.PARAMETERS.TIMESTAMP, request.getRequest().getTimestamp());
        return map;
    }
}
