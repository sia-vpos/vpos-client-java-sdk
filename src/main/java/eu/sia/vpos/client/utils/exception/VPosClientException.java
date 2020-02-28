package eu.sia.vpos.client.utils.exception;

public class VPosClientException extends Exception{

    private final String message;
    private final Throwable rootCause;

    public VPosClientException(String message){
        this.message = message;
        rootCause = null;
    }

    public VPosClientException(String message, Throwable rootCause) {
        this.message = message;
        this.rootCause = rootCause;
    }

    public String getExceptionMessage() {
        return message;
    }

    public Throwable getRootCause() {
        return rootCause;
    }
}
