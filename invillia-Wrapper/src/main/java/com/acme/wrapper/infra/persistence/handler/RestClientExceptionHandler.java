package com.acme.wrapper.infra.persistence.handler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.acme.wrapper.application.exception.BadRequestException;
import com.acme.wrapper.application.exception.errorDetails.AcmeException;
import com.acme.wrapper.application.exception.errorDetails.ErrorDetails;
import com.acme.wrapper.application.exception.errorDetails.ResourceNotFoundDetails;
import com.acme.wrapper.application.exception.errorDetails.ValidationErrorDetails;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author jose.diegues
 */
@ControllerAdvice
public final class RestClientExceptionHandler {

    public RestClientExceptionHandler() {
    }

    public RestClientExceptionHandler(HttpStatusCodeException e, String api, String method) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            if (e.getResponseBodyAsString().contains("fieldMessage")) {
                ValidationErrorDetails error = objectMapper.readValue(e.getResponseBodyAsString(), ValidationErrorDetails.class);
                throw new BadRequestException(error.getField().concat(" ").concat(error.getFieldMessage()));
            } else {
                BadRequestException error = objectMapper.readValue(e.getResponseBodyAsString(), BadRequestException.class);
                throw new BadRequestException(error.getMessage());
            }
        } else {
            ErrorDetails error = objectMapper.readValue(e.getResponseBodyAsString(), ErrorDetails.class);
            throw new AcmeException(error.getDetail(), e.getStatusCode());
        }
    }

    @ExceptionHandler(AcmeException.class)
    public ResponseEntity<?> handleLocaleServiceException(AcmeException acmeException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(acmeException.getHttpStatus().value())
                .title("Application Exception")
                .detail(acmeException.getMessage())
                .developerMessage(acmeException.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, acmeException.getHttpStatus());
    }
}
