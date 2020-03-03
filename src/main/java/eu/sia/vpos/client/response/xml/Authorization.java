package eu.sia.vpos.client.response.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Authorization {

	@XmlElement(name = "PaymentType")
	private String paymentType;
	@XmlElement(name = "AuthorizationType")
	private String authorizationType;
	@XmlElement(name = "TransactionID")
	private String transactionId;
	@XmlElement(name = "Network")
	private String network;
	@XmlElement(name = "OrderID")
	private String orderId;
	@XmlElement(name = "TransactionAmount")
	private String transactionAmount;
	@XmlElement(name = "AuthorizedAmount")
	private String authorizedAmount;
	@XmlElement(name = "Currency")
	private String currency;
	@XmlElement(name="Exponent")
	private String exponent;
	@XmlElement(name = "AccountedAmount")
	private String accountedAmount;
	@XmlElement(name = "RefundedAmount")
	private String refundedAmount;
	@XmlElement(name = "TransactionResult")
	private String transactionResult;
	@XmlElement(name = "Timestamp")
	private String timestamp;
	@XmlElement(name = "AuthorizationNumber")
	private String authorizationNumber;
	@XmlElement(name = "AcquirerBIN")
	private String acquirerBin;
	@XmlElement(name = "MerchantID")
	private String merchantId;
	@XmlElement(name = "TransactionStatus")
	private String transactionStatus;
	@XmlElement(name="ResponseCodeISO")
	private String responseCodeIso;
	@XmlElement(name = "PanTail")
	private String panTail;
	@XmlElement(name = "PanExpiryDate")
	private String panExpiryDate;
	@XmlElement(name = "PaymentTypePP")
	private String paymentTypePP;
	@XmlElement(name = "RRN")
	private String RRN;
	@XmlElement(name = "CardType")
	private String cardType;
	@XmlElement(name = "MAC")
	private String mac;

	public String getPaymentType() {

		return paymentType;
	}

	public void setPaymentType(String paymentType) {

		this.paymentType = paymentType;
	}

	public String getAuthorizationType() {

		return authorizationType;
	}

	public void setAuthorizationType(String authorizationType) {

		this.authorizationType = authorizationType;
	}

	public String getTransactionId() {

		return transactionId;
	}

	public void setTransactionId(String transactionId) {

		this.transactionId = transactionId;
	}

	public String getNetwork() {

		return network;
	}

	public void setNetwork(String network) {

		this.network = network;
	}

	public String getOrderId() {

		return orderId;
	}

	public void setOrderId(String orderId) {

		this.orderId = orderId;
	}

	public String getTransactionAmount() {

		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {

		this.transactionAmount = transactionAmount;
	}

	public String getCurrency() {

		return currency;
	}

	public void setCurrency(String currency) {

		this.currency = currency;
	}

	public String getAccountedAmount() {

		return accountedAmount;
	}

	public void setAccountedAmount(String accountedAmount) {

		this.accountedAmount = accountedAmount;
	}

	public String getRefundedAmount() {

		return refundedAmount;
	}

	public void setRefundedAmount(String refundedAmount) {

		this.refundedAmount = refundedAmount;
	}

	public String getTransactionResult() {

		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {

		this.transactionResult = transactionResult;
	}

	public String getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(String timestamp) {

		this.timestamp = timestamp;
	}

	public String getAuthorizationNumber() {

		return authorizationNumber;
	}

	public void setAuthorizationNumber(String authorizationNumber) {

		this.authorizationNumber = authorizationNumber;
	}

	public String getAcquirerBin() {

		return acquirerBin;
	}

	public void setAcquirerBin(String acquirerBin) {

		this.acquirerBin = acquirerBin;
	}

	public String getMerchantId() {

		return merchantId;
	}

	public void setMerchantId(String merchantId) {

		this.merchantId = merchantId;
	}

	public String getTransactionStatus() {

		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {

		this.transactionStatus = transactionStatus;
	}

	public String getPanTail() {
		return panTail;
	}

	public void setPanTail(String panTail) {
		this.panTail = panTail;
	}

	public String getPanExpiryDate() {
		return panExpiryDate;
	}

	public void setPanExpiryDate(String panExpiryDate) {
		this.panExpiryDate = panExpiryDate;
	}

	public String getPaymentTypePP() {

		return paymentTypePP;
	}

	public void setPaymentTypePP(String paymentTypePP) {

		this.paymentTypePP = paymentTypePP;
	}

	public String getRRN() {

		return RRN;
	}

	public void setRRN(String RRN) {

		this.RRN = RRN;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getMac() {

		return mac;
	}

	public void setMac(String mac) {

		this.mac = mac;
	}

	public String getAuthorizedAmount() {
		return authorizedAmount;
	}

	public void setAuthorizedAmount(String authorizedAmount) {
		this.authorizedAmount = authorizedAmount;
	}

	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
	}

	public String getResponseCodeIso() {
		return responseCodeIso;
	}

	public void setResponseCodeIso(String responseCodeIso) {
		this.responseCodeIso = responseCodeIso;
	}

}
