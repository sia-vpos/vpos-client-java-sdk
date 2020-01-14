package it.reply.cof.utils;

import it.reply.cof.dto.request.ConfirmRequestDto;
import it.reply.cof.dto.request.RefundRequestDto;
import it.reply.cof.utils.constants.AposConstants;
import it.reply.cof.utils.constants.Constants;
import it.reply.cof.utils.constants.Operations;
import it.reply.cof.utils.exception.COFException;

public class RequestValidator {

    private RequestValidator() {

    }

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

}
