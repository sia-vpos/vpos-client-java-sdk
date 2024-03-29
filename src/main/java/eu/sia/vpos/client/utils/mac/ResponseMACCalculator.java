package eu.sia.vpos.client.utils.mac;

import eu.sia.vpos.client.response.xml.*;
import eu.sia.vpos.client.utils.exception.VPosClientException;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class implementing responses' MAC calculation
 *
 * @author Gabriel Raul Marini
 */
public class ResponseMACCalculator {

    private Encoder encoder;

    public ResponseMACCalculator(Encoder encoder) {
        this.encoder = encoder;
    }

    public String getBPWXmlResponseMac(BPWXmlResponse response, String key) throws VPosClientException {
        List<String> valueList = new ArrayList<>();
        valueList.add(response.getTimestamp());
        valueList.add(response.getResult());
        return encoder.getMac(valueList, key);
    }

    public String getAuthorizationMac(Authorization authorization, String key) throws VPosClientException {
        List<String> valueList = new ArrayList<>();
        valueList.add(authorization.getAuthorizationType());
        valueList.add(authorization.getTransactionId());
        valueList.add(authorization.getNetwork());
        valueList.add(authorization.getOrderId());
        valueList.add(authorization.getTransactionAmount());
        valueList.add(authorization.getAuthorizedAmount());
        valueList.add(authorization.getCurrency());
        valueList.add(authorization.getAccountedAmount());
        valueList.add(authorization.getRefundedAmount());
        valueList.add(authorization.getTransactionResult());
        valueList.add(authorization.getTimestamp());
        valueList.add(authorization.getAuthorizationNumber());
        valueList.add(authorization.getAcquirerBin());
        valueList.add(authorization.getMerchantId());
        valueList.add(authorization.getTransactionStatus());
        valueList.add(authorization.getResponseCodeIso());
        valueList.add(authorization.getPanTail());
        valueList.add(authorization.getPanExpiryDate());
        valueList.add(authorization.getPaymentTypePP());
        valueList.add(authorization.getRRN());
        valueList.add(authorization.getCardType());
        valueList.add(authorization.getCardholderInfo());
        valueList.add(authorization.getInstallmentsNumber());
        valueList.add(authorization.getTicklerMerchantCode());
        valueList.add(authorization.getTicklerPlanCode());
        valueList.add(authorization.getTicklerSubscriptionCode());
        return encoder.getMac(valueList, key);
    }

    public String getOperationMac(Operation operation, String key) throws VPosClientException {
        List<String> valueList = new ArrayList<>();
        valueList.add(operation.getTransactionId());
        valueList.add(operation.getTimestampReq());
        valueList.add(operation.getTimestampElab());
        valueList.add(operation.getSrcType());
        valueList.add(operation.getAmount());
        valueList.add(operation.getResult());
        valueList.add(operation.getStatus());
        valueList.add(operation.getOpDescr());
        return encoder.getMac(valueList, key);
    }

    public String getThreeDSMethodMac(ThreeDSMethod threeDSMethod, String key) throws VPosClientException {
        List<String> valueList = new ArrayList<>();
        valueList.add(threeDSMethod.getThreeDSTransId());
        valueList.add(threeDSMethod.getThreeDSMethodData());
        valueList.add(threeDSMethod.getThreeDSMethodUrl());
        return encoder.getMac(valueList, key);
    }

    public String getPanAliasDataMac(PanAliasData panAliasData, String key) throws VPosClientException {
        List<String> valueList = new ArrayList<>();
        valueList.add(panAliasData.getPanAliasRev());
        valueList.add(panAliasData.getPanAlias());
        valueList.add(panAliasData.getPanAliasExpDate());
        valueList.add(panAliasData.getPanAliasTail());
        valueList.add(panAliasData.getcRecurr());
        return encoder.getMac(valueList, key);
    }

    public String getThreeDSChallengeMac(ThreeDSChallenge threeDSChallenge, String key) throws VPosClientException {
        List<String> valueList = new ArrayList<>();
        valueList.add(threeDSChallenge.getThreeDSTransId());
        valueList.add(threeDSChallenge.getCreq());
        valueList.add(threeDSChallenge.getAcsUrl());
        return encoder.getMac(valueList, key);
    }
}
