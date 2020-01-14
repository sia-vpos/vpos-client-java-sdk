package it.reply.cof.apos.request;

import it.reply.cof.utils.constants.Constants;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Request {

    @XmlElement(name = "Operation")
    private String operation;
    @XmlElement(name = "Timestamp")
    private String timestamp;
    @XmlElement(name = "MAC")
    private String mac;

    public Request(String operation, Date reqDate) {
        this.operation = operation;
        SimpleDateFormat format = new SimpleDateFormat(Constants.TIMESTAMP_PATTERN);
        this.timestamp = format.format(reqDate);
    }

    public String getOperation() {

        return operation;
    }

    public void setOperation(String operation) {

        this.operation = operation;
    }

    public String getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(String timestamp) {

        this.timestamp = timestamp;
    }

    public String getMac() {

        return mac;
    }

    public void setMac(String mac) {

        this.mac = mac;
    }

}
