package it.reply.cof.dto.response;

public class ConfirmationResponseDto {

    private String paymentType;
    private String authorizationType;
    private String transactionID;
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
    private String acquireBIN;
    private String merchantID;
    private String transactionStatus;
    private String responseCodeISO;
    private String panTail;
    private String panExpiryDate;
    private String MAC;

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

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

}
