package Util.exception;

public class CommanException extends Exception{
    public CommanException() {
    }

    public CommanException(String message) {
        super(message);
    }

    public CommanException(String message, Throwable cause) {
        super(message, cause);
    }
}
