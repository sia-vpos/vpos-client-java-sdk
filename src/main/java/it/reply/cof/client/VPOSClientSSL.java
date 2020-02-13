package it.reply.cof.client;

import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.utils.exception.COFException;

import java.io.File;
import java.security.KeyStore;

/**
 * CLient implementation able to perform VPOS calls through a defined gateway
 *
 * @author Gabriel Raul Marini
 */
public class VPOSClientSSL extends VPOSClientAbstract {

    /**
     * @param url
     * @param startKey
     * @param apiResultKey
     * @param ksPw
     * @param ks
     * @param kFile
     * @throws COFException
     */
    public VPOSClientSSL(String url, String startKey, String apiResultKey, String ksPw, KeyStore ks, File kFile) throws COFException {
        super(startKey, apiResultKey);
        this.aposClient = new AposPaymentClient(url, ksPw, ks, kFile);
    }

}
