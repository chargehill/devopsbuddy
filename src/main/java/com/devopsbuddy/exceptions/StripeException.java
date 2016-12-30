package com.devopsbuddy.exceptions;

/**
 * Created by octavio on 12/29/16.
 */
public class StripeException extends RuntimeException {

    public StripeException() {

    }

    public StripeException(String message) {
        super(message);
    }

    public StripeException(Throwable cause) {
        super(cause);
    }
}
