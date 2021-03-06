package eu.sia.vpos.client.response.xml;

import eu.sia.vpos.client.request.xml.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BPWXmlResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class BPWXmlResponse {

    @XmlElement(name = "Timestamp")
    private String timestamp;
    @XmlElement(name = "Result")
    private String result;
    @XmlElement(name = "MAC")
    private String mac;
    @XmlElement(name = "Data")
    private Data data;

    public String getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(String timestamp) {

        this.timestamp = timestamp;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {

        this.result = result;
    }

    public String getMac() {

        return mac;
    }

    public void setMac(String mac) {

        this.mac = mac;
    }

    public Data getData() {

        return data;
    }

    public void setData(Data data) {

        this.data = data;
    }


}
