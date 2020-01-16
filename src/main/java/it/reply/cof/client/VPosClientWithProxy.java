package it.reply.cof.client;

import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.utils.MacAlgorithms;
import it.reply.cof.utils.exception.COFException;

public class VPosClientWithProxy extends VPOSClientAbstract {

    public VPosClientWithProxy(String url, String key, String proxyName, Integer port) throws COFException {
        super(key);
        this.aposClient = new AposPaymentClient(url, proxyName, port);
    }

    public VPosClientWithProxy(String url, String key, MacAlgorithms algorithm, String proxyName, Integer port) throws COFException {
        super(key, algorithm);
        this.aposClient = new AposPaymentClient(url, proxyName, port);
    }

}
