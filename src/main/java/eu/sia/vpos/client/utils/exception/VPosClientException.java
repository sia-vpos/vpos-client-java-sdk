package eu.sia.vpos.client.utils.exception;

public class VPosClientException extends Exception{

    public VPosClientException() {
    }

    public VPosClientException(String message) {
        super(message);
    }

    public VPosClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public VPosClientException(Throwable cause) {
        super(cause);
    }

    public VPosClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
