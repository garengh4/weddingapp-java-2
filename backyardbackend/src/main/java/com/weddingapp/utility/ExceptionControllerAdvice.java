package com.weddingapp.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.weddingapp.exception.BackyardWeddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @Autowired
  Environment environment;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
    ErrorInfo errorInfo = new ErrorInfo();
    errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
    errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorInfo.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // for customized exception. message classpath set in Application.java
  @ExceptionHandler(BackyardWeddingException.class)
  public ResponseEntity<ErrorInfo> backyardWeddingExcepitonHandler(BackyardWeddingException exception) {
    ErrorInfo errorInfo = new ErrorInfo();
    errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
    errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
    errorInfo.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
  }

  // for @pattern validation, message autosets to ValidationMessages.properties
  @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
  public ResponseEntity<ErrorInfo> patternExceptionHandler(Exception exception) {
    ErrorInfo errorInfo = new ErrorInfo();
    String errMsg = "";
    if (exception instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException e1 = (MethodArgumentNotValidException) exception;
      errMsg = e1.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage())
          .collect(Collectors.joining(", "));
    } else {
      ConstraintViolationException e1 = (ConstraintViolationException) exception;
      errMsg = e1.getConstraintViolations().stream().map(e -> e.getMessage())
          .collect(Collectors.joining(", "));
    }
    errorInfo.setErrorMessage(errMsg);
    errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
    errorInfo.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
  }

}
