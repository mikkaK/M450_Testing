package com.example.demo.core.exception;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class ResponseError implements Serializable {
  private LocalDate timeStamp;
  private Map<String, String> errors;

  public ResponseError() {
  }

  public LocalDate getTimeStamp() {
    return timeStamp;
  }

  public ResponseError setTimeStamp(LocalDate timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public ResponseError setErrors(Map<String, String> errors) {
    this.errors = errors;
    return this;
  }

  public ResponseError build() {
    return this;
  }
}
