package com.jifs.server.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppError {

    @NotNull
    private Timestamp timestamp;

    @JsonIgnore
    @NotNull
    private HttpStatus httpStatus;

    private int status;

    private String description;

    private String detail;

    private String message;

    private String error;

    private String path;

    public AppError(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        timestamp = new Timestamp(System.currentTimeMillis());
        status = httpStatus.value();
        error = httpStatus.getReasonPhrase();
    }

    public AppError(HttpStatus httpStatus, String description) {
        this(httpStatus);
        this.description = description;
    }

    public AppError(HttpStatus httpStatus, String description,  String message) {
        this(httpStatus, description);
        if (!Objects.equals(description, message)) {
            this.message = message;
        }
    }

    public AppError(HttpStatus httpStatus, String description, String message, String detail) {
        this(httpStatus, description, message);
        if (!Objects.equals(detail, message)) {
            this.detail = detail;
        }
    }

    public AppError(HttpStatus status, String description, String message, String localizedMessage, String path) {
        this(status, description, message, localizedMessage);
        this.path = path;
    }
}
