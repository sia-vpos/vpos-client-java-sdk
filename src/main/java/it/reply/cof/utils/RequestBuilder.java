package it.reply.cof.utils;

import it.reply.cof.apos.request.*;
import it.reply.cof.dto.ConfirmRequestDto;
import it.reply.cof.dto.request.RefundRequestDto;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Constants;
import it.reply.cof.utils.constants.Operations;
import it.reply.cof.utils.exception.COFException;
import it.reply.cof.utils.mac.Encoder;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestBuilder {

    private String key;
    private MacAlgorithms algorithm;
    private Encoder encoder;

    public RequestBuilder(String key) {
        this(key, MacAlgorithms.HMAC_SHA_256);
    }

    public RequestBuilder(String key, MacAlgorithms algorithm) throws COFException {
        this.algorithm = algorithm;
        this.key = key;
        encoder = new Encoder(algorithm);
    }

    public BPWXmlRequest buildConfirmRequest(ConfirmRequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = new BPWXmlRequest();
        request.setRelease(AposConstants.RELEASE);
        request.setRequest(new Request(Operations.PARAMETERS.DEFERREDREQUEST, reqDate));

        ConfirmRequest confirm = new ConfirmRequest(reqDate);

        //HEADER
        confirm.getHeader().setShopId(dtoRequest.getShopId());
        confirm.getHeader().setOperatorId(dtoRequest.getOperatorId());

    }

    public static BPWXmlRequest buildRefundRequest(RefundRequestDto dtoRequest) throws COFException {
        Date reqDate = new Date();
        BPWXmlRequest request = new BPWXmlRequest();
        request.setRelease(AposConstants.RELEASE);
        request.setRequest(new Request(Operations.PARAMETERS.REFUND, reqDate));

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

        //calculate MAC
        Encoder encoder= new Encoder();
        request.getRequest().setMac(encoder.getMac(getRefundMap(request),"KEY"));
        return request;
    }

    public static Map<String, String> getRefundMap(BPWXmlRequest request) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
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

}
