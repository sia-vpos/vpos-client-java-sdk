package it.reply.cof.dto.response;

public class OrderStatusResponseDto {

    private String paymentType;
    private String authorizationType;
    private String transactionID;
    private String network;
    private String orderID;
    private String transactionAmount;
    private String authorizedAmount;
    private String refundedAmount;
    private String transactionResult;
    private String timestamp;
    private String authorizationNumber;
    private String acquireBIN;
    private String merchantID;
    private String transactionStatus;
    private String responseCodeISO;
    private String panTail;
    private String panExpiryDate;
    private String paymentTypePP;
    private String RRN;
    private String cardType;
    private String MAC;
    private String panAlias;
    private String panAliasRev;
    private String panAliasExpDate;
    private String panAliasTail;
    private String commonMAC;

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

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public String getAcquireBIN() {
        return acquireBIN;
    }

    public void setAcquireBIN(String acquireBIN) {
        this.acquireBIN = acquireBIN;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getResponseCodeISO() {
        return responseCodeISO;
    }

    public void setResponseCodeISO(String responseCodeISO) {
        this.responseCodeISO = responseCodeISO;
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

    public String getPaymentTypePP() {
        return paymentTypePP;
    }

    public void setPaymentTypePP(String paymentTypePP) {
        this.paymentTypePP = paymentTypePP;
    }

    public String getRRN() {
        return RRN;
    }

    public void setRRN(String RRN) {
        this.RRN = RRN;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getPanAlias() {
        return panAlias;
    }

    public void setPanAlias(String panAlias) {
        this.panAlias = panAlias;
    }

    public String getPanAliasRev() {
        return panAliasRev;
    }

    public void setPanAliasRev(String panAliasRev) {
        this.panAliasRev = panAliasRev;
    }

    public String getPanAliasExpDate() {
        return panAliasExpDate;
    }

    public void setPanAliasExpDate(String panAliasExpDate) {
        this.panAliasExpDate = panAliasExpDate;
    }

    public String getPanAliasTail() {
        return panAliasTail;
    }

    public void setPanAliasTail(String panAliasTail) {
        this.panAliasTail = panAliasTail;
    }

    public String getCommonMAC() {
        return commonMAC;
    }

    public void setCommonMAC(String commonMAC) {
        this.commonMAC = commonMAC;
    }

    public void clearAllIndividualFields() {

        this.setPaymentType(null);
        this.setAuthorizationType(null);
        this.setTransactionID(null);
        this.setNetwork(null);
        this.setOrderID(null);
        this.setTransactionAmount(null);
        this.setAuthorizedAmount(null);
        this.setRefundedAmount(null);
        this.setTransactionResult(null);
        this.setTimestamp(null);
        this.setAuthorizationNumber(null);
        this.setAcquireBIN(null);
        this.setMerchantID(null);
        this.setTransactionStatus(null);
        this.setResponseCodeISO(null);
        this.setPanTail(null);
        this.setPanExpiryDate(null);
        this.setPaymentTypePP(null);
        this.setRRN(null);
        this.setCardType(null);
        this.setMAC(null);

    }

}
