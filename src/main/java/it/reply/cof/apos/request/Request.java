package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Request {

	@XmlElement(name = "Operation")
	private String operation;
	@XmlElement(name = "Timestamp")
	private String timestamp;
	@XmlElement(name = "MAC")
	private String mac;

	public String getOperation() {

		return operation;
	}

	public void setOperation(String operation) {

		this.operation = operation;
	}

	public String getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(String timestamp) {

		this.timestamp = timestamp;
	}

	public String getMac() {

		return mac;
	}

	public void setMac(String mac) {

		this.mac = mac;
	}

}
