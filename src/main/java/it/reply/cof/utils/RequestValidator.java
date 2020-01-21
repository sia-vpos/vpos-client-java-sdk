package it.reply.cof.utils;

import it.reply.cof.apos.request.Auth3DSStep2Request;
import it.reply.cof.apos.response.Operation;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Constants;
import it.reply.cof.utils.constants.Operations;
import it.reply.cof.utils.exception.COFException;

/**
 * Utility class used to validate requests.
 *
 * @author a.simonetti
 * @author Gabriel Raul Marini
 */
public class RequestValidator {

    private RequestValidator() {

    }

    /**
     * Method used to validate the Refund request.
     *
     * @param requestDto request to validate
     * @throws COFException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateRefundRequest(RefundRequestDto requestDto) throws COFException {
        String field = "";

        if (requestDto.getShopId() == null || !requestDto.getShopId().matches(Operations.PARAMETERS.SHOPID.PATTERN)) {
            field = Operations.PARAMETERS.SHOPID.NAME;
        } else if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
            //TODO check
        } else if (requestDto.getTransactionId() == null || requestDto.getTransactionId().length() != Operations.PARAMETERS.TRANSACTIONID.LEN) {
            field = Operations.PARAMETERS.TRANSACTIONID.NAME;
        } else if (requestDto.getOrderId() == null || !requestDto.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (requestDto.getAmount() == null || !requestDto.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN)) {
            field = Operations.PARAMETERS.AMOUNT.NAME;

        } else if (requestDto.getCurrency() == null || !requestDto.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN)) {
            field = Operations.PARAMETERS.CURRENCY.NAME;
        }

        if (!field.isEmpty())
            throw new COFException("BAD REQUEST: Field " + field + " is missing or not valid.");
    }

    /**
     * Method used to validate the Confirm request.
     *
     * @param requestDto request to validate
     * @throws COFException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateConfirmRequest(ConfirmRequestDto requestDto) throws COFException {
        String field = "";

        if (requestDto.getShopId() == null || !requestDto.getShopId().matches(Operations.PARAMETERS.SHOPID.PATTERN))
            field = Operations.PARAMETERS.SHOPID.NAME;
        else if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN))
            field = Operations.PARAMETERS.OPERATORID.NAME;
        else if (requestDto.getAmount() == null || !requestDto.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN))
            field = Operations.PARAMETERS.AMOUNT.NAME;
        else if (requestDto.getCurrency() == null || !requestDto.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN))
            field = Operations.PARAMETERS.CURRENCY.NAME;
        else if (requestDto.getOrderId() == null || !requestDto.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN))
            field = Operations.PARAMETERS.ORDERID.NAME;
        else if (requestDto.getTransactionId() == null || requestDto.getTransactionId().length() != Operations.PARAMETERS.TRANSACTIONID.LEN)
            field = Operations.PARAMETERS.TRANSACTIONID.NAME;
        else if (requestDto.getExponent() == null || !requestDto.getCurrency().equals(Constants.Currency.EUR))
            field = AposConstants.EXPONENT;
        else if (requestDto.getAccountingMode() == null || !requestDto.getAccountingMode().matches(Operations.PARAMETERS.ACCOUNTINGMODE.PATTERN))
            field = Operations.PARAMETERS.ACCOUNTINGMODE.NAME;
        else if (requestDto.getCloseOrder() == null)
            field = Operations.PARAMETERS.CLOSEORDER.NAME;

        if (!field.isEmpty())
            throw new COFException("BAD REQUEST: Field " + field + " is missing or not valid.");
    }
    /**
     * Method used to validate a Verify request.
     *
     * @param requestDto request to validate
     * @throws COFException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateVerifyRequest(VerifyRequestDto requestDto) throws COFException {
        String field = "";

        if (requestDto.getShopId() == null || !requestDto.getShopId().matches(Operations.PARAMETERS.SHOPID.PATTERN)) {
            field = Operations.PARAMETERS.SHOPID.NAME;
        } else if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
            //TODO regex for refReqNum
        } else if (requestDto.getOriginalReqRefNum() == null) {
            field = Operations.PARAMETERS.REQREFNUM;
        }

        if (!field.isEmpty())
            throw new COFException("BAD REQUEST: Field " + field + " is missing or not valid.");
    }

    /**
     * Method used to validate the Order Status request.
     *
     * @param requestDto request to validate
     * @throws COFException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateOrderStatusRequest(OrderStatusRequestDto requestDto) throws COFException {
        String field = "";
        if (requestDto.getShopId() == null || !requestDto.getShopId().matches(Operations.PARAMETERS.SHOPID.PATTERN)) {
            field = Operations.PARAMETERS.SHOPID.NAME;
        } else if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (requestDto.getOrderId() == null || !requestDto.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (requestDto.getProductRef() != null && requestDto.getProductRef().matches(Operations.PARAMETERS.PRODUCTREF.PATTERN)) {
            field = Operations.PARAMETERS.PRODUCTREF.NAME;
        } else if (requestDto.getOptions() != null && requestDto.getOptions().matches(Operations.PARAMETERS.OPTIONS.PATTERN)) {
            field = Operations.PARAMETERS.OPTIONS.NAME;
        }

        if (!field.isEmpty())
            throw new COFException("BAD REQUEST: Field " + field + " is missing or not valid.");
    }

    /**
     * Method used to validate the first step of a 3D Secure authorization.
     *
     * @param requestDto request to validate
     * @throws COFException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateAuth3DSStep1Request (Auth3DSDto requestDto) throws COFException{
        String field = "";

       if(requestDto.getOrderId() == null || !requestDto.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)){
           field = Operations.PARAMETERS.ORDERID.NAME;
       } else if (requestDto.getPan() == null || !requestDto.getPan().matches(Operations.PARAMETERS.PAN.PATTERNGENERIC)){
          field = Operations.PARAMETERS.PAN.NAME ;
       } else if (requestDto.getCvv2() != null && !requestDto.getCvv2().matches(Operations.PARAMETERS.CVV2.PATTERN)){
          field = Operations.PARAMETERS.CVV2.NAME;
       } else if (requestDto.getExpDate() == null || !requestDto.getExpDate().matches(Operations.PARAMETERS.EXPDATE.PATTERN)){
          field = Operations.PARAMETERS.EXPDATE.NAME;
       } else if (requestDto.getAmount() == null || !requestDto.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN)){
          field = Operations.PARAMETERS.AMOUNT.NAME;
       } else if (requestDto.getCurrency() == null ||!requestDto.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN)){
           field = Operations.PARAMETERS.CURRENCY.NAME;
       } else if (!requestDto.getCurrency().equals("978") && (requestDto.getExponent() == null || requestDto.getExponent().matches(Operations.PARAMETERS.EXPONENT.PATTERN))){
           field = Operations.PARAMETERS.EXPONENT.NAME;
       } else if (requestDto.getAccountingMode() == null || !requestDto.getAccountingMode().matches(Operations.PARAMETERS.ACCOUNTINGMODE.PATTERN)){
          field = Operations.PARAMETERS.ACCOUNTINGMODE.NAME ;
       } else if (requestDto.getNetwork() == null || !requestDto.getNetwork().matches(Operations.PARAMETERS.NETWORK.PATTERN)){
           field = Operations.PARAMETERS.NETWORK.NAME;
       } else if (requestDto.getEmailCh() != null && !requestDto.getEmailCh().matches(Operations.PARAMETERS.EMAIL.PATTERN)){
           field = Operations.PARAMETERS.EMAIL.NAMECH;
       } else if (requestDto.getUserId() != null && !requestDto.getUserId().matches(Operations.PARAMETERS.USERID.PATTERN)){
           field = Operations.PARAMETERS.USERID.NAME;
       } else if (requestDto.getAcquirer() != null && !requestDto.getAcquirer().matches(Operations.PARAMETERS.ACQUIRER.PATTERN)){
           field = Operations.PARAMETERS.ACQUIRER.NAME;
       } else if (requestDto.getIpAddress()!= null && !requestDto.getIpAddress().matches(Operations.PARAMETERS.IPADDRESS.PATTERN)){
           field = Operations.PARAMETERS.IPADDRESS.NAME;
       } else if (requestDto.getUsrAuthFlag() != null && !requestDto.getUsrAuthFlag().matches(Operations.PARAMETERS.USRAUTHFLAG.PATTERN)){
           field = Operations.PARAMETERS.USRAUTHFLAG.NAME;
       } else if (requestDto.getOpDescr() != null && !requestDto.getOpDescr().matches(Operations.PARAMETERS.OPDESCR.PATTERN)){
           field = Operations.PARAMETERS.OPDESCR.PATTERN;
       } else if (requestDto.getAntifraud() != null && !requestDto.getAntifraud().matches(Operations.PARAMETERS.ANTIFRAUD.PATTERN)){
           field = Operations.PARAMETERS.ANTIFRAUD.NAME;
       } else if (requestDto.getProductRef() != null && !requestDto.getProductRef().matches(Operations.PARAMETERS.PRODUCTREF.PATTERN)){
           field = Operations.PARAMETERS.PRODUCTREF.NAME;
       } else if (requestDto.getName() != null && !requestDto.getName().matches(Operations.PARAMETERS.NAME.PATTERN)){
           field = Operations.PARAMETERS.NAME.NAME;
       } else if (requestDto.getSurname() != null && !requestDto.getSurname().matches(Operations.PARAMETERS.SURNAME.PATTERN)){
           field = Operations.PARAMETERS.SURNAME.NAME;
       } else if (requestDto.getTaxId() != null && !requestDto.getTaxId().matches(Operations.PARAMETERS.TAXID.PATTERN)){
           field = Operations.PARAMETERS.TAXID.NAME;
       } else if (requestDto.getCreatePanAlias() != null && !requestDto.getCreatePanAlias().matches(Operations.PARAMETERS.CREATEPANALIAS.PATTERN)){
           field = Operations.PARAMETERS.CREATEPANALIAS.NAME;
       } else if (requestDto.getInPerson() != null && !requestDto.getInPerson().matches(Operations.PARAMETERS.INPERSON.PATTERN)){
           field = Operations.PARAMETERS.INPERSON.NAME;
       } else if (requestDto.getMerchantUrl() != null && !requestDto.getMerchantUrl().matches(Operations.PARAMETERS.MERCHANTURL.PATTERN)){
           field = Operations.PARAMETERS.MERCHANTURL.NAME;
       } else if (requestDto.isMasterpass() && (requestDto.getService() != null && !requestDto.getService().matches(Operations.PARAMETERS.SERVICE.PATTERN))){
           field = Operations.PARAMETERS.SERVICE.NAME;
       } else if (requestDto.getxId() != null && !requestDto.getxId().matches(Operations.PARAMETERS.XID.PATTERN)){
           field = Operations.PARAMETERS.XID.NAME;
       } else if (requestDto.getCavv() != null && !requestDto.getCavv().matches(Operations.PARAMETERS.CAVV.PATTERN)){
           field = Operations.PARAMETERS.CAVV.NAME;
       } else if ((requestDto.getParesStatus() == "Y" || requestDto.getParesStatus() == "A")&& (requestDto.getEci() != null && !requestDto.getEci().matches(Operations.PARAMETERS.ECI.PATTERN))){
           field = Operations.PARAMETERS.ECI.NAME;
       } else if  (requestDto.getPpAuthenticateMethod() != null && !requestDto.getPpAuthenticateMethod().matches(Operations.PARAMETERS.PP_AUTHENTICATEMETHOD.PATTERN)){
           field = Operations.PARAMETERS.PP_AUTHENTICATEMETHOD.NAME;
       } else if (requestDto.getCardEnrollMethod() != null && !requestDto.getCardEnrollMethod().matches(Operations.PARAMETERS.PP_CARDENROLLMETHOD.PATTERN)){
           field = Operations.PARAMETERS.PP_CARDENROLLMETHOD.NAME;
       } else if (requestDto.getParesStatus() != null && !requestDto.getParesStatus().matches(Operations.PARAMETERS.PARESSTATUS.PATTERN)){
           field = Operations.PARAMETERS.PARESSTATUS.NAME;
       } else if (requestDto.getScenRollStatus() != null && !requestDto.getScenRollStatus().matches(Operations.PARAMETERS.SCENROLLSTATUS.PATTERN)){
           field = Operations.PARAMETERS.SCENROLLSTATUS.NAME;
       } else if (requestDto.getSignatureVerification() != null && !requestDto.getSignatureVerification().matches(Operations.PARAMETERS.SIGNATUREVERIFICATION.PATTERN)){
           field = Operations.PARAMETERS.SIGNATUREVERIFICATION.NAME;
       }

        if(!field.isEmpty())
            throw new COFException("BAD REQUEST: Field " + field + " is missing or not valid.");

    }

    /**
     * Method used to validate the second step of a 3D Secure authorization.
     *
     * @param requestDto request to validate
     * @throws COFException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateAuth3DSStep2Request(Auth3DSStep2RequestDto requestDto) throws COFException{
        String field = "";

        if (requestDto.getOriginalRefReqNum() == null || requestDto.getOriginalRefReqNum().matches(Operations.PARAMETERS.ORIGINALREQREFNUM.PATTERN))
        {
           field = Operations.PARAMETERS.ORIGINALREQREFNUM.NAME;
        } else if (requestDto.getAcquirer() != null && !requestDto.getAcquirer().matches(Operations.PARAMETERS.ACQUIRER.PATTERN)){
            field = Operations.PARAMETERS.ACQUIRER.NAME;
        } else if(requestDto.getOptions() != null && !requestDto.getOptions().matches(Operations.PARAMETERS.OPTIONS.PATTERN)) {
            field = Operations.PARAMETERS.OPTIONS.NAME;
        }

        if(!field.isEmpty())
            throw new COFException("BAD REQUEST: Field " + field + " is missing or not valid.");

    }
}
