package eu.sia.vpos.client.response.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ThreeDSChallenge {

    @XmlElement(name = "ThreeDSTransId")
    private String threeDSTransId;
    @XmlElement(name = "CReq")
    private String creq;
    @XmlElement(name = "URLAcs")
    private String acsUrl;
    @XmlElement(name = "MAC")
    private String mac;

    public String getThreeDSTransId() {
        return threeDSTransId;
    }

    public String getCreq() {
        return creq;
    }

    public String getAcsUrl() {
        return acsUrl;
    }

    public String getMac() {
        return mac;
    }
}
