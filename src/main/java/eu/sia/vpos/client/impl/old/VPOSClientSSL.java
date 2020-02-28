package eu.sia.vpos.client.impl.old;

import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.impl.util.VPosPaymentClient;

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
     * @throws VPosClientException
     */
    public VPOSClientSSL(String url, String startKey, String apiResultKey, String ksPw, KeyStore ks, File kFile) throws VPosClientException {
        super(startKey, apiResultKey);
        this.aposClient = new VPosPaymentClient(url, ksPw, ks, kFile);
    }

}
