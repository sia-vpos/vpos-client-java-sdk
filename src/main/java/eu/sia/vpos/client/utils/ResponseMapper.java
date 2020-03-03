package eu.sia.vpos.client.utils;


import eu.sia.vpos.client.response.*;
import eu.sia.vpos.client.response.xml.Authorization;
import eu.sia.vpos.client.response.xml.BPWXmlResponse;
import eu.sia.vpos.client.response.xml.Operation;
import eu.sia.vpos.client.response.xml.PanAliasData;

import java.util.List;

public class ResponseMapper {


    /**
     * Maps a Refund Response to its dto
     *
     * @param response contains all the relevant information
     * @return dto populated with values obtained from the response XML
     */
    public RefundResponse mapRefundResponseDto(BPWXmlResponse response) {
        RefundResponse dto = new RefundResponse();

        if (response != null) {
            if (response.getData() != null && response.getData().getOperation() != null) {
                Operation operation = response.getData().getOperation();
                if (operation != null) {
                    dto.setTransactionID(operation.getTransactionId());
                    dto.setTimestampReq(operation.getTimestampReq());
                    dto.setTimestampElab(operation.getTimestampElab());
                    dto.setSrcType(operation.getSrcType());
                    dto.setAmount(operation.getAmount());
                    dto.setResult(operation.getResult());
                    dto.setStatus(operation.getStatus());
                    dto.setOpDescr(operation.getOpDescr());
                    dto.setOperationMAC(operation.getMac());
                }
            }

            if (response.getData() != null && response.getData().getAuthorization() != null && !response.getData().getAuthorization().isEmpty()) {
                Authorization authorization = response.getData().getAuthorization().get(0);
                if (authorization != null) {
                    dto.setPaymentType(authorization.getPaymentType());
                    dto.setAuthorizationType(authorization.getAuthorizationType());
                    dto.setTransactionID(authorization.getTransactionId());
                    dto.setNetwork(authorization.getNetwork());
                    dto.setOrderID(authorization.getOrderId());
                    dto.setTransactionAmount(authorization.getTransactionAmount());
                    dto.setAuthorizatedAmount(authorization.getAuthorizedAmount());
                    dto.setCurrency(authorization.getCurrency());
                    dto.setExponent(authorization.getExponent());
                    dto.setAccountedAmount(authorization.getAccountedAmount());
                    dto.setRefundedAmount(authorization.getRefundedAmount());
                    dto.setTransactionResult(authorization.getTransactionResult());
                    dto.setTimestamp(authorization.getTimestamp());
                    dto.setAuthorizationNumber(authorization.getAuthorizationNumber());
                    dto.setAcquireBIN(authorization.getAcquirerBin());
                    dto.setMerchantID(authorization.getMerchantId());
                    dto.setTransactionStatus(authorization.getTransactionStatus());
                    dto.setResponseCodedIso(authorization.getResponseCodeIso());
                }
            }

        }
        return dto;
    }

    /**
     * Maps an Order Status Response to its dto
     *
     * @param response contains all the relevant information
     * @return dto populated with values obtained from the response XML
     */
    public OrderStatusResponse mapOrderStatusResponse(BPWXmlResponse response) {
        OrderStatusResponse dto = new OrderStatusResponse();

        if (response != null && response.getData() != null && response.getData().getAuthorization() != null) {
            List<Authorization> authorizationList = response.getData().getAuthorization();

            if (!authorizationList.isEmpty()) {
                PanAliasData commonElements = response.getData().getPanAliasData();
                OrderStatusResponseDto dtoElement = new OrderStatusResponseDto();

                for (Authorization element : authorizationList) {
                    dtoElement.setPaymentType(element.getPaymentType());
                    dtoElement.setAuthorizationType(element.getAuthorizationType());
                    dtoElement.setTransactionID(element.getTransactionId());
                    dtoElement.setNetwork(element.getNetwork());
                    dtoElement.setOrderID(element.getOrderId());
                    dtoElement.setTransactionAmount(element.getTransactionAmount());
                    dtoElement.setAuthorizedAmount(element.getAuthorizedAmount());
                    dtoElement.setRefundedAmount(element.getRefundedAmount());
                    dtoElement.setTransactionResult(element.getTransactionResult());
                    dtoElement.setTimestamp(element.getTimestamp());
                    dtoElement.setAuthorizationNumber(element.getAuthorizationNumber());
                    dtoElement.setAcquireBIN(element.getAcquirerBin());
                    dtoElement.setMerchantID(element.getMerchantId());
                    dtoElement.setTransactionStatus(element.getTransactionStatus());
                    dtoElement.setResponseCodeISO(element.getResponseCodeIso());
                    dtoElement.setPanTail(element.getPanTail());
                    dtoElement.setPanExpiryDate(element.getPanExpiryDate());
                    dtoElement.setPaymentTypePP(element.getPaymentTypePP());
                    dtoElement.setrRN(element.getRRN());
                    dtoElement.setCardType(element.getCardType());

                    dto.oSRElements.add(dtoElement);
                    dtoElement.clearAllIndividualFields();
                }
                if (commonElements != null) {
                    dtoElement.setPanAlias(commonElements.getPanAlias());
                    dtoElement.setPanAliasRev(commonElements.getPanAliasRev());
                    dtoElement.setPanAliasExpDate(commonElements.getPanAliasExpDate());
                    dtoElement.setPanAliasTail(commonElements.getPanAliasTail());
                    dtoElement.setCommonMAC(commonElements.getMac());
                    dto.oSRCommon = dtoElement;
                }
            }
        }

        return dto;
    }


