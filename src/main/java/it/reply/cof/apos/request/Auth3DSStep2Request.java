package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Auth3DSStep2Request {

	@XmlElement(name = "Header")
	private Header header;

	@XmlElement(name = "OriginalReqRefNum")
	private String originalReqRefNum;

	@XmlElement(name = "PaRes")
	private String paRes;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getOriginalReqRefNum() {
		return originalReqRefNum;
	}

	public void setOriginalReqRefNum(String originalReqRefNum) {
		this.originalReqRefNum = originalReqRefNum;
	}

	public String getPaRes() {
		return paRes;
	}

	public void setPaRes(String paRes) {
		this.paRes = paRes;
	}

}
