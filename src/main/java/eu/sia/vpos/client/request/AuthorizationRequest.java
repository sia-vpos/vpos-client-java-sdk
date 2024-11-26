package eu.sia.vpos.client.request;

public class AuthorizationRequest extends RequestDto {

    private String orderId;
    private String pan;
    private String cvv2;
    private String createPanAlias;
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
    private String antiFraud;
    private String productRef;
    private String name;
    private String surname;
    private String taxId;
    private String tRecurr;
    private String cRecurr;
    private String installmentsNumber;
    private String aftData;
    private String dsTransId;

    public String getDsTransId() {
        return dsTransId;
    }

    public void setDsTransId(String dsTransId) {
        this.dsTransId = dsTransId;
    }

    public String getAftData() {
        return aftData;
    }

    public void setAftData(String aftData) {
        this.aftData = aftData;
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

    public String getCreatePanAlias() {
        return createPanAlias;
    }

    public void setCreatePanAlias(String createPanAlias) {
        this.createPanAlias = createPanAlias;
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

    public String getAntiFraud() {
        return antiFraud;
    }

    public void setAntiFraud(String antiFraud) {
        this.antiFraud = antiFraud;
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