    public CaptureResponse bookingResponseDto(BPWXmlResponse response) {
        CaptureResponse dto = new CaptureResponse();

        if (response != null && response.getData() != null && response.getData().getOperation() != null) {
            Operation operation = response.getData().getOperation();

            dto.setTransactionID(operation.getTransactionId());
            dto.setTimestampReq(operation.getTimestampReq());
            dto.setTimestampElab(operation.getTimestampElab());
            dto.setSrcType(operation.getSrcType());
            dto.setAmount(operation.getAmount());
            dto.setResult(operation.getResult());
            dto.setStatus(operation.getStatus());
            dto.setOpDesc(operation.getOpDescr());

            if (response.getData().getAuthorization() != null && response.getData().getAuthorization().size() == 1)
                dto.setAuthorization(response.getData().getAuthorization().get(0));

        }
        return dto;
    }

    public ThreeDSAuthorization0Response threeDSAuthorization0Response(BPWXmlResponse response) {
        ThreeDSAuthorization0Response dto = new ThreeDSAuthorization0Response();
        if (response == null || response.getData() == null) {
            return dto;
        }
        if (response.getData().getThreeDSchallenge() != null) {
            dto.setCreq(response.getData().getThreeDSchallenge().getCreq());
            dto.setAcsUrl(response.getData().getThreeDSchallenge().getAcsUrl());
            dto.setThreeDSTransId(response.getData().getThreeDSchallenge().getThreeDSTransId());

        }
        if (response.getData().getThreeDSMethod() != null) {
            dto.setThreeDSTransId(response.getData().getThreeDSMethod().getThreeDSTransId());
            dto.setThreeDSMethodData(response.getData().getThreeDSMethod().getThreeDSMethodData());
            dto.setThreeDSMethodUrl(response.getData().getThreeDSMethod().getThreeDSMethodUrl());
        }
        if (response.getData().getAuthorization() != null) {
            Authorization authorization = response.getData().getAuthorization().get(0);
            if (authorization != null) {
                dto.setPaymentType(authorization.getPaymentType());
                dto.setAuthorizationType(authorization.getAuthorizationType());
                dto.setTransactionId(authorization.getTransactionId());
                dto.setNetwork(authorization.getNetwork());
                dto.setOrderId(authorization.getOrderId());
                dto.setTransactionAmount(authorization.getTransactionAmount());
                dto.setAuthorizedAmount(authorization.getAuthorizedAmount());
                dto.setCurrency(authorization.getCurrency());
                dto.setAccountedAmount(authorization.getAccountedAmount());
                dto.setRefundedAmount(authorization.getRefundedAmount());
                dto.setTransactionResult(authorization.getTransactionResult());
                dto.setTimestamp(authorization.getTimestamp());
                dto.setAuthorizationNumber(authorization.getAuthorizationNumber());
                dto.setTransactionStatus(authorization.getTransactionStatus());
                dto.setResponseCodeIso(authorization.getResponseCodeIso());
                dto.setPanTail(authorization.getPanTail());
                dto.setPanExpiryDate(authorization.getPanExpiryDate());
            }

        }
        return dto;
    }
}
