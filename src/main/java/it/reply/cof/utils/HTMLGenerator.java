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

    private static final String INPUT_PATTERN = "<input type=\"hidden\" name=\"KEY\" value=\"VALUE\">";

    private String generateParamsHtml(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

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
        return Base64.getEncoder().encodeToString(html.getBytes());
    }

}
