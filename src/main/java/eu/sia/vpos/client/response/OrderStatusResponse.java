package eu.sia.vpos.client.response;


import java.util.ArrayList;
import java.util.List;

public class OrderStatusResponse {
    private String result;
    private String timestamp;
    private String productRef;
    private List<AuthorizationResponse> authorizations = new ArrayList<>();
    private String panAlias;
    private String panAliasRev;
    private String panAliasExpDate;
    private String panAliasTail;


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

}
