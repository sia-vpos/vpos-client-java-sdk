package eu.sia.vpos.client.request;

import java.lang.reflect.Field;

public class Data3DSJson {
    private String browserAcceptHeader;
    private String browserIP;
    private String browserJavaEnabled;
    private String browserLanguage;
    private String browserColorDepth;
    private String browserScreenHeight;
    private String browserScreenWidth;
    private String browserTZ;
    private String browserUserAgent;

    private String threeDSRequestorChallengeInd;
    private String addrMatch;
    private String chAccAgeInd;
    private String chAccChange;
    private String chAccChangeInd;
    private String chAccDate;
    private String chAccPwChange;
    private String chAccPwChangeInd;
    private String nbPurchaseAccount;
    private String txnActivityDay;
    private String txnActivityYear;
    private String shipAddressUsage;
    private String shipAddressUsageInd;
    private String shipNameIndicator;
    private String acctID;
    private String billAddrCity;
    private String billAddrCountry;
    private String billAddrLine1;
    private String billAddrLine2;
    private String billAddrLine3;
    private String billAddrPostCode;
    private String billAddrState;
    private String homePhone;
    private String mobilePhone;
    private String shipAddrCity;
    private String shipAddrCountry;
    private String shipAddrLine1;
    private String shipAddrLine2;
    private String shipAddrLine3;
    private String shipAddrPostCode;
    private String shipAddrState;
    private String workPhone;
    private String deliveryEmailAddress;
    private String deliveryTimeframe;
    private String preOrderDate;
    private String preOrderPurchaseInd;


    public String getBrowserAcceptHeader() {
        return browserAcceptHeader;
    }

    public void setBrowserAcceptHeader(String browserAcceptHeader) {
        this.browserAcceptHeader = browserAcceptHeader;
    }


    public String getBrowserJavaEnabled() {
        return browserJavaEnabled;
    }

    public void setBrowserJavaEnabled(String browserJavaEnabled) {
        this.browserJavaEnabled = browserJavaEnabled;
    }

