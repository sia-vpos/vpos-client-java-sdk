package it.reply.cof.apos.request;

import it.reply.cof.apos.response.Authorization;
import it.reply.cof.apos.response.Operation;
import it.reply.cof.apos.response.PanAliasData;
import it.reply.cof.apos.response.VBVRedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

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

    @XmlElement(name = "DeferredRequest")
    private ConfirmRequest confirmRequest;

    @XmlElement(name = "Authorization3DS")
    private Auth3DSStep2Request auth3DSStep2Request;

    @XmlElement(name = "VBVRedirect")
    private VBVRedirect vbvRedirect;


    public Data(){
        /*
    Empty arg constructor
     */
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

}
