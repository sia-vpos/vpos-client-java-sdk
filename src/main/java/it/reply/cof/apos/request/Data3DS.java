package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Data3DS {

    @XmlElement(name = "Service")
    private String service;

    @XmlElement(name = "Eci")
    private String eci;

    @XmlElement(name = "Xid")
    private String xid;

    @XmlElement(name = "CAVV")
    private String cavv;

    @XmlElement(name = "ParesStatus")
    private String paresStatus;

    @XmlElement(name = "ScEnrollStatus")
    private String scEnrollStatus;

    @XmlElement(name = "SignatureVerifytion")
    private String signatureVerifytion;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getCavv() {
        return cavv;
    }

    public void setCavv(String cavv) {
        this.cavv = cavv;
    }

    public String getParesStatus() {
        return paresStatus;
    }

    public void setParesStatus(String paresStatus) {
        this.paresStatus = paresStatus;
    }

    public String getScEnrollStatus() {
        return scEnrollStatus;
    }

    public void setScEnrollStatus(String scEnrollStatus) {
        this.scEnrollStatus = scEnrollStatus;
    }

    public String getSignatureVerifytion() {
        return signatureVerifytion;
    }

    public void setSignatureVerifytion(String signatureVerifytion) {
        this.signatureVerifytion = signatureVerifytion;
    }

}
