package it.reply.cof.client;

import it.reply.cof.utils.exception.COFException;

public class VPOSSimpleClient extends VPOSClientAbstract {

    public VPOSSimpleClient(String startKey, String apiResultKey) throws COFException {
        super(startKey, apiResultKey);
    }
}
