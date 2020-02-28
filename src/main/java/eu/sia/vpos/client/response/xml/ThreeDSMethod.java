package eu.sia.vpos.client.response.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ThreeDSMethod {
    @XmlElement(name = "ThreeDSTransId")
    private String threeDSTransId;
    @XmlElement(name = "ThreeDSMethodData")
    private String threeDSMethodData;
    @XmlElement(name = "ThreeDSMethodUrl")
    private String threeDSMethodUrl;
    @XmlElement(name = "MAC")
    private String mac;

    public String getThreeDSTransId() {
        return threeDSTransId;
    }

    public String getThreeDSMethodData() {
        return threeDSMethodData;
    }

    public String getThreeDSMethodUrl() {
        return threeDSMethodUrl;
    }

    public String getMac() {
        return mac;
    }
}
