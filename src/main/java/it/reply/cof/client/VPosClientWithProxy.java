package it.reply.cof.client;

import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.utils.mac.MacAlgorithms;
import it.reply.cof.utils.exception.COFException;

public class VPosClientWithProxy extends VPOSClientAbstract {

    public VPosClientWithProxy(String url, String startKey, String apiResultKey, String proxyName, Integer port) throws COFException {
        super(startKey, apiResultKey);
        this.aposClient = new AposPaymentClient(url, proxyName, port);
    }

    public VPosClientWithProxy(String url, String startKey, String apiResultKey,  MacAlgorithms algorithm, String proxyName, Integer port) throws COFException {
        super(startKey, apiResultKey,algorithm);
        this.aposClient = new AposPaymentClient(url, proxyName, port);
    }

}
