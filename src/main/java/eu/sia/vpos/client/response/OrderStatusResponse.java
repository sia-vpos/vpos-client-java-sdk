package eu.sia.vpos.client.response;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusResponse implements Serializable {
    private String result;
    private String timestamp;
    private String productRef;
    private List<AuthorizationResponse> authorizations = new ArrayList<>();
    private String panAlias;
    private String panAliasRev;
    private String panAliasExpDate;
    private String panAliasTail;
    private String cardHolderName;
    private String cardHolderEmail;
    private String billingAddressPostalcode;
    private String billingAddressCity;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingAddressLine3;
    private String billingAddressState;
    private String billingAddressCountry;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public List<AuthorizationResponse> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<AuthorizationResponse> authorizations) {
        this.authorizations = authorizations;
    }

    public String getPanAlias() {
        return panAlias;
    }

    public void setPanAlias(String panAlias) {
        this.panAlias = panAlias;
    }

    public String getPanAliasRev() {
        return panAliasRev;
    }

    public void setPanAliasRev(String panAliasRev) {
        this.panAliasRev = panAliasRev;
    }

    public String getPanAliasExpDate() {
        return panAliasExpDate;
    }

    public void setPanAliasExpDate(String panAliasExpDate) {
        this.panAliasExpDate = panAliasExpDate;
    }

    public String getPanAliasTail() {
        return panAliasTail;
    }

    public void setPanAliasTail(String panAliasTail) {
        this.panAliasTail = panAliasTail;
    }

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

    @Override
    public String toString() {
        return "OrderStatusResponse{" +
                "result='" + result + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", productRef='" + productRef + '\'' +
                ", authorizations=" + authorizations +
                ", panAlias='" + panAlias + '\'' +
                ", panAliasRev='" + panAliasRev + '\'' +
                ", panAliasExpDate='" + panAliasExpDate + '\'' +
                ", panAliasTail='" + panAliasTail + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardHolderEmail='" + cardHolderEmail + '\'' +
                ", billingAddressPostalcode='" + billingAddressPostalcode + '\'' +
                ", billingAddressCity='" + billingAddressCity + '\'' +
                ", billingAddressLine1='" + billingAddressLine1 + '\'' +
                ", billingAddressLine2='" + billingAddressLine2 + '\'' +
                ", billingAddressLine3='" + billingAddressLine3 + '\'' +
                ", billingAddressState='" + billingAddressState + '\'' +
                ", billingAddressCountry='" + billingAddressCountry + '\'' +
                '}';
    }
}
