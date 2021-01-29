package com.example.customer.exception;
/**
 * @author Raghavendra
 */
public class CustomerServiceNotFoundException extends RuntimeException {

    public CustomerServiceNotFoundException() {
        super();
    }

    public CustomerServiceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomerServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerServiceNotFoundException(String message) {
        super(message);
    }

    public CustomerServiceNotFoundException(Throwable cause) {
        super(cause);
    }
}
