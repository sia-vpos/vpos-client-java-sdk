package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class RefundRequest extends GeneralRequest {

    @XmlElement(name = "OpDescr")
    private String opDescr;

    @XmlElement(name = "Options")
    private String options;

    public RefundRequest(){}

    public RefundRequest(Date reqDate) {
        super(reqDate);
    }

    public RefundRequest(){

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
