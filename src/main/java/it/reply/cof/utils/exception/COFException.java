package it.reply.cof.utils.exception;

public class COFException extends Exception{

    private final String message;
    private final Throwable rootCause;

    public COFException(String message, Throwable rootCause) {
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
