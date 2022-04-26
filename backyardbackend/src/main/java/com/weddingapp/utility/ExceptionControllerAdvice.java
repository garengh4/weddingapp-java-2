package com.weddingapp.utility;

import java.time.LocalDateTime;

import com.weddingapp.exception.BackyardWeddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @ExceptionHandler(BackyardWeddingException.class)
  public ResponseEntity<ErrorInfo> backyardWeddingExcepitonHandler(BackyardWeddingException exception) {
    ErrorInfo errorInfo = new ErrorInfo();
    errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
    errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
    errorInfo.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
  }

}
