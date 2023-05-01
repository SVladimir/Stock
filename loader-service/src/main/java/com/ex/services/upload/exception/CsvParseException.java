package com.ex.services.upload.exception;

public class CsvParseException extends Exception {
  public CsvParseException(String message) {
    super(message);
  }

  public CsvParseException(String message, Throwable cause) {
    super(message, cause);
  }
}
