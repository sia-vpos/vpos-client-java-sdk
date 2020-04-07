package eu.sia.vpos.client.utils;

import eu.sia.vpos.client.impl.VPosClient;
import eu.sia.vpos.client.request.*;
import eu.sia.vpos.client.utils.constants.Constants;
import eu.sia.vpos.client.utils.constants.Operations;
import eu.sia.vpos.client.utils.exception.VPosClientException;

/**
 * Utility class used to validate requests.
 *
 * @author Albino Simonetti
 * @author Gabriel Raul Marini
 */
public class RequestValidator {

    private static final String ERROR_MSG_PREMISE = "BAD REQUEST: Field ";
    private static final String ERROR_MSG_QUEUE = " is missing or not valid.";

    private RequestValidator() {

    }

    /**
     * Method used to validate the Refund request.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateRefundRequest(RefundRequest request) throws VPosClientException {
        String field = "";

        if (request.getOperatorId() == null || !request.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (request.getTransactionId() == null || request.getTransactionId().length() != Operations.PARAMETERS.TRANSACTIONID.LEN) {
            field = Operations.PARAMETERS.TRANSACTIONID.NAME;
        } else if (request.getOrderId() == null || !request.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (request.getAmount() == null || !request.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN)) {
            field = Operations.PARAMETERS.AMOUNT.NAME;

        } else if (request.getCurrency() == null || !request.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN)) {
            field = Operations.PARAMETERS.CURRENCY.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    /**
     * Method used to validate the Capture request.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateCaptureRequest(CaptureRequest request) throws VPosClientException {
        String field = "";

        if (request.getOperatorId() == null || !request.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN))
            field = Operations.PARAMETERS.OPERATORID.NAME;
        else if (request.getAmount() == null || !request.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN))
            field = Operations.PARAMETERS.AMOUNT.NAME;
        else if (request.getCurrency() == null || !request.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN))
            field = Operations.PARAMETERS.CURRENCY.NAME;
        else if (request.getOrderId() == null || !request.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN))
            field = Operations.PARAMETERS.ORDERID.NAME;
        else if (request.getTransactionId() == null || request.getTransactionId().length() != Operations.PARAMETERS.TRANSACTIONID.LEN)
            field = Operations.PARAMETERS.TRANSACTIONID.NAME;
        else if (request.getExponent() == null && !request.getCurrency().equals(Constants.Currency.EUR.getValue()))
            field = Operations.PARAMETERS.EXPONENT.NAME;


        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    /**
     * Method used to validate the Order Status request.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateOrderStatusRequest(OrderStatusRequest request) throws VPosClientException {
        String field = "";
        if (request.getOperatorId() == null || !request.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (request.getOrderId() == null || !request.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (request.getProductRef() != null && request.getProductRef().matches(Operations.PARAMETERS.PRODUCTREF.PATTERN)) {
            field = Operations.PARAMETERS.PRODUCTREF.NAME;
        } else if (request.getOptions() != null && request.getOptions().matches(Operations.PARAMETERS.OPTIONS.PATTERN)) {
            field = Operations.PARAMETERS.OPTIONS.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    /**
     * Method used to validate the step 0 of a 3D Secure authorization.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateThreeDSAuthorization0Request(ThreeDSAuthorization0Request request) throws VPosClientException {
        String field = "";

        if (request.getOrderId() == null || !request.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (request.getOperatorId() == null) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (request.getPan() == null || !request.getPan().matches(Operations.PARAMETERS.PAN.PATTERNGENERIC)) {
            field = Operations.PARAMETERS.PAN.NAME;
        } else if (request.getCvv2() != null && !request.getCvv2().matches(Operations.PARAMETERS.CVV2.PATTERN)) {
            field = Operations.PARAMETERS.CVV2.NAME;
        } else if (request.getExpDate() == null || !request.getExpDate().matches(Operations.PARAMETERS.EXPDATE.PATTERN)) {
            field = Operations.PARAMETERS.EXPDATE.NAME;
        } else if (request.getAmount() == null || !request.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN)) {
            field = Operations.PARAMETERS.AMOUNT.NAME;
        } else if (request.getCurrency() == null || !request.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN)) {
            field = Operations.PARAMETERS.CURRENCY.NAME;
        } else if (!request.getCurrency().equalsIgnoreCase("978") && (request.getExponent() == null || request.getExponent().matches(Operations.PARAMETERS.EXPONENT.PATTERN))) {
            field = Operations.PARAMETERS.EXPONENT.NAME;
        } else if (request.getAccountingMode() == null || !request.getAccountingMode().matches(Operations.PARAMETERS.ACCOUNTINGMODE.PATTERN)) {
            field = Operations.PARAMETERS.ACCOUNTINGMODE.NAME;
        } else if (request.getNetwork() == null || !request.getNetwork().matches(Operations.PARAMETERS.NETWORK.PATTERN)) {
            field = Operations.PARAMETERS.NETWORK.NAME;
        } else if (request.getEmailCh() != null && !request.getEmailCh().matches(Operations.PARAMETERS.EMAIL.PATTERN)) {
            field = Operations.PARAMETERS.EMAIL.NAMECH;
        } else if (request.getUserId() != null && !request.getUserId().matches(Operations.PARAMETERS.USERID.PATTERN)) {
            field = Operations.PARAMETERS.USERID.NAME;
        } else if (request.getAcquirer() != null && !request.getAcquirer().matches(Operations.PARAMETERS.ACQUIRER.PATTERN)) {
            field = Operations.PARAMETERS.ACQUIRER.NAME;
        } else if (request.getIpAddress() != null && !request.getIpAddress().matches(Operations.PARAMETERS.IPADDRESS.PATTERN)) {
            field = Operations.PARAMETERS.IPADDRESS.NAME;
        } else if (request.getUsrAuthFlag() != null && !request.getUsrAuthFlag().matches(Operations.PARAMETERS.USRAUTHFLAG.PATTERN)) {
            field = Operations.PARAMETERS.USRAUTHFLAG.NAME;
        } else if (request.getOpDescr() != null && !request.getOpDescr().matches(Operations.PARAMETERS.OPDESCR.PATTERN)) {
            field = Operations.PARAMETERS.OPDESCR.PATTERN;
        } else if (request.getAntifraud() != null && !request.getAntifraud().matches(Operations.PARAMETERS.ANTIFRAUD.PATTERN)) {
            field = Operations.PARAMETERS.ANTIFRAUD.NAME;
        } else if (request.getProductRef() != null && !request.getProductRef().matches(Operations.PARAMETERS.PRODUCTREF.PATTERN)) {
            field = Operations.PARAMETERS.PRODUCTREF.NAME;
        } else if (request.getName() != null && !request.getName().matches(Operations.PARAMETERS.NAME.PATTERN)) {
            field = Operations.PARAMETERS.NAME.NAME;
        } else if (request.getSurname() != null && !request.getSurname().matches(Operations.PARAMETERS.SURNAME.PATTERN)) {
            field = Operations.PARAMETERS.SURNAME.NAME;
        } else if (request.getTaxId() != null && !request.getTaxId().matches(Operations.PARAMETERS.TAXID.PATTERN)) {
            field = Operations.PARAMETERS.TAXID.NAME;
        } else if (request.getCreatePanAlias() != null && !request.getCreatePanAlias().matches(Operations.PARAMETERS.CREATEPANALIAS.PATTERN)) {
            field = Operations.PARAMETERS.CREATEPANALIAS.NAME;
        } else if (request.getThreeDSData() == null) {
            field = Operations.PARAMETERS.THREEDSDATA.NAME;
        } else if (request.getNotifyUrl() == null) {
            field = Operations.PARAMETERS.NOTIFURL.NAME;
        }
        if(request.getThreeDSData() != null)
            validateThreeDsData(request.getThreeDSData());

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);

    }

    /**
     * Method used to validate the step 1 of a 3D Secure authorization.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateThreeDSAuthorization1Request(ThreeDSAuthorization1Request request) throws VPosClientException {
        String field = "";
        if (request.getOperatorId() == null || !request.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (request.getThreeDSMtdComplInd() == null || !request.getThreeDSMtdComplInd().matches(Operations.PARAMETERS.THREEDSMTDCOMPLIND.PATTERN)) {
            field = Operations.PARAMETERS.THREEDSMTDCOMPLIND.NAME;
        } else if (request.getThreeDSTransId() == null) {
            field = Operations.PARAMETERS.THREEDSTRANSID.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    /**
     * Method used to validate the step 2 of a 3D Secure authorization.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateThreeDSAuthorization2Request(ThreeDSAuthorization2Request request) throws VPosClientException {
        String field = "";
        if (request.getOperatorId() == null || !request.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (request.getThreeDSTransId() == null) {
            field = Operations.PARAMETERS.THREEDSTRANSID.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    /**
     * Method used to validate a redirect payment request.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateHTMLRedirectFragmentRequest(PaymentInfo request) throws VPosClientException {
        String field = "";

        if (request.getAmount() == null || !request.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN)) {
            field = Operations.PARAMETERS.AMOUNT.NAME;
        } else if (request.getCurrency() == null || !request.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN)) {
            field = Operations.PARAMETERS.CURRENCY.NAME;
        } else if (request.getOrderId() == null || !request.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (request.getShopId() == null || !request.getShopId().matches(Operations.PARAMETERS.SHOPID.PATTERN)) {
            field = Operations.PARAMETERS.SHOPID.NAME;
        } else if (request.getUrlBack() == null) {
            field = Operations.PARAMETERS.URLBACK.NAME;
        } else if (request.getUrlDone() == null) {
            field = Operations.PARAMETERS.URLDONE.NAME;
        } else if (request.getUrlMs() == null) {
            field = Operations.PARAMETERS.URLMS.NAME;
        } else if (request.getAccountingMode() == null || !request.getAccountingMode().matches(Operations.PARAMETERS.ACCOUNTINGMODE.PATTERN)) {
            field = Operations.PARAMETERS.ACCOUNTINGMODE.NAME;
        } else if (request.getAuthorMode() == null || !request.getAuthorMode().matches(Operations.PARAMETERS.AUTHORMODE.PATTERN)) {
            field = Operations.PARAMETERS.AUTHORMODE.NAME;
        }
        
        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);

    }

    /**
     * Method used to validate an Authorization request.
     *
     * @param request request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateAuthorizationRequest(AuthorizationRequest request) throws VPosClientException {
        String field = "";

        if (request.getOrderId() == null || !request.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (request.getOperatorId() == null || !request.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (request.getPan() == null || !request.getPan().matches(Operations.PARAMETERS.PAN.PATTERNGENERIC)) {
            field = Operations.PARAMETERS.PAN.NAME;
        } else if (request.getCvv2() != null && !request.getCvv2().matches(Operations.PARAMETERS.CVV2.PATTERN)) {
            field = Operations.PARAMETERS.CVV2.NAME;
        } else if (request.getExpDate() == null || !request.getExpDate().matches(Operations.PARAMETERS.EXPDATE.PATTERN)) {
            field = Operations.PARAMETERS.EXPDATE.NAME;
        } else if (request.getAmount() == null || !request.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN)) {
            field = Operations.PARAMETERS.AMOUNT.NAME;
        } else if (request.getCurrency() == null || !request.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN)) {
            field = Operations.PARAMETERS.CURRENCY.NAME;
        } else if (!request.getCurrency().equalsIgnoreCase("978") && (request.getExponent() == null || request.getExponent().matches(Operations.PARAMETERS.EXPONENT.PATTERN))) {
            field = Operations.PARAMETERS.EXPONENT.NAME;
        } else if (request.getAccountingMode() == null || !request.getAccountingMode().matches(Operations.PARAMETERS.ACCOUNTINGMODE.PATTERN)) {
            field = Operations.PARAMETERS.ACCOUNTINGMODE.NAME;
        } else if (request.getNetwork() == null || !request.getNetwork().matches(Operations.PARAMETERS.NETWORK.PATTERN)) {
            field = Operations.PARAMETERS.NETWORK.NAME;
        } else if (request.getEmailCh() != null && !request.getEmailCh().matches(Operations.PARAMETERS.EMAIL.PATTERN)) {
            field = Operations.PARAMETERS.EMAIL.NAMECH;
        } else if (request.getUserId() != null && !request.getUserId().matches(Operations.PARAMETERS.USERID.PATTERN)) {
            field = Operations.PARAMETERS.USERID.NAME;
        } else if (request.getAcquirer() != null && !request.getAcquirer().matches(Operations.PARAMETERS.ACQUIRER.PATTERN)) {
            field = Operations.PARAMETERS.ACQUIRER.NAME;
        } else if (request.getIpAddress() != null && !request.getIpAddress().matches(Operations.PARAMETERS.IPADDRESS.PATTERN)) {
            field = Operations.PARAMETERS.IPADDRESS.NAME;
        } else if (request.getUsrAuthFlag() != null && !request.getUsrAuthFlag().matches(Operations.PARAMETERS.USRAUTHFLAG.PATTERN)) {
            field = Operations.PARAMETERS.USRAUTHFLAG.NAME;
        } else if (request.getOpDescr() != null && !request.getOpDescr().matches(Operations.PARAMETERS.OPDESCR.PATTERN)) {
            field = Operations.PARAMETERS.OPDESCR.PATTERN;
        } else if (request.getAntiFraud() != null && !request.getAntiFraud().matches(Operations.PARAMETERS.ANTIFRAUD.PATTERN)) {
            field = Operations.PARAMETERS.ANTIFRAUD.NAME;
        } else if (request.getProductRef() != null && !request.getProductRef().matches(Operations.PARAMETERS.PRODUCTREF.PATTERN)) {
            field = Operations.PARAMETERS.PRODUCTREF.NAME;
        } else if (request.getName() != null && !request.getName().matches(Operations.PARAMETERS.NAME.PATTERN)) {
            field = Operations.PARAMETERS.NAME.NAME;
        } else if (request.getSurname() != null && !request.getSurname().matches(Operations.PARAMETERS.SURNAME.PATTERN)) {
            field = Operations.PARAMETERS.SURNAME.NAME;
        } else if (request.getTaxId() != null && !request.getTaxId().matches(Operations.PARAMETERS.TAXID.PATTERN)) {
            field = Operations.PARAMETERS.TAXID.NAME;
        } else if (request.getCreatePanAlias() != null && !request.getCreatePanAlias().matches(Operations.PARAMETERS.CREATEPANALIAS.PATTERN)) {
            field = Operations.PARAMETERS.CREATEPANALIAS.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    public static void validateThreeDsData(Data3DSJson data) throws VPosClientException {
        String field = null;
        if( data.getBrowserAcceptHeader() == null){
            field= "browserAcceptHeader";
        }else if(data.getBrowserColorDepth() == null){
            field= "browserColorDepth";
        }else if(data.getBrowserJavaEnabled() == null){
            field= "browserJavaEnabled";
        }else if(data.getBrowserIP() == null){
            field= "browserIP";
        }else if(data.getBrowserLanguage() == null){
            field= "browserLanguage";
        }else if(data.getBrowserScreenHeight() == null){
            field= "browserScreenHeight";
        }else if(data.getBrowserScreenWidth() == null){
            field= "browserScreenWidth";
        }

        if (field != null){
            throw new VPosClientException("ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE");
        }

    }
}
