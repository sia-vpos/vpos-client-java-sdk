package eu.sia.vpos.client.request;

import eu.sia.vpos.client.request.Data3DSJson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Data transfer object passed to VPOS client when starting a payment
 *
 * @author gab.marini
 */
public class PaymentInfo implements Serializable {

    private String amount;
    private String currency;
    private String exponent;
    private String orderId;
    private String urlBack;
    private String urlDone;
    private String urlMs;
    private String accountingMode;
    private String authorMode;
    private String urlApos;



    private Data3DSJson data3DSJson;
    private Map<FieldName, String> notCompulsoryFields;

    public PaymentInfo() {
        notCompulsoryFields = new HashMap<>();
    }

    public void addOption(OptionName option) {
        String field = notCompulsoryFields.get(FieldName.OPTIONS);
        if (field == null) {
            field = "";
            field = field.concat(option.toString());
        }
        notCompulsoryFields.put(FieldName.OPTIONS, field);
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUrlBack() {
        return urlBack;
    }

    public void setUrlBack(String urlBack) {
        this.urlBack = urlBack;
    }

    public String getUrlDone() {
        return urlDone;
    }

    public void setUrlDone(String urlDone) {
        this.urlDone = urlDone;
    }

    public String getUrlMs() {
        return urlMs;
    }

    public void setUrlMs(String urlMs) {
        this.urlMs = urlMs;
    }

    public String getAccountingMode() {
        return accountingMode;
    }

    public void setAccountingMode(String accountingMode) {
        this.accountingMode = accountingMode;
    }

    public String getAuthorMode() {
        return authorMode;
    }

    public void setAuthorMode(String authorMode) {
        this.authorMode = authorMode;
    }

    public String getUrlApos() {
        return urlApos;
    }

    public void setUrlApos(String urlApos) {
        this.urlApos = urlApos;
    }

    public Map<FieldName, String> getNotCompulsoryFields() {
        return notCompulsoryFields;
    }

    public void setNotCompulsoryFields(Map<FieldName, String> notCompulsoryFields) {
        this.notCompulsoryFields = notCompulsoryFields;
    }

    public Data3DSJson getData3DSJson() {
        return data3DSJson;
    }

    public void setData3DSJson(Data3DSJson data3DSJson) {
        this.data3DSJson = data3DSJson;
    }

    public enum FieldName {
        LANG,
        SHOPEMAIL,
        OPTIONS,
        LOCKCARD,
        COMMIS,
        EMAIL,
        ORDDESCR,
        VSID,
        OPDESCR,
        REMAININGDURATION,
        USERID,
        BP_POSTEPAY,
        BP_CARDS,
        PHONENUMBER,
        CAUSATION,
        USER,
        NAME,
        SURNAME,
        TAXID,
        PRODUCTREF,
        ANTIFRAUD,
        TRECURR,
        CRECURR,
        INSTALLMENTSNUMBER,
        TOKEN,
        EXPDATE,
        NETWORK,
        IBAN,
        NAMECH,
        SURNAMECH
    }

    public enum OptionName {
        B, D, F, G, H, I, L, M, N, O, P, Q, R, V, W, X
    }

}
