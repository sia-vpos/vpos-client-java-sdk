package it.reply.cof.apos.request;

import it.reply.cof.utils.Utils;
import it.reply.cof.utils.constants.Constants;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Header {

    @XmlElement(name = "ShopID")
    private String shopId;

    @XmlElement(name = "OperatorID")
    private String operatorId;

    @XmlElement(name = "ReqRefNum")
    private String reqRefNum;

    public Header(Date reqDate) {
        SimpleDateFormat format = new SimpleDateFormat(Constants.REFNUM_PATTERN);
        this.reqRefNum = format.format(reqDate) + Utils.generateRandomDigits(24);
    }

    public Header(){
    }

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

    public String toString(){
        return "Header: ShopID: " + this.getShopId() + " OperatorID: " + " ReqRefNum: " + this.getReqRefNum() + " ";
    }
}
