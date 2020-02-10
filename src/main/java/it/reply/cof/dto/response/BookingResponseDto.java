package it.reply.cof.dto.response;

import it.reply.cof.apos.response.Authorization;

public class BookingResponseDto {

    private String transactionID;
    private String timestampReq;
    private String timestampElab;
    private String srcType;
    private String amount;
    private String result;
    private String status;
    private String opDesc;
    private Authorization authorization;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTimestampReq() {
        return timestampReq;
    }

    public void setTimestampReq(String timestampReq) {
        this.timestampReq = timestampReq;
    }

    public String getTimestampElab() {
        return timestampElab;
    }

    public void setTimestampElab(String timestampElab) {
        this.timestampElab = timestampElab;
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

}
