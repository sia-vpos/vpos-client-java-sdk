package eu.sia.vpos.client.response.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CardHolderData {

    @XmlElement(name = "CardHolderName")
    private String cardHolderName;

    @XmlElement(name = "CardHolderEmail")
    private String cardHolderEmail;

    @XmlElement(name = "BillingAddressPostalcode")
    private String billingAddressPostalcode;

    @XmlElement(name = "BillingAddressCity")
    private String billingAddressCity;

    @XmlElement(name = "BillingAddressLine1")
    private String billingAddressLine1;

    @XmlElement(name = "BillingAddressLine2")
    private String billingAddressLine2;

    @XmlElement(name = "BillingAddressLine3")
    private String billingAddressLine3;

    @XmlElement(name = "BillingAddressState")
    private String billingAddressState;

    @XmlElement(name = "BillingAddressCountry")
    private String billingAddressCountry;

    @XmlElement(name = "MAC")
    private String mac;

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardHolderEmail() {
        return cardHolderEmail;
    }

    public void setCardHolderEmail(String cardHolderEmail) {
        this.cardHolderEmail = cardHolderEmail;
    }

    public String getBillingAddressPostalcode() {
        return billingAddressPostalcode;
    }

    public void setBillingAddressPostalcode(String billingAddressPostalcode) {
        this.billingAddressPostalcode = billingAddressPostalcode;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingAddressLine3() {
        return billingAddressLine3;
    }

    public void setBillingAddressLine3(String billingAddressLine3) {
        this.billingAddressLine3 = billingAddressLine3;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "CardHolderData{" +
                "cardHolderName='" + cardHolderName + '\'' +
                ", cardHolderEmail='" + cardHolderEmail + '\'' +
                ", billingAddressPostalcode='" + billingAddressPostalcode + '\'' +
                ", billingAddressCity='" + billingAddressCity + '\'' +
                ", billingAddressLine1='" + billingAddressLine1 + '\'' +
                ", billingAddressLine2='" + billingAddressLine2 + '\'' +
                ", billingAddressLine3='" + billingAddressLine3 + '\'' +
                ", billingAddressState='" + billingAddressState + '\'' +
                ", billingAddressCountry='" + billingAddressCountry + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
