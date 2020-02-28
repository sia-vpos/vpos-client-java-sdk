package eu.sia.vpos.client.request;

public class ThreeDSAuthorization2Request extends RequestDto {

    private String threeDSTransId;

    public String getThreeDSTransId() {
        return threeDSTransId;
    }

    public void setThreeDSTransId(String threeDSTransId) {
        this.threeDSTransId = threeDSTransId;
    }
}
