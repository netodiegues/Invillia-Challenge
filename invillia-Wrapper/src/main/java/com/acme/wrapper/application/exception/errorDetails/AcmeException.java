package com.acme.wrapper.application.exception.errorDetails;

import org.springframework.http.HttpStatus;

/**
 *
 * @author jose.diegues
 */
public class AcmeException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public AcmeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
