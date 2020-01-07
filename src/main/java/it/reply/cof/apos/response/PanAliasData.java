package it.reply.cof.apos.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PanAliasData {

	@XmlElement(name = "PanAlias")
	private String panAlias;

	@XmlElement(name = "PanAliasRev")
	private String panAliasRev;

	@XmlElement(name = "PanAliasExpDate")
	private String panAliasExpDate;

	@XmlElement(name = "PanAliasTail")
	private String panAliasTail;

	@XmlElement(name = "MAC")
	private String mac;

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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
