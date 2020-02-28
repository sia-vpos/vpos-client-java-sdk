package eu.sia.vpos.client.request;

public class Auth3DSStep2RequestDto extends RequestDto {

    private String originalRefReqNum;
    private String paRes;
    private String acquirer;

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
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }
}
