package com.acme.wrapper.application.exception.errorDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 *
 * @author jose.diegues
 */
public class ErrorDetails {

    private String title;
    private int status;
    private String detail;
    private LocalDateTime timestamp;
    private String developerMessage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public static class Builder {

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

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder status(final int status) {
            this.status = status;
            return this;
        }

        public Builder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        public Builder timestamp(final LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(final String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle(title);
            errorDetails.setStatus(status);
            errorDetails.setDetail(detail);
            errorDetails.setTimestamp(timestamp);
            errorDetails.setDeveloperMessage(developerMessage);
            return errorDetails;
        }
    }
}
