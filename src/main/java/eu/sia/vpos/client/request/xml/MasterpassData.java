package eu.sia.vpos.client.request.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MasterpassData {

    @XmlElement(name = "PP_AuthenticationMethod")
    private String ppAuthenticationMethod;

    @XmlElement(name = "PP_CardEnrollMethod")
    private String ppCardEnrollMethod;

    public MasterpassData() {
    }

    public String getPpAuthenticationMethod() {
        return ppAuthenticationMethod;
    }

    public void setPpAuthenticationMethod(String ppAuthenticationMethod) {
        this.ppAuthenticationMethod = ppAuthenticationMethod;
    }

    public String getPpCardEnrollMethod() {
        return ppCardEnrollMethod;
    }

    public void setPpCardEnrollMethod(String ppCardEnrollMethod) {
        this.ppCardEnrollMethod = ppCardEnrollMethod;
    }
}
