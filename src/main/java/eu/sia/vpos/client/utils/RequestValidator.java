package eu.sia.vpos.client.utils;

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
     * @param requestDto request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateRefundRequest(RefundRequest requestDto) throws VPosClientException {
        String field = "";

        if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
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
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    /**
     * Method used to validate the Confirm request.
     *
     * @param requestDto request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateCaptureRequest(CaptureRequest requestDto) throws VPosClientException {
        String field = "";

        if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN))
            field = Operations.PARAMETERS.OPERATORID.NAME;
        else if (requestDto.getAmount() == null || !requestDto.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN))
            field = Operations.PARAMETERS.AMOUNT.NAME;
        else if (requestDto.getCurrency() == null || !requestDto.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN))
            field = Operations.PARAMETERS.CURRENCY.NAME;
        else if (requestDto.getOrderId() == null || !requestDto.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN))
            field = Operations.PARAMETERS.ORDERID.NAME;
        else if (requestDto.getTransactionId() == null || requestDto.getTransactionId().length() != Operations.PARAMETERS.TRANSACTIONID.LEN)
            field = Operations.PARAMETERS.TRANSACTIONID.NAME;
        else if (requestDto.getExponent() == null && !requestDto.getCurrency().equals(Constants.Currency.EUR.getValue()))
            field = Operations.PARAMETERS.EXPONENT.NAME;


        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }


    /**
     * Method used to validate the Order Status request.
     *
     * @param requestDto request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateOrderStatusRequest(OrderStatusRequest requestDto) throws VPosClientException {
        String field = "";
        if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (requestDto.getOrderId() == null || !requestDto.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (requestDto.getProductRef() != null && requestDto.getProductRef().matches(Operations.PARAMETERS.PRODUCTREF.PATTERN)) {
            field = Operations.PARAMETERS.PRODUCTREF.NAME;
        } else if (requestDto.getOptions() != null && requestDto.getOptions().matches(Operations.PARAMETERS.OPTIONS.PATTERN)) {
            field = Operations.PARAMETERS.OPTIONS.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    /**
     * Method used to validate the first step of a 3D Secure authorization.
     *
     * @param requestDto request to validate
     * @throws VPosClientException raised when a field is missing (if it is mandatory) or not valid
     */
    public static void validateThreeDSAuthorization0Request(ThreeDSAuthorization0Request requestDto) throws VPosClientException {
        String field = "";

        if (requestDto.getOrderId() == null || !requestDto.getOrderId().matches(Operations.PARAMETERS.ORDERID.PATTERN)) {
            field = Operations.PARAMETERS.ORDERID.NAME;
        } else if (requestDto.getOperatorId() == null) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (requestDto.getPan() == null || !requestDto.getPan().matches(Operations.PARAMETERS.PAN.PATTERNGENERIC)) {
            field = Operations.PARAMETERS.PAN.NAME;
        } else if (requestDto.getCvv2() != null && !requestDto.getCvv2().matches(Operations.PARAMETERS.CVV2.PATTERN)) {
            field = Operations.PARAMETERS.CVV2.NAME;
        } else if (requestDto.getExpDate() == null || !requestDto.getExpDate().matches(Operations.PARAMETERS.EXPDATE.PATTERN)) {
            field = Operations.PARAMETERS.EXPDATE.NAME;
        } else if (requestDto.getAmount() == null || !requestDto.getAmount().matches(Operations.PARAMETERS.AMOUNT.PATTERN)) {
            field = Operations.PARAMETERS.AMOUNT.NAME;
        } else if (requestDto.getCurrency() == null || !requestDto.getCurrency().matches(Operations.PARAMETERS.CURRENCY.PATTERN)) {
            field = Operations.PARAMETERS.CURRENCY.NAME;
        } else if (!requestDto.getCurrency().equalsIgnoreCase("978") && (requestDto.getExponent() == null || requestDto.getExponent().matches(Operations.PARAMETERS.EXPONENT.PATTERN))) {
            field = Operations.PARAMETERS.EXPONENT.NAME;
        } else if (requestDto.getAccountingMode() == null || !requestDto.getAccountingMode().matches(Operations.PARAMETERS.ACCOUNTINGMODE.PATTERN)) {
            field = Operations.PARAMETERS.ACCOUNTINGMODE.NAME;
        } else if (requestDto.getNetwork() == null || !requestDto.getNetwork().matches(Operations.PARAMETERS.NETWORK.PATTERN)) {
            field = Operations.PARAMETERS.NETWORK.NAME;
        } else if (requestDto.getEmailCh() != null && !requestDto.getEmailCh().matches(Operations.PARAMETERS.EMAIL.PATTERN)) {
            field = Operations.PARAMETERS.EMAIL.NAMECH;
        } else if (requestDto.getUserId() != null && !requestDto.getUserId().matches(Operations.PARAMETERS.USERID.PATTERN)) {
            field = Operations.PARAMETERS.USERID.NAME;
        } else if (requestDto.getAcquirer() != null && !requestDto.getAcquirer().matches(Operations.PARAMETERS.ACQUIRER.PATTERN)) {
            field = Operations.PARAMETERS.ACQUIRER.NAME;
        } else if (requestDto.getIpAddress() != null && !requestDto.getIpAddress().matches(Operations.PARAMETERS.IPADDRESS.PATTERN)) {
            field = Operations.PARAMETERS.IPADDRESS.NAME;
        } else if (requestDto.getUsrAuthFlag() != null && !requestDto.getUsrAuthFlag().matches(Operations.PARAMETERS.USRAUTHFLAG.PATTERN)) {
            field = Operations.PARAMETERS.USRAUTHFLAG.NAME;
        } else if (requestDto.getOpDescr() != null && !requestDto.getOpDescr().matches(Operations.PARAMETERS.OPDESCR.PATTERN)) {
            field = Operations.PARAMETERS.OPDESCR.PATTERN;
        } else if (requestDto.getAntifraud() != null && !requestDto.getAntifraud().matches(Operations.PARAMETERS.ANTIFRAUD.PATTERN)) {
            field = Operations.PARAMETERS.ANTIFRAUD.NAME;
        } else if (requestDto.getProductRef() != null && !requestDto.getProductRef().matches(Operations.PARAMETERS.PRODUCTREF.PATTERN)) {
            field = Operations.PARAMETERS.PRODUCTREF.NAME;
        } else if (requestDto.getName() != null && !requestDto.getName().matches(Operations.PARAMETERS.NAME.PATTERN)) {
            field = Operations.PARAMETERS.NAME.NAME;
        } else if (requestDto.getSurname() != null && !requestDto.getSurname().matches(Operations.PARAMETERS.SURNAME.PATTERN)) {
            field = Operations.PARAMETERS.SURNAME.NAME;
        } else if (requestDto.getTaxId() != null && !requestDto.getTaxId().matches(Operations.PARAMETERS.TAXID.PATTERN)) {
            field = Operations.PARAMETERS.TAXID.NAME;
        } else if (requestDto.getCreatePanAlias() != null && !requestDto.getCreatePanAlias().matches(Operations.PARAMETERS.CREATEPANALIAS.PATTERN)) {
            field = Operations.PARAMETERS.CREATEPANALIAS.NAME;
        } else if (requestDto.getThreeDSData() == null) {
            field = Operations.PARAMETERS.THREEDSDATA.NAME;
        } else if (requestDto.getNotifyUrl() == null) {
            field = Operations.PARAMETERS.NOTIFURL.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);

    }

    public static void validateThreeDSAuthorization1Request(ThreeDSAuthorization1Request requestDto) throws VPosClientException {
        String field = "";
        if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (requestDto.getThreeDSMtdComplInd() == null) {
            field = Operations.PARAMETERS.THREEDSMTDCOMPLIND.NAME;
        } else if (requestDto.getThreeDSTransId() == null) {
            field = Operations.PARAMETERS.THREEDSTRANSID.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

    public static void validateThreeDSAuthorization2Request(ThreeDSAuthorization2Request requestDto) throws VPosClientException {
        String field = "";
        if (requestDto.getOperatorId() == null || !requestDto.getOperatorId().matches(Operations.PARAMETERS.OPERATORID.PATTERN)) {
            field = Operations.PARAMETERS.OPERATORID.NAME;
        } else if (requestDto.getThreeDSTransId() == null) {
            field = Operations.PARAMETERS.THREEDSTRANSID.NAME;
        }

        if (!field.isEmpty())
            throw new VPosClientException(ERROR_MSG_PREMISE + field + ERROR_MSG_QUEUE);
    }

}
