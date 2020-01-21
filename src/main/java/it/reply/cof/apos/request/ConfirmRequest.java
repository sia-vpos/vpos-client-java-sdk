package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class ConfirmRequest extends GeneralRequest {

    @XmlElement(name = "AccountingMode")
    private String accountingMode;

    @XmlElement(name = "CloseOrder")
    private String closeOrder;

    @XmlElement(name = "Options")
    private String options;

    public ConfirmRequest(){
        super();
    }

    public ConfirmRequest(Date reqDate) {
        super(reqDate);
    }

    public String getAccountingMode() {
        return accountingMode;
    }

    public void setAccountingMode(String accountingMode) {
        this.accountingMode = accountingMode;
    }

    public String getCloseOrder() {
        return closeOrder;
    }

    public void setCloseOrder(String closeOrder) {
        this.closeOrder = closeOrder;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
