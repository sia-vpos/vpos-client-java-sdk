package it.reply.cof.utils.builders;

import it.reply.cof.apos.request.BPWXmlRequest;
import it.reply.cof.apos.request.RefundRequest;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Operations;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder {

    public static Map<String, String> getRefundMap(BPWXmlRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Operations.PARAMETERS.OPERATION, Operations.PARAMETERS.REFUND);
        map.put(Operations.PARAMETERS.TIMESTAMP, request.getRequest().getTimestamp());

        //Get refund request
        RefundRequest refund = request.getData().getRefundRequest();
        map.put(AposConstants.SHOPID, refund.getHeader().getShopId());
        map.put(Operations.PARAMETERS.OPERATORID.NAME, refund.getHeader().getOperatorId());
        map.put(Operations.PARAMETERS.REQREFNUM, refund.getHeader().getReqRefNum());
        map.put(Operations.PARAMETERS.TRANSACTIONID.NAME, refund.getTransactionId());
        map.put(Operations.PARAMETERS.ORDERID.NAME, refund.getOrderId());
        map.put(Operations.PARAMETERS.AMOUNT.NAME, refund.getAmount());
        map.put(Operations.PARAMETERS.CURRENCY.NAME, refund.getCurrency());
        map.put(Operations.PARAMETERS.EXPONENT.NAME, refund.getExponent());
        map.put(Operations.PARAMETERS.OPDESCR.NAME, refund.getOpDescr());
        map.put(Operations.PARAMETERS.OPTIONS.NAME, refund.getOptions());
        return map;
    }

    public static Map<String, String> getConfirmMap() {
        Map<String, String> map = new LinkedHashMap<>();

    }
}
