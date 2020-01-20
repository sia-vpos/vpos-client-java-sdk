package it.reply.cof.dto.request;

public class Auth3DSDto extends RequestDto {

    private boolean isMasterpass;
    private String orderId;
    private String pan;
    private String cvv2;
    private String expDate;
    private String amount;
    private String currency;
    private String exponent;
    private String accountingMode;
    private String network;
    private String emailCh;
    private String userId;
    private String acquirer;
    private String ipAddress;
    private String usrAuthFlag;
    private String opDescr;
    private String antifraud;
    private String productRef;
    private String name;
    private String surname;
    private String taxId;
    private String createPanAlias;
    private String inPerson;
    private String merchantUrl;
    private String service;
    private String xId;
    private String cavv;
    private String eci;
    private String ppAuthenticateMethod;
    private String cardEnrollMethod;
    private String paresStatus;
    private String scenRollStatus;
    private String signatureVerification;

    public boolean isMasterpass() {
        return isMasterpass;
    }

    public void setMasterpass(boolean masterpass) {
        isMasterpass = masterpass;
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

    public String getEmailCh() {
        return emailCh;
    }

    public void setEmailCh(String emailCh) {
        this.emailCh = emailCh;
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

    public String getInPerson() {
        return inPerson;
    }

    public void setInPerson(String inPerson) {
        this.inPerson = inPerson;
    }

    public String getMerchantUrl() {
        return merchantUrl;
    }

    public void setMerchantUrl(String merchantUrl) {
        this.merchantUrl = merchantUrl;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getxId() {
        return xId;
    }

    public void setxId(String xId) {
        this.xId = xId;
    }

    public String getCavv() {
        return cavv;
    }

    public void setCavv(String cavv) {
        this.cavv = cavv;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getPpAuthenticateMethod() {
        return ppAuthenticateMethod;
    }

    public void setPpAuthenticateMethod(String ppAuthenticateMethod) {
        this.ppAuthenticateMethod = ppAuthenticateMethod;
    }

    public String getCardEnrollMethod() {
        return cardEnrollMethod;
    }

    public void setCardEnrollMethod(String cardEnrollMethod) {
        this.cardEnrollMethod = cardEnrollMethod;
    }

    public String getParesStatus() {
        return paresStatus;
    }

    public void setParesStatus(String paresStatus) {
        this.paresStatus = paresStatus;
    }

    public String getScenRollStatus() {
        return scenRollStatus;
    }

    public void setScenRollStatus(String scenRollStatus) {
        this.scenRollStatus = scenRollStatus;
    }

    public String getSignatureVerification() {
        return signatureVerification;
    }

    public void setSignatureVerification(String signatureVerification) {
        this.signatureVerification = signatureVerification;
    }
}
