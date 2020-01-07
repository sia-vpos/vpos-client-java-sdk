package it.reply.cof.utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.core.io.ClassPathResource;

import com.cirfood.payment.gateway.dto.DtoTransactions;
import com.cirfood.payment.gateway.exceptions.PGException500;
import com.cirfood.payment.gateway.log.PGLogger;
import com.cirfood.payment.gateway.util.Constants.AposConstant;
import com.cirfood.payment.gateway.util.Constants.Currency;

public class Utils {

	private static final String CLASSNAME = Utils.class.getSimpleName();
	private static final String NUMBERS = "0123456789";
	public static final String DATE_PATTERN = "MMyy";

	private Utils() {

	}

	public static Map<String, String> generateMap(DtoTransactions transaction, String shopId, String urlMs,
			String urlDone, String urlBack) {
		Map<String, String> map = new HashMap<>();

		map.put(AposConstant.URLMS, urlMs);
		map.put(AposConstant.URLDONE, urlDone);
		map.put(AposConstant.ORDERID, transaction.getOrderId());
		map.put(AposConstant.SHOPID, shopId);
		map.put(AposConstant.AMOUNT, transaction.getAmount());
		map.put(AposConstant.CURRENCY, Currency.getCurrency(transaction.getCurrency()).getValue());
		map.put(AposConstant.ACCOUNTINGMODE, transaction.getAccountingMode());
		map.put(AposConstant.AUTHORMODE, "I"); // TODO missing
		map.put(AposConstant.OPTIONS, "GM"); // TODO missing
		map.put(AposConstant.URLBACK, urlBack);
		map.put(AposConstant.EMAIL, "enelpaytest@yopmail.com"); // TODO missing

		return map;
	}

	public static String generateUrl(Map<String, String> map, String key, String urlApos) throws PGException500 {
		StringBuilder sb = new StringBuilder();

		sb.append(AposConstant.URLMS + '=' + map.get(AposConstant.URLMS));

		appendField(AposConstant.URLDONE, map.get(AposConstant.URLDONE), sb);
		appendField(AposConstant.ORDERID, map.get(AposConstant.ORDERID), sb);
		appendField(AposConstant.SHOPID, map.get(AposConstant.SHOPID), sb);
		appendField(AposConstant.AMOUNT, map.get(AposConstant.AMOUNT), sb);
		appendField(AposConstant.CURRENCY, map.get(AposConstant.CURRENCY), sb);
		appendField(AposConstant.ACCOUNTINGMODE, map.get(AposConstant.ACCOUNTINGMODE), sb);
		appendField(AposConstant.AUTHORMODE, map.get(AposConstant.AUTHORMODE), sb);
		appendField(AposConstant.OPTIONS, map.get(AposConstant.OPTIONS), sb);

		String tmp = sb.toString();

		appendField(AposConstant.MAC, HmacCalculator.calculate(tmp, key), sb);

		appendField(AposConstant.URLBACK, map.get(AposConstant.URLBACK), sb);
		appendField(AposConstant.EMAIL, map.get(AposConstant.EMAIL), sb);

		PGLogger.debug("Apos Url: " + urlApos + "&LANG=ITA&" + sb.toString(),CLASSNAME);

		return urlApos + "&LANG=ITA&" + sb.toString();
	}

	private static void appendField(String key, String string, StringBuilder sb) {
		sb.append('&');
		sb.append(key);
		sb.append('=');
		sb.append(string);
	}

	public static String addField(String field, String fieldName) {
		String mac = "";
		if (StringUtils.isNotBlank(field) || (fieldName.equals(Constants.OPERATIONS.PARAMETERS.URLDONE.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.ORDERID.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.SHOPID.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.AMOUNT.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.CURRENCY.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.ACCOUNTINGMODE.NAME))) {
			mac = "&" + fieldName + "=" + field;
		}
		return mac;
	}

	public static String addFieldAuth(String field, String fieldName) {
		String mac = "";
		if (StringUtils.isNotBlank(field) || (!fieldName.equals(Constants.OPERATIONS.AUTHORIZATION.PANTAIL)
				&& !fieldName.equals(Constants.OPERATIONS.AUTHORIZATION.PANEXPIRYDATE)
				&& !fieldName.equals(Constants.OPERATIONS.AUTHORIZATION.CARDTYPE))) {
			mac = "&" + field;
		}
		return mac;
	}

	public static String addFieldDone(String field, String fieldName) {
		String mac = "";
		if (StringUtils.isNotBlank(field) || (fieldName.equals(Constants.OPERATIONS.PARAMETERS.ORDERID.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.SHOPID.NAME)
				|| fieldName.equals(Constants.OPERATIONS.CONFIRMATION.AUTHORIZATIONUMBER)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.AMOUNT.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.CURRENCY.NAME)
				|| fieldName.equals(Constants.OPERATIONS.CONFIRMATION.TRANSACTIONID)
				|| fieldName.equals(Constants.OPERATIONS.CONFIRMATION.RESULT)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.ACCOUNTINGMODE.NAME)
				|| fieldName.equals(Constants.OPERATIONS.PARAMETERS.NETWORK.NAME)
				|| fieldName.equals(Constants.OPERATIONS.CONFIRMATION.TRANSACTIONTYPE))) {
			mac = "&" + fieldName + "=" + field;
		}
		return mac;
	}

	public static String generateRandomDigits() {
		StringBuilder rndBuilder = new StringBuilder();
		SecureRandom rng = new SecureRandom();

		for (int i = 0; i < 24; i++) {
			rndBuilder.append(NUMBERS.charAt(rng.nextInt(NUMBERS.length())));
		}

		return rndBuilder.toString();
	}

	public static String htmlToBase64(String fileName, Map<String, String> replacements) throws PGException500 {
		String html = "";

		try {
			html = Jsoup.parse(new ClassPathResource(fileName).getFile(), "UTF-8").toString();
		} catch (IOException e) {
			throw new PGException500("", e.getCause());
		}

		for (Map.Entry<String, String> replacement : replacements.entrySet()) {
			html = html.replace("[" + replacement.getKey() + "]", replacement.getValue());
		}

		return Base64.getEncoder().encodeToString(html.getBytes());
	}

}
