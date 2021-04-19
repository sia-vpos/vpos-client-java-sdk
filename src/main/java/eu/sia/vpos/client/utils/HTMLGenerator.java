package eu.sia.vpos.client.utils;

import java.util.Map;
import java.util.Map.Entry;

import eu.sia.vpos.client.utils.constants.Operations;

/**
 * Utility class used to generate payment initiation HTML document
 *
 * @author a.simonetti
 * @author Gabriel Raul Marini
 */
public class HTMLGenerator {

	private static final String INPUT_PATTERN = "<input type=\"hidden\" name=\"KEY\" value=\"VALUE\">";
	private static final String TEMPLATE = "<div><form id=\"myForm\"action=\"[VPOS_URL]\" method=\"POST\"><input name=\"PAGE\" type=\"hidden\" value=\"[PAGE]\">[PARAMETERS]</form><script type=\"text/javascript\">function subForm() {document.getElementById('myForm').submit();}</script></div>";

	public HTMLGenerator() {
            
	}

	public String buildHtmlPaymentDiv(String urlApos, Map<String, String> params) {
		String html = TEMPLATE;

		html = html.replace("[VPOS_URL]", urlApos);
		if (params.get(Operations.PARAMETERS.TOKEN.NAME) != null) {
			html = html.replace("[PAGE]", "TOKEN");
		} else {
			html = html.replace("[PAGE]", "LAND");
		}
		html = html.replace("[PARAMETERS]", generateParamsHtml(params));
		return html;
	}

	private String generateParamsHtml(Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		String decodedInputPattern = INPUT_PATTERN;

		for (Entry<String, String> entry : params.entrySet()) {
			if (entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
				String toAppend = decodedInputPattern.replace("KEY", entry.getKey()).replace("VALUE", entry.getValue());
				sb.append(toAppend);
			}
		}

		return sb.toString();
	}

}
