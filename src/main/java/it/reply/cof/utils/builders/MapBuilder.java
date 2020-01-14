package it.reply.cof.utils.builders;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.request.ConfirmRequest;
import it.reply.cof.apos.request.GeneralRequest;
import it.reply.cof.apos.request.RefundRequest;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Operations;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder {

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


    private static Map<String, String> getGeneralMap(BPWXmlRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Operations.PARAMETERS.OPERATION, request.getRequest().getOperation());
        map.put(Operations.PARAMETERS.TIMESTAMP, request.getRequest().getTimestamp());

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
}
