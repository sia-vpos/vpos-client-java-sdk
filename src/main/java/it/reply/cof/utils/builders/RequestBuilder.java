package it.reply.cof.utils.builders;

import it.reply.cof.apos.request.*;
import it.reply.cof.dto.ConfirmRequestDto;
import it.reply.cof.dto.request.RefundRequestDto;
import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.constants.AposConstants;
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

    public RequestBuilder(String key) throws COFException {
        this(key, MacAlgorithms.HMAC_SHA_256);
    }

    public RequestBuilder(String key, MacAlgorithms algorithm) throws COFException {
        this.algorithm = algorithm;
        this.key = key;
        encoder = new Encoder(algorithm);
    }

    public BPWXmlRequest buildRefundRequest(RefundRequestDto dtoRequest) throws COFException {
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
        Encoder encoder = new Encoder();
        request.getRequest().setMac(encoder.getMac(getRefundMap(request), "KEY"));
        return request;
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

        request.getRequest().setMac(encoder.getMac(getConfirmMap(reque), key);
        return request;
    }

}
