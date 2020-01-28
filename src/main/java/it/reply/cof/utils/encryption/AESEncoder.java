package it.reply.cof.utils.encryption;

import it.reply.cof.utils.exception.COFException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Utility class performing AES encryption
 *
 * @author Gabriel Raul Marini
 */
public final class AESEncoder {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String ENCRYPTION = "AES";
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private AESEncoder(){}

    /**
     * @param apiSecretMerchant secret key used to perform encryption
     * @param jsonObject        string representation of a JSON object
     * @return the input string encrypted with the first 16 bytes of the merchant's secret key
     * @throws COFException in case of failure
     */
    public static String encode3DSData(String apiSecretMerchant, String
            jsonObject) throws COFException {
        // Initialization vector
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        // AES Key from the API merchant key
        byte[] key = apiSecretMerchant.substring(0, 16).getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION);

        // What we should encrypt
        byte[] toEncrypt = jsonObject.getBytes(CHARSET);

        // Encrypt
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(toEncrypt);
            // Convert to base64
            return DatatypeConverter.printBase64Binary(encrypted);
        }catch (Exception e) {
            throw new COFException(e.getMessage(), e.getCause());
        }
    }

    public static void main(String args[]) throws COFException {
        System.out.println(AESEncoder.encode3DSData("aaaaaaaaaaaaaaaa", "ciao"));
    }
}
