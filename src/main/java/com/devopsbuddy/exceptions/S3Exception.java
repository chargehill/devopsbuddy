package com.devopsbuddy.exceptions;

/**
 * Created by octavio on 12/28/16.
 */
public class S3Exception extends RuntimeException {
    public S3Exception(String message) {
        super(message);
    }

    public S3Exception(Throwable cause) {
        super(cause);
    }
}
