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


        if (response != null && response.getData() != null && response.getData().getOperation() != null) {
                Operation operation = response.getData().getOperation();

                dto.setTransactionID(operation.getTransactionId());
                dto.setTimestampReq(operation.getTimestampReq());
                dto.setTimestampElab(operation.getTimestampElab());
                dto.setSrcType(operation.getSrcType());
                dto.setAmount(operation.getAmount());
                dto.setResult(operation.getResult());
                dto.setStatus(operation.getStatus());
                dto.setOpDescr(operation.getOpDescr());
                if (operation.getAuthorization() != null)
                    dto.setAuthorization(response.getData().getOperation().getAuthorization());
        }else {
            if(response != null){
                dto.setResult(response.getResult());
                dto.setTimestampReq(response.getTimestamp());
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

            if (response.getData().getOperation().getAuthorization() != null)
                dto.setAuthorization(response.getData().getOperation().getAuthorization());

        }else {
            if(response != null){
                dto.setResult(response.getResult());
                dto.setTimestampReq(response.getTimestamp());
            }
        }
        return dto;
    }

    public ThreeDSAuthorization0Response threeDSAuthorization0Response(BPWXmlResponse response) {
        ThreeDSAuthorization0Response dto = new ThreeDSAuthorization0Response();
        dto.setResult(response.getResult());
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
        if (response.getData().getPanAliasData() != null) {
            PanAliasData pan = response.getData().getPanAliasData();
            dto.setPanAlias(pan.getPanAlias());
            dto.setPanAliasExpDate(pan.getPanAliasExpDate());
            dto.setPanAliasRev(pan.getPanAliasRev());
            dto.setPanAliasTail(pan.getPanAliasTail());
        }
        return dto;
    }

    public ThreeDSAuthorization1Response threeDSAuthorization1Response(BPWXmlResponse response) {
        ThreeDSAuthorization1Response dto = new ThreeDSAuthorization1Response();
        dto.setResult(response.getResult());
        if (response == null || response.getData() == null) {
            return dto;
        }
        if (response.getData().getThreeDSchallenge() != null) {
            dto.setCreq(response.getData().getThreeDSchallenge().getCreq());
            dto.setAcsUrl(response.getData().getThreeDSchallenge().getAcsUrl());
            dto.setThreeDSTransId(response.getData().getThreeDSchallenge().getThreeDSTransId());

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
        if (response.getData().getPanAliasData() != null) {
            PanAliasData pan = response.getData().getPanAliasData();
            dto.setPanAlias(pan.getPanAlias());
            dto.setPanAliasExpDate(pan.getPanAliasExpDate());
            dto.setPanAliasRev(pan.getPanAliasRev());
            dto.setPanAliasTail(pan.getPanAliasTail());
        }
        return dto;
    }

    public ThreeDSAuthorization2Response threeDSAuthorization2Response(BPWXmlResponse response) {
        ThreeDSAuthorization2Response dto = new ThreeDSAuthorization2Response();
        dto.setResult(response.getResult());
        if (response == null || response.getData() == null) {
            return dto;
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
        if (response.getData().getPanAliasData() != null) {
            PanAliasData pan = response.getData().getPanAliasData();
            dto.setPanAlias(pan.getPanAlias());
            dto.setPanAliasExpDate(pan.getPanAliasExpDate());
            dto.setPanAliasRev(pan.getPanAliasRev());
            dto.setPanAliasTail(pan.getPanAliasTail());
        }

        return dto;
    }

    public AuthorizationResponse authorizationResponse(BPWXmlResponse response) {
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.setResult(response.getResult());

        if (response.getData() != null && response.getData().getAuthorization() != null) {
            Authorization auth = response.getData().getAuthorization().get(0);
            authorizationResponse.setPaymentType(auth.getPaymentType());
            authorizationResponse.setAuthorizationType(auth.getAuthorizationType());
            authorizationResponse.setTransactionId(auth.getTransactionId());
            authorizationResponse.setNetwork(auth.getNetwork());
            authorizationResponse.setOrderId(auth.getOrderId());
            authorizationResponse.setTransactionAmount(auth.getTransactionAmount());
            authorizationResponse.setAuthorizedAmount(auth.getAuthorizedAmount());
            authorizationResponse.setCurrency(auth.getCurrency());
            authorizationResponse.setExponent(auth.getExponent());
            authorizationResponse.setAccountedAmount(auth.getAccountedAmount());
            authorizationResponse.setRefundedAmount(auth.getRefundedAmount());
            authorizationResponse.setTransactionResult(auth.getTransactionResult());
            authorizationResponse.setTimestamp(auth.getTimestamp());
            authorizationResponse.setAuthorizationNumber(auth.getAuthorizationNumber());
            authorizationResponse.setAcquirerBin(auth.getAcquirerBin());
            authorizationResponse.setMerchantId(auth.getMerchantId());
            authorizationResponse.setTransactionStatus(auth.getTransactionStatus());
            authorizationResponse.setResponseCodeIso(auth.getResponseCodeIso());
            authorizationResponse.setPanTail(auth.getPanTail());
            authorizationResponse.setPanExpiryDate(auth.getPanExpiryDate());
            authorizationResponse.setPaymentTypePP(auth.getPaymentTypePP());
            authorizationResponse.setRRN(auth.getRRN());
            authorizationResponse.setCardType(auth.getCardType());
            return authorizationResponse;
        }
        return authorizationResponse;

    }
}
    
