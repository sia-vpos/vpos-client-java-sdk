package eu.sia.vpos.client.request.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class OnlineAuthorizationRequest {
    @XmlElement(name = "Header")
    private Header header;

    @XmlElement(name = "OrderID")
    private String orderId;

    @XmlElement(name = "PAN")
    private String pan;

    @XmlElement(name = "CVV2")
    private String cvv2;

    @XmlElement(name = "ExpDate")
    private String expDate;

    @XmlElement(name = "Amount")
    private String amount;

    @XmlElement(name = "Currency")
    private String currency;

    @XmlElement(name = "Exponent")
    private String exponent;

    @XmlElement(name = "AccountingMode")
    private String accountingMode;

    @XmlElement(name = "Network")
    private String network;

    @XmlElement(name = "EmailCH")
    private String emailCH;

    @XmlElement(name = "UserId")
    private String userId;

    @XmlElement(name = "Acquirer")
    private String acquirer;

    @XmlElement(name = "IpAddress")
    private String ipAddress;

    @XmlElement(name = "UsrAuthFlag")
    private String usrAuthFlag;

    @XmlElement(name = "OpDescr")
    private String opDescr;

    @XmlElement(name = "Options")
    private String options;

    @XmlElement(name = "Antifraud")
    private String antifraud;

    @XmlElement(name = "ProductRef")
    private String productRef;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Surname")
    private String surname;

    @XmlElement(name = "TaxId")
    private String taxId;

    @XmlElement(name = "CreatePanAlias")
    private String createPanAlias;

    @XmlElement(name = "AntiFraud")
    private String antiFraud;

    @XmlElement(name = "TRecurr")
    private String tRecurr;

    @XmlElement(name = "CRecurr")
    private String cRecurr;

    @XmlElement(name = "InstallmentsNumber")
    private String installmentsNumber;

    public OnlineAuthorizationRequest(){}

    public OnlineAuthorizationRequest(Date date){
        this.header = new Header(date);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }

    public String getAccountingMode() {
        return accountingMode;
    }

    public void setAccountingMode(String accountingMode) {
        this.accountingMode = accountingMode;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getEmailCH() {
        return emailCH;
    }

    public void setEmailCH(String emailCH) {
        this.emailCH = emailCH;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUsrAuthFlag() {
        return usrAuthFlag;
    }

    public void setUsrAuthFlag(String usrAuthFlag) {
        this.usrAuthFlag = usrAuthFlag;
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

    public String getAntifraud() {
        return antifraud;
    }

    public void setAntifraud(String antifraud) {
        this.antifraud = antifraud;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCreatePanAlias() {
        return createPanAlias;
    }

    public void setCreatePanAlias(String createPanAlias) {
        this.createPanAlias = createPanAlias;
    }

    public String getAntiFraud() {
        return antiFraud;
    }

    public void setAntiFraud(String antiFraud) {
        this.antiFraud = antiFraud;
    }

    public String gettRecurr() {
        return tRecurr;
    }

    public void settRecurr(String tRecurr) {
        this.tRecurr = tRecurr;
    }

    public String getcRecurr() {
        return cRecurr;
    }

    public void setcRecurr(String cRecurr) {
        this.cRecurr = cRecurr;
    }

    public String getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(String installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }
}
