package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatusRequest {

    @XmlElement(name = "Header")
    protected Header header;

    @XmlElement(name = "OriginalReqRefNum")
    protected String originalReqRefNum;

    @XmlElement(name = "OrderID")
    protected String orderId;

    @XmlElement(name = "ProductRef")
    protected String productRef;

    @XmlElement(name = "Options")
    private String options;

    public StatusRequest() {
    }

    public StatusRequest(Date reqDate) {
        this.header = new Header(reqDate);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getOriginalReqRefNum() {
        return originalReqRefNum;
    }

    public void setOriginalReqRefNum(String originalReqRefNum) {
        this.originalReqRefNum = originalReqRefNum;
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

    public String toString() {
        return "StatusRequest: Header: " + this.getHeader().toString() + " OriginalReqRefNum: " + this.originalReqRefNum + " OrderID: " + this.orderId + " ProductRef: " + this.productRef
                + " Options: " + this.options;
    }
}
