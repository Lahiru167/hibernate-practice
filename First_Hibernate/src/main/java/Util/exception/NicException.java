package Util.exception;

import Util.Enums.Error_Case;

public class NicException extends Exception {
    private  int statusCode;
    private Error_Case errorCase;




    public NicException(){
       super();

    }
    public NicException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;

    }

    public NicException(String message, Throwable cause, int statusCode, Error_Case errorCase) {
        super(message, cause);
        this.statusCode = statusCode;
        this.errorCase = errorCase;
    }
}