    public String getBrowserLanguage() {
        return browserLanguage;
    }

    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage;
    }

    public String getBrowserColorDepth() {
        return browserColorDepth;
    }

    public void setBrowserColorDepth(String browserColorDepth) {
        this.browserColorDepth = browserColorDepth;
    }

    public String getBrowserScreenHeight() {
        return browserScreenHeight;
    }

    public void setBrowserScreenHeight(String browserScreenHeight) {
        this.browserScreenHeight = browserScreenHeight;
    }

    public String getBrowserScreenWidth() {
        return browserScreenWidth;
    }

    public void setBrowserScreenWidth(String browserScreenWidth) {
        this.browserScreenWidth = browserScreenWidth;
    }

    public String getBrowserIP() {
        return browserIP;
    }

    public void setBrowserIP(String browserIP) {
        this.browserIP = browserIP;
    }

    public String getBrowserTZ() {
        return browserTZ;
    }

    public void setBrowserTZ(String browserTZ) {
        this.browserTZ = browserTZ;
    }

    public String getBrowserUserAgent() {
        return browserUserAgent;
    }

    public void setBrowserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
    }

    public String getThreeDSRequestorChallengeInd() {
        return threeDSRequestorChallengeInd;
    }

    public void setThreeDSRequestorChallengeInd(String threeDSRequestorChallengeInd) {
        this.threeDSRequestorChallengeInd = threeDSRequestorChallengeInd;
    }

    public String getAddrMatch() {
        return addrMatch;
    }

    public void setAddrMatch(String addrMatch) {
        this.addrMatch = addrMatch;
    }

    public String getChAccAgeInd() {
        return chAccAgeInd;
    }

    public void setChAccAgeInd(String chAccAgeInd) {
        this.chAccAgeInd = chAccAgeInd;
    }

    public String getChAccChange() {
        return chAccChange;
    }

    public void setChAccChange(String chAccChange) {
        this.chAccChange = chAccChange;
    }

    public String getChAccChangeInd() {
        return chAccChangeInd;
    }

    public void setChAccChangeInd(String chAccChangeInd) {
        this.chAccChangeInd = chAccChangeInd;
    }

    public String getChAccDate() {
        return chAccDate;
    }

    public void setChAccDate(String chAccDate) {
        this.chAccDate = chAccDate;
    }

    public String getChAccPwChange() {
        return chAccPwChange;
    }

    public void setChAccPwChange(String chAccPwChange) {
        this.chAccPwChange = chAccPwChange;
    }

    public String getChAccPwChangeInd() {
        return chAccPwChangeInd;
    }

    public void setChAccPwChangeInd(String chAccPwChangeInd) {
        this.chAccPwChangeInd = chAccPwChangeInd;
    }

    public String getNbPurchaseAccount() {
        return nbPurchaseAccount;
    }

    public void setNbPurchaseAccount(String nbPurchaseAccount) {
        this.nbPurchaseAccount = nbPurchaseAccount;
    }

    public String getTxnActivityDay() {
        return txnActivityDay;
    }

    public void setTxnActivityDay(String txnActivityDay) {
        this.txnActivityDay = txnActivityDay;
    }

    public String getTxnActivityYear() {
        return txnActivityYear;
    }

    public void setTxnActivityYear(String txnActivityYear) {
        this.txnActivityYear = txnActivityYear;
    }

    public String getShipAddressUsage() {
        return shipAddressUsage;
    }

    public void setShipAddressUsage(String shipAddressUsage) {
        this.shipAddressUsage = shipAddressUsage;
    }

    public String getShipAddressUsageInd() {
        return shipAddressUsageInd;
    }

    public void setShipAddressUsageInd(String shipAddressUsageInd) {
        this.shipAddressUsageInd = shipAddressUsageInd;
    }

    public String getShipNameIndicator() {
        return shipNameIndicator;
    }

    public void setShipNameIndicator(String shipNameIndicator) {
        this.shipNameIndicator = shipNameIndicator;
    }

    public String getAcctID() {
        return acctID;
    }

    public void setAcctID(String acctID) {
        this.acctID = acctID;
    }

    public String getBillAddrCity() {
        return billAddrCity;
    }

    public void setBillAddrCity(String billAddrCity) {
        this.billAddrCity = billAddrCity;
    }

    public String getBillAddrCountry() {
        return billAddrCountry;
    }

    public void setBillAddrCountry(String billAddrCountry) {
        this.billAddrCountry = billAddrCountry;
    }

    public String getBillAddrLine1() {
        return billAddrLine1;
    }

    public void setBillAddrLine1(String billAddrLine1) {
        this.billAddrLine1 = billAddrLine1;
    }

    public String getBillAddrLine2() {
        return billAddrLine2;
    }

    public void setBillAddrLine2(String billAddrLine2) {
        this.billAddrLine2 = billAddrLine2;
    }

    public String getBillAddrLine3() {
        return billAddrLine3;
    }

    public void setBillAddrLine3(String billAddrLine3) {
        this.billAddrLine3 = billAddrLine3;
    }

    public String getBillAddrPostCode() {
        return billAddrPostCode;
    }

    public void setBillAddrPostCode(String billAddrPostCode) {
        this.billAddrPostCode = billAddrPostCode;
    }

    public String getBillAddrState() {
        return billAddrState;
    }

    public void setBillAddrState(String billAddrState) {
        this.billAddrState = billAddrState;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getShipAddrCity() {
        return shipAddrCity;
    }

    public void setShipAddrCity(String shipAddrCity) {
        this.shipAddrCity = shipAddrCity;
    }

    public String getShipAddrCountry() {
        return shipAddrCountry;
    }

    public void setShipAddrCountry(String shipAddrCountry) {
        this.shipAddrCountry = shipAddrCountry;
    }

    public String getShipAddrLine1() {
        return shipAddrLine1;
    }

    public void setShipAddrLine1(String shipAddrLine1) {
        this.shipAddrLine1 = shipAddrLine1;
    }

    public String getShipAddrLine2() {
        return shipAddrLine2;
    }

    public void setShipAddrLine2(String shipAddrLine2) {
        this.shipAddrLine2 = shipAddrLine2;
    }

    public String getShipAddrLine3() {
        return shipAddrLine3;
    }

    public void setShipAddrLine3(String shipAddrLine3) {
        this.shipAddrLine3 = shipAddrLine3;
    }

    public String getShipAddrPostCode() {
        return shipAddrPostCode;
    }

    public void setShipAddrPostCode(String shipAddrPostCode) {
        this.shipAddrPostCode = shipAddrPostCode;
    }

    public String getShipAddrState() {
        return shipAddrState;
    }

    public void setShipAddrState(String shipAddrState) {
        this.shipAddrState = shipAddrState;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getDeliveryEmailAddress() {
        return deliveryEmailAddress;
    }

    public void setDeliveryEmailAddress(String deliveryEmailAddress) {
        this.deliveryEmailAddress = deliveryEmailAddress;
    }

    public String getDeliveryTimeframe() {
        return deliveryTimeframe;
    }

    public void setDeliveryTimeframe(String deliveryTimeframe) {
        this.deliveryTimeframe = deliveryTimeframe;
    }

    public String getPreOrderDate() {
        return preOrderDate;
    }

    public void setPreOrderDate(String preOrderDate) {
        this.preOrderDate = preOrderDate;
    }

    public String getPreOrderPurchaseInd() {
        return preOrderPurchaseInd;
    }

    public void setPreOrderPurchaseInd(String preOrderPurchaseInd) {
        this.preOrderPurchaseInd = preOrderPurchaseInd;
    }

    /**
     * @return the JSON format of the object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.get(this) != null) {
                    sb.append("\"");
                    sb.append(field.getName());
                    sb.append("\":\"");
                    sb.append(field.get(this));
                    sb.append("\",");
                }
            }
        } catch (IllegalAccessException e) {
            return e.getMessage();
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

}
