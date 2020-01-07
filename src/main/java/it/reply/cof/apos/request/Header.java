package it.reply.cof.apos.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Header {

	@XmlElement(name = "ShopID")
	private String shopId;
	@XmlElement(name = "OperatorID")
	private String operatorId;
	@XmlElement(name = "ReqRefNum")
	private String reqRefNum;

	public String getShopId() {

		return shopId;
	}

	public void setShopId(String shopId) {

		this.shopId = shopId;
	}

	public String getOperatorId() {

		return operatorId;
	}

	public void setOperatorId(String operatorId) {

		this.operatorId = operatorId;
	}

	public String getReqRefNum() {

		return reqRefNum;
	}

	public void setReqRefNum(String reqRefNum) {

		this.reqRefNum = reqRefNum;
	}
}
