package com.zti.yerbastore;

import com.zti.yerbastore.exception.ForbiddenException;
import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.response.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class YerbaStoreExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        logger.error("Forbidden exception occurred: ", ex);

        Error error = Error.builder()
                .timestamp(Instant.now())
                .reason("Forbidden")
                .details(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, error, HttpHeaders.EMPTY, HttpStatus.FORBIDDEN, request);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        logger.error("NotFound exception occurred: ", ex);

        Error error = Error.builder()
                .timestamp(Instant.now())
                .reason("Not Found")
                .details(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, error, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            logger.error("Unknown exception occurred: ", ex);

            Error error = Error.builder()
                    .timestamp(Instant.now())
                    .reason("Internal Server Error")
                    .build();

            return super.handleExceptionInternal(ex, error, headers, status, request);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
