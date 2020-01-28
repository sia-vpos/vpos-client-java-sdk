package it.reply.cof.dto;

import it.reply.cof.dto.request.Data3DSJson;

import java.util.HashMap;
import java.util.Map;

/**
 * Data transfer object passed to VPOS client when starting a payment
 *
 * @author gab.marini
 */
public class PaymentInfo {

    private String amount;
    private String currency;
    private String exponent;
    private String orderId;
    private String shopId;
    private String urlBack;
    private String urlDone;
    private String urlMs;
    private String accountingMode;
    private String authorMode;
    private Data3DSJson data3DSJson;
    private Map<String, String> notCompulsoryFields;

    public PaymentInfo() {
        notCompulsoryFields = new HashMap<>();
    }

    public void addOption(String option) {
        String field = notCompulsoryFields.get("OPTIONS");
        if (field == null) {
            field = "";
            field = field.concat(option);
        }
        notCompulsoryFields.put("OPTIONS", field);
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public Map<String, String> getNotCompulsoryFields() {
        return notCompulsoryFields;
    }

    public void setNotCompulsoryFields(Map<String, String> notCompulsoryFields) {
        this.notCompulsoryFields = notCompulsoryFields;
    }

    public Data3DSJson getData3DSJson() {
        return data3DSJson;
    }

    public void setData3DSJson(Data3DSJson data3DSJson) {
        this.data3DSJson = data3DSJson;
    }
}
