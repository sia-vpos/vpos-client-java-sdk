package it.reply.cof.dto.request;


public class RefundRequestDto {

    private String shopId;
    private String operatorId;
    private String TransactionId;
    private String orderId;
    private String amount;
    private String currency;
    private String exponent;
    private String opDescr;
    private String options;

    public RefundRequestDto(String shopId, String operatorId, String transactionId, String orderId, String amount, String currency, String exponent, String opDescr) {
        this.shopId = shopId;
        this.operatorId = operatorId;
        TransactionId = transactionId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.exponent = exponent;
        this.opDescr = opDescr;
    }

    public RefundRequestDto(String shopId, String operatorId, String transactionId, String orderId, String amount, String currency, String exponent, String opDescr, String options) {
        this.shopId = shopId;
        this.operatorId = operatorId;
        TransactionId = transactionId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.exponent = exponent;
        this.opDescr = opDescr;
        this.options = options;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
