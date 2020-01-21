package it.reply.cof.client;

import it.reply.cof.apos.utils.AposPaymentClient;
import it.reply.cof.utils.exception.COFException;

public class VPOSSimpleClient extends VPOSClientAbstract {

    public VPOSSimpleClient(String urlApos, String startKey, String apiResultKey) throws COFException {
        super(startKey, apiResultKey);
        this.aposClient = new AposPaymentClient(urlApos);
    }
}
