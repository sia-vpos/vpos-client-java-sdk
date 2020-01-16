package it.reply.cof.apos.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Operation {

	@XmlElement(name = "TransactionID")
	private String transactionId;
	@XmlElement(name = "TimestampReq")
	private String timestampReq;
	@XmlElement(name = "TimestampElab")
	private String timestampElab;
	@XmlElement(name = "SrcType")
	private String srcType;
	@XmlElement(name = "Amount")
	private String amount;
	@XmlElement(name = "Result")
	private String result;
	@XmlElement(name = "Status")
	private String status;
	@XmlElement(name = "OpDescr")
	private String opDescr;
	@XmlElement(name = "MAC")
	private String mac;
	@XmlElement(name = "Operation")
	private String operation;
	@XmlElement(name = "Authorization")
	private Authorization authorization;


	public String getTransactionId() {

		return transactionId;
	}

	public void setTransactionId(String transactionId) {

		this.transactionId = transactionId;
	}

	public String getTimestampReq() {

		return timestampReq;
	}

	public void setTimestampReq(String timestampReq) {

		this.timestampReq = timestampReq;
	}

	public String getTimestampElab() {

		return timestampElab;
	}

	public void setTimestampElab(String timestampElab) {

		this.timestampElab = timestampElab;
	}

	public String getSrcType() {

		return srcType;
	}

	public void setSrcType(String srcType) {

		this.srcType = srcType;
	}

	public String getAmount() {

		return amount;
	}

	public void setAmount(String amount) {

		this.amount = amount;
	}

	public String getResult() {

		return result;
	}

	public void setResult(String result) {

		this.result = result;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getMac() {

		return mac;
	}

	public void setMac(String mac) {

		this.mac = mac;
	}

	public Authorization getAuthorization() {

		return authorization;
	}

	public void setAuthorization(Authorization authorization) {

		this.authorization = authorization;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOpDescr() {
		return opDescr;
	}

	public void setOpDesc(String opDescr) {
		this.opDescr = opDescr;
	}

}
