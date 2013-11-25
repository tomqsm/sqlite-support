package biz.letsweb.sqlite;

public class FulljarRuntimeException extends RuntimeException{

    public FulljarRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FulljarRuntimeException(String message) {
        super(message);
    }
    
}
