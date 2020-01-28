package it.reply.cof.dto.request;


public class RefundRequestDto extends RequestDto {

    private String transactionId;
    private String orderId;
    private String amount;
    private String currency;
    private String exponent;
    private String opDescr;

    public RefundRequestDto(String shopId, String operatorId, String transactionId, String orderId, String amount, String currency, String exponent, String opDescr) {
        this.shopId = shopId;
        this.operatorId = operatorId;
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.exponent = exponent;
        this.opDescr = opDescr;
    }

    public RefundRequestDto(String shopId, String operatorId, String transactionId, String orderId, String amount, String currency, String exponent, String opDescr, String options) {
        this.shopId = shopId;
        this.operatorId = operatorId;
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.exponent = exponent;
        this.opDescr = opDescr;
        this.options = options;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getOpDescr() {
        return opDescr;
    }

    public void setOpDescr(String opDescr) {
        this.opDescr = opDescr;
    }

}
