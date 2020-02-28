package eu.sia.vpos.client.request;

public class OrderStatusRequest extends RequestDto {

    private String orderId;
    private String productRef;

    public OrderStatusRequest(String shopId, String operatorId, String orderId, String productRef, String options) {
        setShopId(shopId);
        setOperatorId(operatorId);
        this.orderId = orderId;
        this.productRef = productRef;
        setOptions(options);
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

}
