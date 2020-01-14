package it.reply.cof.dto.request;

public class VerifyRequestDto extends RequestDto {

    private String originalReqRefNum;

    public String getOriginalReqRefNum() {
        return originalReqRefNum;
    }

    public void setOriginalReqRefNum(String originalReqRefNum) {
        this.originalReqRefNum = originalReqRefNum;
    }
}
