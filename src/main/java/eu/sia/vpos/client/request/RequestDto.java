package eu.sia.vpos.client.request;

import java.io.Serializable;

public class RequestDto implements Serializable {

    protected String operatorId;
    protected String options;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
