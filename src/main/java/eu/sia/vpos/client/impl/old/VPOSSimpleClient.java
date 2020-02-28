package eu.sia.vpos.client.impl.old;

import eu.sia.vpos.client.utils.exception.VPosClientException;
import eu.sia.vpos.client.impl.util.VPosPaymentClient;

/**
 * Simple VPOS client supporting all the operations to interact with SIA VPOS systems
 *
 * @author Gabriel Raul Marini
 */
public class VPOSSimpleClient extends VPOSClientAbstract {

    /**
     * @param urlApos      hostname of the VPOS API endpoint
     * @param startKey     used to calculate the MAC in payment initiation messages
     * @param apiResultKey used to calculate the MAC for all the messages between user's systems and
     *                     VPOS endpoints
     * @throws VPosClientException in case of failure (more details in exception message)
     */
    public VPOSSimpleClient(String urlApos, String startKey, String apiResultKey) throws VPosClientException {
        super(startKey, apiResultKey);
        this.aposClient = new VPosPaymentClient(urlApos);
    }
}
