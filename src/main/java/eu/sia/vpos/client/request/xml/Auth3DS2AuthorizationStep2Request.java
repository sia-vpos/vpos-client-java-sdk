package eu.sia.vpos.client.request.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Auth3DS2AuthorizationStep2Request {
    @XmlElement(name = "Header")
    private Header header;

    @XmlElement(name = "ThreeDSTransID")
    private String threeDSTransID;

    public Auth3DS2AuthorizationStep2Request(Date date){
        this.header = new Header(date);

    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getThreeDSTransID() {
        return threeDSTransID;
    }

    public void setThreeDSTransID(String threeDSTransID) {
        this.threeDSTransID = threeDSTransID;
    }
}