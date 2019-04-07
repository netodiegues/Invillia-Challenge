package com.acme.wrapper.application.exception.errorDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 *
 * @author jose.diegues
 */
public class ResourceNotFoundDetails extends ErrorDetails {

    public static final class Builder {

        private String title;
        private int status;
        private String detail;
        private LocalDateTime timestamp;
        private String developerMessage;

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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setTimestamp(timestamp);
            resourceNotFoundDetails.setStatus(status);
            return resourceNotFoundDetails;
        }
    }
}
