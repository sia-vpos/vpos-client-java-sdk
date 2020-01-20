package it.reply.cof.utils;

import it.reply.cof.utils.exception.COFException;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author Gabriel Raul Marini
 */
public class HTMLGenerator {

    private static final String FORM_PATTERN = "PGZvcm0gYWN0aW9uPSJbQVBPU19VUkxdIiBtZXRob2Q9IlBPU1QiPjxpbnB1dCBuYW1lPSJQQUdFIiB0eXBlPSJoaWRkZW4iIHZhbHVlPSJMQU5EIj5bUEFSQU1FVEVSU108aW5wdXQgaWQ9InN1Ym1pdCIgc3R5bGU9ImRpc3BsYXk6IG5vbmU7IiB0eXBlPXN1Ym1pdCAgdmFsdWU9Ii4iPjwvZm9ybT4=";
    private static final String INPUT_PATTERN = "PGlucHV0IHR5cGU9ImhpZGRlbiIgbmFtZT0iS0VZIiB2YWx1ZT0iVkFMVUUiPg==";
    private static final String SCRIPT = "PHNjcmlwdCB0eXBlPSJ0ZXh0L2phdmFzY3JpcHQiPndpbmRvdy5vbmxvYWQgPSBmdW5jdGlvbigpe3NldFRpbWVvdXQoZnVuY3Rpb24oKXtkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgnc3VibWl0JykuY2xpY2soKTt9LCBbREVMQVldKTt9PC9zY3JpcHQ+";

    private final Base64.Decoder decoder;
    private final Base64.Encoder encoder;

    public HTMLGenerator(){
        encoder = Base64.getEncoder();
        decoder = Base64.getDecoder();
    }

    private String generateParamsHtml(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        String decodedInputPattern = new String(decoder.decode(INPUT_PATTERN));

        for (Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
                String toAppend = INPUT_PATTERN.replace("KEY", entry.getKey()).replace("VALUE", entry.getValue());
                sb.append(toAppend);
            }
        }

        return sb.toString();
    }

    public String htmlToBase64(String fileName, Map<String, String> params) throws COFException {
        String html;

        try (Scanner sc = new Scanner(new File(fileName))) {
            html = sc.useDelimiter("\\Z").next();
        } catch (IOException e) {
            throw new COFException("Error while generating HTML base64 encoded file", e.getCause());
        }

        html = html.replace("[PARAMETERS]", generateParamsHtml(params));
        return encoder.encodeToString(html.getBytes());
    }

    public String base64ToHtml(String base64, Integer delay){
        String html = new String(decoder.decode(base64.getBytes()));
        String decodedFormPattern = new String(decoder.decode(FORM_PATTERN));
        String decodedScript = new String(decoder.decode(SCRIPT));

        html = html.replace("</body>", decodedFormPattern + "</body>");
        html = html.replace("</html>", decodedScript + "</html>");
        html = html.replace("[DELAY]", delay.toString());

        return html;
    }

}
