package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class RefundRequest {

	@XmlElement(name = "Header")
	private Header header;
	@XmlElement(name = "TransactionID")
	private String transactionId;
	@XmlElement(name = "OrderID")
	private String orderId;
	@XmlElement(name = "Amount")
	private String amount;
	@XmlElement(name = "Currency")
	private String currency;
	@XmlElement(name = "Exponent")
	private String exponent;
	@XmlElement(name = "OpDescr")
	private String opDescr;
	@XmlElement(name = "Options")
	private String options;

	public RefundRequest(Date reqDate) {
		this.header=new Header(reqDate);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
	}

	public String getOpDescr() {
		return opDescr;
	}

	public void setOpDescr(String opDescr) {
		this.opDescr = opDescr;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
}
