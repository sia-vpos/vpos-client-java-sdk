package it.reply.cof.dto.request;

public class
OrderStatusRequestDto {
    private String shopId;
    private String operatorId;
    private String orderId;
    private String productRef;
    private String options;

    public OrderStatusRequestDto(String shopId, String operatorId, String orderId, String productRef, String options) {
        this.shopId = shopId;
        this.operatorId = operatorId;
        this.orderId = orderId;
        this.productRef = productRef;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
