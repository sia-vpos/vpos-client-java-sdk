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

        request.getRequest().setMac(encoder.getMac(MapBuilder.getConfirmMap(request), key));
        return request;
    }

}
