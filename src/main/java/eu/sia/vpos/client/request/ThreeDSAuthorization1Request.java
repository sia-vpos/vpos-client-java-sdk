package eu.sia.vpos.client.request;

public class ThreeDSAuthorization1Request extends RequestDto{
    private String operatorId;
    private String threeDSTransId;
    private String threeDSMtdComplInd;

    @Override
    public String getOperatorId() {
        return operatorId;
    }

    @Override
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getThreeDSTransId() {
        return threeDSTransId;
    }

    public void setThreeDSTransId(String threeDSTransId) {
        this.threeDSTransId = threeDSTransId;
    }

    public String getThreeDSMtdComplInd() {
        return threeDSMtdComplInd;
    }

    public void setThreeDSMtdComplInd(String threeDSMtdComplInd) {
        this.threeDSMtdComplInd = threeDSMtdComplInd;
    }
}
