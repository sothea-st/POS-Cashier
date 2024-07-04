package com.example.pos.connection1.util.exception;

import com.example.pos.connection1.util.exception.customeException.FieldIsRequiredException;
import com.example.pos.connection1.util.exception.customeException.InValidEmail;
import com.example.pos.connection1.util.exception.customeException.JavaDataAlreadyExists;
import com.example.pos.connection1.util.exception.customeException.JavaNotFoundByIdGiven;
import com.example.pos.connection1.util.response.ResponseError;
import com.example.pos.connection1.util.response.ResponseMessage;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The email or password is incorrect");

            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return errorDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<?, ?> requiredField(MethodArgumentNotValidException ex) {
        List<FieldErrorResponse> fieldErrorResponses = new ArrayList<>();
        ex.getFieldErrors().forEach(fieldError -> fieldErrorResponses.add(FieldErrorResponse.builder()
                .field(fieldError.getField())
                .detail(fieldError.getDefaultMessage())
                .build()));

        ErrorResponse<?> errorResponse = ErrorResponse
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .reason(fieldErrorResponses)
                .build();
        return Map.of("error", errorResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<?, ?> handleResponseStatusException(ResponseStatusException e) {
        ErrorResponse<?> errorResponse = ErrorResponse
                .builder()
                .code(e.getStatusCode().value())
                .reason(e.getReason())
                .build();
        return Map.of("error", errorResponse);
    }

    @ExceptionHandler(JavaDataAlreadyExists.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError dataAlreadyExist(JavaDataAlreadyExists ex) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(JavaNotFoundByIdGiven.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError dataNotFoundByIdGiven(JavaNotFoundByIdGiven ex) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(InValidEmail.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError invalidEmail(InValidEmail ex) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(value = FieldIsRequiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError fieldIsRequired(FieldIsRequiredException ex) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("The file must be lower than 2MB"));
    }

}
