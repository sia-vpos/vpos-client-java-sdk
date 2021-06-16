package eu.sia.vpos.client.request.xml;

import eu.sia.vpos.client.response.xml.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElement(name = "ThreeDSAuthorizationRequest0")
    private Auth3DS2AuthorizationStep0Request auth3DS2AuthorizationStep0Request;

    @XmlElement(name = "ThreeDSAuthorizationRequest1")
    private Auth3DS2AuthorizationStep1Request auth3DS2AuthorizationStep1Request;

    @XmlElement(name = "ThreeDSAuthorizationRequest2")
    private Auth3DS2AuthorizationStep2Request auth3DS2AuthorizationStep2Request;

    @XmlElement(name = "AuthorizationRequest")
    private OnlineAuthorizationRequest onlineAuthorizationRequest;

    @XmlElement(name = "Authorization")
    private List<Authorization> authorization;

    @XmlElement(name = "Operation")
    private Operation operation;

    @XmlElement(name = "PanAliasData")
    private PanAliasData panAliasData;

    @XmlElement(name = "OrderStatus")
    private StatusRequest orderStatusRequest;

    @XmlElement(name = "Refund")
    private RefundRequest refundRequest;

    @XmlElement(name = "Accounting")
    private GeneralRequest accounting;

    @XmlElement(name = "VBVRedirect")
    private VBVRedirect vbvRedirect;

    @XmlElement(name = "ThreeDSMethod")
    private ThreeDSMethod threeDSMethod;

    @XmlElement(name = "ThreeDSChallenge")
    private ThreeDSChallenge threeDSchallenge;

    @XmlElement(name = "CardHolderData")
    private CardHolderData cardHolderData;


    public Data() {
        /*
    Empty arg constructor
     */
    }

    public Auth3DS2AuthorizationStep0Request getAuth3DS2AuthorizationStep0Request() {
        return auth3DS2AuthorizationStep0Request;
    }

    public void setAuth3DS2AuthorizationStep0Request(Auth3DS2AuthorizationStep0Request auth3DS2AuthorizationStep0Request) {
        this.auth3DS2AuthorizationStep0Request = auth3DS2AuthorizationStep0Request;
    }

    public Auth3DS2AuthorizationStep1Request getAuth3DS2AuthorizationStep1Request() {
        return auth3DS2AuthorizationStep1Request;
    }

    public void setAuth3DS2AuthorizationStep1Request(Auth3DS2AuthorizationStep1Request auth3DS2AuthorizationStep1Request) {
        this.auth3DS2AuthorizationStep1Request = auth3DS2AuthorizationStep1Request;
    }

    public Auth3DS2AuthorizationStep2Request getAuth3DS2AuthorizationStep2Request() {
        return auth3DS2AuthorizationStep2Request;
    }

    public void setAuth3DS2AuthorizationStep2Request(Auth3DS2AuthorizationStep2Request auth3DS2AuthorizationStep2Request) {
        this.auth3DS2AuthorizationStep2Request = auth3DS2AuthorizationStep2Request;
    }

    public OnlineAuthorizationRequest getOnlineAuthorizationRequest() {
        return onlineAuthorizationRequest;
    }

    public void setOnlineAuthorizationRequest(OnlineAuthorizationRequest onlineAuthorizationRequest) {
        this.onlineAuthorizationRequest = onlineAuthorizationRequest;
    }

    public List<Authorization> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(List<Authorization> authorization) {
        this.authorization = authorization;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public PanAliasData getPanAliasData() {
        return panAliasData;
    }

    public void setPanAliasData(PanAliasData panAliasData) {
        this.panAliasData = panAliasData;
    }

    public StatusRequest getOrderStatusRequest() {
        return orderStatusRequest;
    }

    public void setOrderStatusRequest(StatusRequest orderStatusRequest) {
        this.orderStatusRequest = orderStatusRequest;
    }

    public RefundRequest getRefundRequest() {
        return refundRequest;
    }

    public void setRefundRequest(RefundRequest refundRequest) {
        this.refundRequest = refundRequest;
    }

    public GeneralRequest getAccounting() {
        return accounting;
    }

    public void setAccounting(GeneralRequest accounting) {
        this.accounting = accounting;
    }

    public VBVRedirect getVbvRedirect() {
        return vbvRedirect;
    }

    public void setVbvRedirect(VBVRedirect vbvRedirect) {
        this.vbvRedirect = vbvRedirect;
    }

    public ThreeDSMethod getThreeDSMethod() {
        return threeDSMethod;
    }

    public void setThreeDSMethod(ThreeDSMethod threeDSMethod) {
        this.threeDSMethod = threeDSMethod;
    }

    public ThreeDSChallenge getThreeDSchallenge() {
        return threeDSchallenge;
    }

    public void setThreeDSchallenge(ThreeDSChallenge threeDSchallenge) {
        this.threeDSchallenge = threeDSchallenge;
    }

    public CardHolderData getCardHolderData() {
        return cardHolderData;
    }

    public void setCardHolderData(CardHolderData cardHolderData) {
        this.cardHolderData = cardHolderData;
    }
}
