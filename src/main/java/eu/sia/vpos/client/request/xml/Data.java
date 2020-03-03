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
    private Authorization3DSRequest authorizationRequest;

    @XmlElement(name = "Authorization")
    private List<Authorization> authorization;

    @XmlElement(name = "Operation")
    private Operation operation;

    @XmlElement(name = "PanAliasData")
    private PanAliasData panAliasData;

    @XmlElement(name = "OrderStatus")
    private StatusRequest orderStatusRequest;

    @XmlElement(name = "VerifyRequest")
    private StatusRequest verifyRequest;

    @XmlElement(name = "Refund")
    private RefundRequest refundRequest;

    @XmlElement(name = "Verify")
    private Operation verifyResponse;

    @XmlElement(name = "Accounting")
    private GeneralRequest accounting;

    @XmlElement(name = "DeferredRequest")
    private ConfirmRequest confirmRequest;

    @XmlElement(name = "Authorization3DS")
    private Auth3DSStep2Request auth3DSStep2Request;

    @XmlElement(name = "VBVRedirect")
    private VBVRedirect vbvRedirect;

    @XmlElement(name = "ThreeDSMethod")
    private ThreeDSMethod threeDSMethod;

    @XmlElement(name = "ThreeDSChallenge")
    private ThreeDSChallenge threeDSchallenge;


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

    public Authorization3DSRequest getAuthorizationRequest() {
        return authorizationRequest;
    }

    public void setAuthorizationRequest(Authorization3DSRequest authorizationRequest) {
        this.authorizationRequest = authorizationRequest;
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

    public ConfirmRequest getConfirmRequest() {
        return confirmRequest;
    }

    public void setConfirmRequest(ConfirmRequest confirmRequest) {
        this.confirmRequest = confirmRequest;
    }

    public StatusRequest getOrderStatusRequest() {
        return orderStatusRequest;
    }

    public void setOrderStatusRequest(StatusRequest orderStatus) {
        this.orderStatusRequest = orderStatus;
    }

    public StatusRequest getVerifyRequest() {
        return verifyRequest;
    }

    public void setVerifyRequest(StatusRequest verifyRequest) {
        this.verifyRequest = verifyRequest;
    }

    public RefundRequest getRefundRequest() {
        return refundRequest;
    }

    public void setRefundRequest(RefundRequest refundRequest) {
        this.refundRequest = refundRequest;
    }

    public Auth3DSStep2Request getAuth3DSStep2Request() {
        return auth3DSStep2Request;
    }

    public void setAuth3DSStep2Request(Auth3DSStep2Request auth3dsStep2Request) {
        auth3DSStep2Request = auth3dsStep2Request;
    }

    public VBVRedirect getVbvRedirect() {
        return vbvRedirect;
    }

    public void setVbvRedirect(VBVRedirect vbvRedirect) {
        this.vbvRedirect = vbvRedirect;
    }

    public Operation getVerifyResponse() {
        return verifyResponse;
    }

    public void setVerifyResponse(Operation verifyResponse) {
        this.verifyResponse = verifyResponse;
    }

    public GeneralRequest getAccounting() {
        return accounting;
    }

    public void setAccounting(GeneralRequest accounting) {
        this.accounting = accounting;
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
}
