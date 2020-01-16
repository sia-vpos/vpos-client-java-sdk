package it.reply.cof.utils;


import it.reply.cof.apos.request.Data;
import it.reply.cof.apos.response.Authorization;
import it.reply.cof.apos.response.BPWXmlResponse;
import it.reply.cof.apos.response.Operation;
import it.reply.cof.apos.response.PanAliasData;
import it.reply.cof.dto.response.*;

import java.util.List;

public class Mapper {


    public VerifyResponseDto mapVerifyResponse(BPWXmlResponse response){

        VerifyResponseDto dto = new VerifyResponseDto();
        
        if(response != null) {
            Data data = response.getData();
            if(data != null) {
                dto.setOperation(data.getVerifyResponse().getOperation());
                if(data.getOperation() != null) {
                    dto.setResult(data.getOperation().getResult());
                    dto.setTransactionID(data.getOperation().getTransactionId());
                    dto.setMAC(data.getOperation().getMac());
                }
            }
        }
        return dto;

    }

   public ConfirmationResponseDto mapConfirmationResponse(BPWXmlResponse response){

       ConfirmationResponseDto dto = new ConfirmationResponseDto();

       if(response != null) {
           if(response.getData() != null && response.getData().getAuthorization() != null) {
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
       }

       return dto;

   }

   public RefundResponseDto mapRefundResponseDto(BPWXmlResponse response){

       RefundResponseDto dto = new RefundResponseDto();

       if(response != null) {

           if(response.getData() != null && response.getData().getOperation() != null) {
               Operation operation = response.getData().getOperation();
                if(operation != null) {
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

        if(response.getData() != null && response.getData().getAuthorization() != null && response.getData().getAuthorization().size() != 0){
            Authorization authorization = response.getData().getAuthorization().get(0);
            if(authorization != null){
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

   public OrderStatusResponseListDto mapOrderStatusResponse(BPWXmlResponse response){

       OrderStatusResponseListDto dto = new OrderStatusResponseListDto();
       if(response != null) {
           if(response.getData() != null && response.getData().getAuthorization() != null) {
               List<Authorization> authorizationList = response.getData().getAuthorization();

               if (authorizationList.size() != 0) {

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
                    if(commonElements != null) {
                        dtoElement.setPanAlias(commonElements.getPanAlias());
                        dtoElement.setPanAliasRev(commonElements.getPanAliasRev());
                        dtoElement.setPanAliasExpDate(commonElements.getPanAliasExpDate());
                        dtoElement.setPanAliasTail(commonElements.getPanAliasTail());
                        dtoElement.setCommonMAC(commonElements.getMac());

                        dto.oSRCommon = dtoElement;
                    }
               }
           }
       }
       return dto;

   }
}
