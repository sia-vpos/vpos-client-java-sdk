package eu.sia.vpos.client.utils;


import eu.sia.vpos.client.response.xml.Operation;
import eu.sia.vpos.client.utils.constants.Operations;

import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utility class used to generate payment initiation HTML document
 *
 * @author Gabriel Raul Marini
 */
public class HTMLGenerator {

    private static final String FORM_PATTERN = "PGZvcm0gYWN0aW9uPSJbQVBPU19VUkxdIiBtZXRob2Q9IlBPU1QiPjxpbnB1dCBuYW1lPSJQQUdFIiB0eXBlPSJoaWRkZW4iIHZhbHVlPSJMQU5EIj5bUEFSQU1FVEVSU108aW5wdXQgaWQ9InN1Ym1pdCIgc3R5bGU9ImRpc3BsYXk6IG5vbmU7IiB0eXBlPXN1Ym1pdCAgdmFsdWU9Ii4iPjwvZm9ybT4=";
    private static final String INPUT_PATTERN = "<input type=\"hidden\" name=\"KEY\" value=\"VALUE\">";
    private static final String SCRIPT = "PHNjcmlwdCB0eXBlPSJ0ZXh0L2phdmFzY3JpcHQiPndpbmRvdy5vbmxvYWQgPSBmdW5jdGlvbigpe3NldFRpbWVvdXQoZnVuY3Rpb24oKXtkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnc3VibWl0JykuY2xpY2soKTt9LCBbREVMQVldKTt9PC9zY3JpcHQ+";
    private static final String TEMPLATE = "<div><form id=\"myForm\"action=\"[VPOS_URL]\" method=\"POST\"><input name=\"PAGE\" type=\"hidden\" value=\"[PAGE]\">[PARAMETERS]</form><script>document.getElementById('myForm').submit();</script></div>";
    private final Base64.Decoder decoder;
    private final Base64.Encoder encoder;

    public HTMLGenerator() {
        encoder = Base64.getEncoder();
        decoder = Base64.getDecoder();
    }

    public String buildHtmlPaymentDiv(String urlApos, Map<String, String> params) {
        String html= TEMPLATE;

        html = html.replace("[VPOS_URL]", urlApos);
        if(params.get(Operations.PARAMETERS.TOKEN.NAME)!= null){
            html = html.replace("[PAGE]", "TOKEN");
        }else{
            html = html.replace("[PAGE]", "LAND");
        }
        html = html.replace("[PARAMETERS]", generateParamsHtml(params));
        return html;
    }

    public String base64ToHtml(String base64, Integer delay) {
        String html = new String(decoder.decode(base64.getBytes()));
        String decodedFormPattern = new String(decoder.decode(FORM_PATTERN));
        String decodedScript = new String(decoder.decode(SCRIPT));

        html = html.replace("</body>", decodedFormPattern + "</body>");
        html = html.replace("</html>", decodedScript + "</html>");
        html = html.replace("[DELAY]", delay.toString());

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
