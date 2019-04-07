package com.acme.wrapper.application.exception;

import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage.ValidationError;

/**
 *
 * @author jose.diegues
 */
public class ValidationErrorException extends RuntimeException {

    private ValidationError[] errors;
    
    public ValidationErrorException(String message) {
        super(message);
    }

    public ValidationError[] getErrors() {
        return errors;
    }

    public void setErrors(ValidationError[] errors) {
        this.errors = errors;
    }
    
    
}
