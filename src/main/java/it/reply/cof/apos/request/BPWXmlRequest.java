package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BPWXmlRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class BPWXmlRequest {

    @XmlElement(name = "Release")
    private String release;
    @XmlElement(name = "Request")
    private Request request;
    @XmlElement(name = "Data")
    private Data data;

    public String getRelease() {

        return release;
    }

    public void setRelease(String release) {

        this.release = release;
    }

    public Request getRequest() {

        return request;
    }

    public void setRequest(Request request) {

        this.request = request;
    }

    public Data getData() {

        return data;
    }

    public void setData(Data data) {

        this.data = data;
    }

}
