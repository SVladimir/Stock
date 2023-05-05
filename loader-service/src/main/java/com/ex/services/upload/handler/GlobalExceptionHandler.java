package com.ex.services.upload.handler;

import com.ex.services.upload.message.ResponseMessage;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ResponseMessage> handleNoSuchElementException(NoSuchElementException e) {
    String message = e.getMessage();
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseMessage(message));
  }
}