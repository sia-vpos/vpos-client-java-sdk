package eu.sia.vpos.client.response.xml;

public class APosResponse {

	private String result;
	private String transType;
	private String transactionId;
	private String authorizationNumber;
	private String acquirerBin;
	private String merchantId;
	private String transactionStatus;
	private String transactionResult;
	private String panAlias;
	private String panAliasRev;
	private String panAliasExpDate;
	private String panAliasTail;
	private String paReq;
	private String acsURL;
	private String orderId;
	private String refundedAmount;
	private String confirmedAmount;
	private String respTimestamp;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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

	public String getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

	public String getPanAlias() {
		return panAlias;
	}

	public void setPanAlias(String panAlias) {
		this.panAlias = panAlias;
	}

	public String getPanAliasRev() {
		return panAliasRev;
	}

	public void setPanAliasRev(String panAliasRev) {
		this.panAliasRev = panAliasRev;
	}

	public String getPanAliasExpDate() {
		return panAliasExpDate;
	}

	public void setPanAliasExpDate(String panAliasExpDate) {
		this.panAliasExpDate = panAliasExpDate;
	}

	public String getPanAliasTail() {
		return panAliasTail;
	}

	public void setPanAliasTail(String panAliasTail) {
		this.panAliasTail = panAliasTail;
	}

	public String getPaReq() {
		return paReq;
	}

	public void setPaReq(String paReq) {
		this.paReq = paReq;
	}

	public String getAcsURL() {
		return acsURL;
	}

	public void setAcsURL(String acsURL) {
		this.acsURL = acsURL;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRefundedAmount() {
		return refundedAmount;
	}

	public void setRefundedAmount(String refundedAmount) {
		this.refundedAmount = refundedAmount;
	}

	public String getConfirmedAmount() {
		return confirmedAmount;
	}

	public void setConfirmedAmount(String confirmedAmount) {
		this.confirmedAmount = confirmedAmount;
	}

	public String getRespTimestamp() {
		return respTimestamp;
	}

	public void setRespTimestamp(String respTimestamp) {
		this.respTimestamp = respTimestamp;
	}

}
