package com.zti.yerbastore;

import com.zti.yerbastore.exception.ForbiddenException;
import com.zti.yerbastore.exception.InternalServerErrorException;
import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.response.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class YerbaStoreExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        log.error("Forbidden exception occurred: ", ex);

        Error error = buildError("Forbidden", ex.getMessage());

        return handleExceptionInternal(ex, error, HttpStatus.FORBIDDEN, request);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        logger.error("Not Found exception occurred: ", ex);

        Error error = buildError("Not Found", ex.getMessage());

        return handleExceptionInternal(ex, error, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {
        logger.error("Internal Server Error exception occurred: ", ex);

        Error error = buildError("Internal Server Error", ex.getMessage());

        return handleExceptionInternal(ex, error, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpStatus status, WebRequest request) {
        if (body == null) {
            logger.error("Unknown exception occurred: ", ex);

            Error error = buildError("Internal Server Error", null);

            return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
        }

        return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    private Error buildError(String reason, String details) {
        return Error.builder()
                .timestamp(Instant.now())
                .reason(reason)
                .details(details)
                .build();
    }
}
