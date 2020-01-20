package it.reply.cof.dto.request;

public class Auth3DSStep2RequestDto extends RequestDto {

    private String originalRefReqNum;
    private String paRes;
    private String Acquirer;

    public String getOriginalRefReqNum() {
        return originalRefReqNum;
    }

    public void setOriginalRefReqNum(String originalRefReqNum) {
        this.originalRefReqNum = originalRefReqNum;
    }

    public String getPaRes() {
        return paRes;
    }

    public void setPaRes(String paRes) {
        this.paRes = paRes;
    }

    public String getAcquirer() {
        return Acquirer;
    }

    public void setAcquirer(String acquirer) {
        Acquirer = acquirer;
    }
}
