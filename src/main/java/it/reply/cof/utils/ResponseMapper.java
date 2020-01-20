package it.reply.cof.utils;



import it.reply.cof.apos.request.Data;
import it.reply.cof.apos.response.*;
import it.reply.cof.dto.response.*;

import java.util.List;

public class ResponseMapper {

    /**
     * Maps a Verify Response to its dto
     * @param response contains all the relevant information
     * @return
     */
    public VerifyResponseDto mapVerifyResponse(BPWXmlResponse response) {
        VerifyResponseDto dto = new VerifyResponseDto();

        if (response != null) {
            Data data = response.getData();
            if (data != null) {
                dto.setOperation(data.getVerifyResponse().getOperation());
                if (data.getOperation() != null) {
                    dto.setResult(data.getOperation().getResult());
                    dto.setTransactionID(data.getOperation().getTransactionId());
                    dto.setMAC(data.getOperation().getMac());
                }
            }
        }
        return dto;
    }

    /**
     * Maps a Confirmation Response to its dto
     * @param response contains all the relevant information
     * @return
     */
    public ConfirmationResponseDto mapConfirmationResponse(BPWXmlResponse response) {
        ConfirmationResponseDto dto = new ConfirmationResponseDto();


        if (response != null && response.getData() != null && response.getData().getAuthorization() != null) {
            List<Authorization> authorizations = response.getData().getAuthorization();
            if (authorizations.size() == 1) {
                Authorization authorization = authorizations.get(0);
                if (authorization != null) {
                    dto.setPaymentType(authorization.getPaymentType());
                    dto.setAuthorizationType(authorization.getAuthorizationType());
                    dto.setTransactionID(authorization.getTransactionId());
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
                    dto.setAcquireBIN(authorization.getAcquirerBin());
                    dto.setMerchantID(authorization.getMerchantId());
                    dto.setTransactionStatus(authorization.getTransactionStatus());
                    dto.setResponseCodeISO(authorization.getResponseCodeIso());
                    dto.setPanTail(authorization.getPanTail());
                    dto.setPanExpiryDate(authorization.getPanExpiryDate());
                    dto.setMAC(authorization.getMac());
                    dto.setResponseCodeISO(authorization.getResponseCodeIso());
                }
            }
        }

        return dto;
    }

    /**
     * Maps a Refund Response to its dto
     * @param response contains all the relevant information
     * @return
     */
    public RefundResponseDto mapRefundResponseDto(BPWXmlResponse response) {
        RefundResponseDto dto = new RefundResponseDto();

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
                    dto.setAuthorizatedAmount(authorization.getAuthorizeAmount());
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
                    dto.setAuthorizationMAC(authorization.getMac());
                }
            }

        }
        return dto;
    }

    /**
     * Maps an Order Status Response to its dto
     * @param response contains all the relevant information
     * @return
     */
    public OrderStatusResponseListDto mapOrderStatusResponse(BPWXmlResponse response) {
        OrderStatusResponseListDto dto = new OrderStatusResponseListDto();

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
                    dtoElement.setRRN(element.getRRN());
                    dtoElement.setCardType(element.getCardType());
                    dtoElement.setMAC(element.getMac());

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

    /**
     * Maps the Step 1 Response of a 3D Secure authorization to its dto
     * @param response contains all the relevant information
     * @return
     */
    public Auth3DSResponseDto auth3DSResponseDto(BPWXmlResponse response){
        Auth3DSResponseDto dto = new Auth3DSResponseDto();

        if (response != null && response.getData() != null && response.getData().getAuthorization() != null){
            Authorization authorization = response.getData().getAuthorization().get(0);
            PanAliasData panAliasData = response.getData().getPanAliasData();
            VBVRedirect vbvRedirect = response.getData().getVbvRedirect();

            if(authorization != null) {

                dto.setPaymentType(authorization.getPaymentType());
                dto.setAuthorizationType(authorization.getAuthorizationType());
                dto.setTransactionID(authorization.getTransactionId());
                dto.setNetwork(authorization.getNetwork());
                dto.setOrderID(authorization.getOrderId());
                dto.setTransactionAmount(authorization.getTransactionAmount());
                dto.setAuthorizedAmount(authorization.getAuthorizedAmount());
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
                dto.setResponseCodeIso(authorization.getResponseCodeIso());
                dto.setPanTail(authorization.getPanTail());
                dto.setPanExpiryDate(authorization.getPanExpiryDate());
                dto.setPaymentTypePP(authorization.getPaymentTypePP());
                dto.setRRN(authorization.getRRN());
                dto.setCardType(authorization.getCardType());
                dto.setMAC(authorization.getMac());

            }

            if(vbvRedirect != null) {
                dto.setPaReq(vbvRedirect.getPaReq());
                dto.setAcsURL(vbvRedirect.getAcsURL());
            }
            if(panAliasData != null) {
                dto.setPanAlias(panAliasData.getPanAlias());
                dto.setPanAliasRev(panAliasData.getPanAliasRev());
                dto.setPanAliasExpDate(panAliasData.getPanAliasExpDate());
                dto.setPanAliasTail(panAliasData.getPanAliasTail());

            }

        }

        return dto;
    }

    /**
     * Maps the Step 1 Response of a 3D Secure authorization to its dto
     * @param response contains all the relevant information
     * @return
     */
    public Auth3DSStep2ResponseDto auth3DSStep2ResponseDto(BPWXmlResponse response) {
        Auth3DSStep2ResponseDto dto = new Auth3DSStep2ResponseDto();

        if (response != null && response.getData() != null && response.getData().getAuthorization() != null) {
            Authorization authorization = response.getData().getAuthorization().get(0);
            PanAliasData panAliasData = response.getData().getPanAliasData();

            if(authorization != null) {
                dto.setPaymentType(authorization.getPaymentType());
                dto.setAuthorizationType(authorization.getAuthorizationType());
                dto.setTransactionID(authorization.getTransactionId());
                dto.setNetwork(authorization.getNetwork());
                dto.setOrderID(authorization.getOrderId());
                dto.setTransactionAmount(authorization.getTransactionAmount());
                dto.setAuthorizedAmount(authorization.getAuthorizedAmount());
                dto.setCurrency(authorization.getCurrency());
                dto.setAccountedAmount(authorization.getAccountedAmount());
                dto.setRefundedAmount(authorization.getRefundedAmount());
                dto.setTransactionResult(authorization.getTransactionResult());
                dto.setTimestamp(authorization.getTimestamp());
                dto.setAuthorizationNumber(authorization.getAuthorizationNumber());
                dto.setAcquireBIN(authorization.getAcquirerBin());
                dto.setMerchantID(authorization.getMerchantId());
                dto.setTransactionStatus(authorization.getTransactionStatus());
                dto.setResponseCodeIso(authorization.getResponseCodeIso());
                dto.setPanTail(authorization.getPanTail());
                dto.setPanExpiryDate(authorization.getPanExpiryDate());
                dto.setMAC(authorization.getMac());
            }

            if(panAliasData != null) {
                dto.setPanAlias(panAliasData.getPanAlias());
                dto.setPanAliasRev(panAliasData.getPanAliasRev());
                dto.setPanAliasExpDate(panAliasData.getPanAliasExpDate());
                dto.setPanAliasTail(panAliasData.getPanAliasTail());


            }
        }

        return dto;

    }
}
