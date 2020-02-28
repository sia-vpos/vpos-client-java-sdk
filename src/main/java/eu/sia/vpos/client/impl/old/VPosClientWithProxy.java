package eu.sia.vpos.client.impl.old;

import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.impl.util.VPosPaymentClient;
import eu.sia.vpos.client.utils.mac.MacAlgorithms;

/**
 * VPOSClient proxy enabled
 */
public class VPosClientWithProxy extends VPOSClientAbstract {

    public VPosClientWithProxy(String url, String startKey, String apiResultKey, String proxyName, Integer port) throws VPosClientException {
        super(startKey, apiResultKey);
        this.aposClient = new VPosPaymentClient(url, proxyName, port);
    }

    public VPosClientWithProxy(String url, String startKey, String apiResultKey, MacAlgorithms algorithm, String proxyName, Integer port) throws VPosClientException {
        super(startKey, apiResultKey, algorithm);
        this.aposClient = new VPosPaymentClient(url, proxyName, port);
    }

}
