package eu.sia.vpos.client.response;

import java.io.Serializable;

public class ThreeDSAuthorization2Response implements Serializable {

    private String result;
    private String paymentType;
    private String authorizationType;
    private String transactionId;
    private String network;
    private String orderId;
    private String transactionAmount;
    private String authorizedAmount;
    private String currency;
    private String accountedAmount;
    private String refundedAmount;
    private String transactionResult;
    private String timestamp;
    private String authorizationNumber;
    private String transactionStatus;
    private String responseCodeIso;
    private String panTail;
    private String panExpiryDate;

    //Pan Alias Data
    private String panAlias;
    private String panAliasExpDate;
    private String panAliasRev;
    private String panAliasTail;
    private String cRecurr;

    private String cardholderInfo;
    private String installmentsNumber;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(String authorizationType) {
        this.authorizationType = authorizationType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(String authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountedAmount() {
        return accountedAmount;
    }

    public void setAccountedAmount(String accountedAmount) {
        this.accountedAmount = accountedAmount;
    }

    public String getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(String refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public String getTransactionResult() {
        return transactionResult;
    }

    public void setTransactionResult(String transactionResult) {
        this.transactionResult = transactionResult;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public void setAuthorizationNumber(String authorizationNumber) {
        this.authorizationNumber = authorizationNumber;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getResponseCodeIso() {
        return responseCodeIso;
    }

    public void setResponseCodeIso(String responseCodeIso) {
        this.responseCodeIso = responseCodeIso;
    }

    public String getPanTail() {
        return panTail;
    }

    public void setPanTail(String panTail) {
        this.panTail = panTail;
    }

    public String getPanExpiryDate() {
        return panExpiryDate;
    }

    public void setPanExpiryDate(String panExpiryDate) {
        this.panExpiryDate = panExpiryDate;
    }

    public String getPanAlias() {
        return panAlias;
    }

    public void setPanAlias(String panAlias) {
        this.panAlias = panAlias;
    }

    public String getPanAliasExpDate() {
        return panAliasExpDate;
    }

    public void setPanAliasExpDate(String panAliasExpDate) {
        this.panAliasExpDate = panAliasExpDate;
    }

    public String getPanAliasRev() {
        return panAliasRev;
    }

    public void setPanAliasRev(String panAliasRev) {
        this.panAliasRev = panAliasRev;
    }

    public String getPanAliasTail() {
        return panAliasTail;
    }

    public void setPanAliasTail(String panAliasTail) {
        this.panAliasTail = panAliasTail;
    }

    public String getcRecurr() {
        return cRecurr;
    }

    public void setcRecurr(String cRecurr) {
        this.cRecurr = cRecurr;
    }

    public String getCardholderInfo() {
        return cardholderInfo;
    }

    public void setCardholderInfo(String cardholderInfo) {
        this.cardholderInfo = cardholderInfo;
    }

    public String getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(String installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }

}
