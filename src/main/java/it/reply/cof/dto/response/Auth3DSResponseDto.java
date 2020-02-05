package it.reply.cof.dto.response;

import it.reply.cof.dto.request.Auth3DSDto;

public class Auth3DSResponseDto extends Auth3DSDto {
    private String paymentType;
    private String authorizationType;
    private String transactionID;
    private String network;
    private String orderID;
    private String transactionAmount;
    private String authorizedAmount;
    private String currency;
    private String exponent;
    private String accountedAmount;
    private String refundedAmount;
    private String transactionResult;
    private String timestamp;
    private String authorizationNumber;
    private String acquireBIN;
    private String merchantID;
    private String transactionStatus;
    private String responseCodeIso;
    private String panTail;
    private String panExpiryDate;
    private String PaymentTypePP;
    private String RRN;
    private String CardType;
    private String MAC;
    private String PaReq;
    private String AcsURL;
    private String PanAlias;
    private String PanAliasRev;
    private String PanAliasExpDate;
    private String PanAliasTail;


    public String getPaReq() {
        return PaReq;
    }

    public void setPaReq(String paReq) {
        PaReq = paReq;
    }

    public String getAcsURL() {
        return AcsURL;
    }

    public void setAcsURL(String acsURL) {
        AcsURL = acsURL;
    }

    public String getPanAlias() {
        return PanAlias;
    }

    public void setPanAlias(String panAlias) {
        PanAlias = panAlias;
    }

    public String getPanAliasRev() {
        return PanAliasRev;
    }

    public void setPanAliasRev(String panAliasRev) {
        PanAliasRev = panAliasRev;
    }

    public String getPanAliasExpDate() {
        return PanAliasExpDate;
    }

    public void setPanAliasExpDate(String panAliasExpDate) {
        PanAliasExpDate = panAliasExpDate;
    }

    public String getPanAliasTail() {
        return PanAliasTail;
    }

    public void setPanAliasTail(String panAliasTail) {
        PanAliasTail = panAliasTail;
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


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
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

    public String getPaymentTypePP() {
        return PaymentTypePP;
    }

    public void setPaymentTypePP(String paymentTypePP) {
        PaymentTypePP = paymentTypePP;
    }

    public String getRRN() {
        return RRN;
    }

    public void setRRN(String RRN) {
        this.RRN = RRN;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getMAC() {
        return this.MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }


    public String getTransactionResult() {
        return transactionResult;
    }

    public void setTransactionResult(String transactionResult) {
        this.transactionResult = transactionResult;
    }


}
