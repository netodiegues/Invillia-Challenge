package com.acme.wrapper.application.exception.errorDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 *
 * @author jose.diegues
 */
public class ValidationErrorDetails extends ErrorDetails {

    private String field;
    private String fieldMessage;

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public static final class Builder {

        private String title;
        private int status;
        private String detail;
        private LocalDateTime timestamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(final String value) {
            this.title = value;
            return this;
        }

        public Builder status(final int value) {
            this.status = value;
            return this;
        }

        public Builder detail(final String value) {
            this.detail = value;
            return this;
        }

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        public Builder timestamp(final LocalDateTime value) {
            this.timestamp = value;
            return this;
        }

        public Builder developerMessage(final String value) {
            this.developerMessage = value;
            return this;
        }

        public Builder field(final String value) {
            this.field = value;
            return this;
        }

        public Builder fieldMessage(final String value) {
            this.fieldMessage = value;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationDetails = new ValidationErrorDetails();
            validationDetails.setDeveloperMessage(developerMessage);
            validationDetails.setTitle(title);
            validationDetails.setDetail(detail);
            validationDetails.setTimestamp(timestamp);
            validationDetails.setStatus(status);
            validationDetails.field = field;
            validationDetails.fieldMessage = fieldMessage;
            return validationDetails;
        }
    }
}
