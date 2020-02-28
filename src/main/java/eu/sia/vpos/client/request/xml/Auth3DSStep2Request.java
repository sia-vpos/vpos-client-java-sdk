package eu.sia.vpos.client.request.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Auth3DSStep2Request {

    @XmlElement(name = "Header")
    private Header header;

    @XmlElement(name = "OriginalReqRefNum")
    private String originalReqRefNum;

    @XmlElement(name = "PaRes")
    private String paRes;

    @XmlElement(name = "Acquirer")
    private String Acquirer;

    public Auth3DSStep2Request() {
    }

    public Auth3DSStep2Request(Date date) {
        this.header = new Header(date);
    }

    public String getAcquirer() {
        return Acquirer;
    }

    public void setAcquirer(String acquirer) {
        Acquirer = acquirer;
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

    public String getPaRes() {
        return paRes;
    }

    public void setPaRes(String paRes) {
        this.paRes = paRes;
    }

}
