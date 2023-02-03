package edu.neu.coe.info6205.sort;

/**
 * Class SortException.
 */
public class SortException extends RuntimeException {

    public SortException(String message) {
        super(message);
    }

    public SortException(String message, Throwable cause) {
        super(message, cause);
    }

    public SortException(Throwable cause) {
        super(cause);
    }

    public SortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
