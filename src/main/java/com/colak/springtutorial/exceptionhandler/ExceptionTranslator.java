package com.colak.springtutorial.exceptionhandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @Builder
    private record InvalidatedParams(String cause, String attribute) {
    }

    // HTTP/1.1 403 Forbidden
    // Content-Type: application/problem+json
    // Content-Language: en
    //
    // {
    //   "status": 403,
    //   "detail": "Your current balance is 30, but that costs 50.",
    //   "type": "https://example.com/errors/out-of-credit",
    //   "title": "You do not have enough credit.",
    //   "instance": "/account/12345",
    //   "traceId": "acbd0001-7b8e-4e81-9d6b-3e34ce7c9a9e", // optional field
    //   "balance": 30, // additional information
    //   "cost": 50 // additional information
    // }

    // MethodArgumentNotValidException is thrown when validation fails on a request body (@RequestBody) or a request parameter annotated with @Valid in a Spring MVC controller.
    // ConstraintViolationException is thrown when validation fails on method parameters annotated with @Validated in a Spring service or controller.
    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> errors = constraintViolationException.getConstraintViolations();
        List<InvalidatedParams> validationResponse = errors.stream()
                .map(err -> InvalidatedParams.builder()
                        .cause(err.getMessage())
                        .attribute(err.getPropertyPath().toString())
                        .build()
                ).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request validation failed");
        // problemDetail.setType();
        // problemDetail.setInstance();

        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("invalidParams", validationResponse);
        return problemDetail;
    }
}