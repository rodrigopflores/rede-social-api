package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for business validation-related errors. This class extends the
 * RuntimeException class, so it is an unchecked exception. The ResponseStatus annotation marks this
 * exception with an HTTP status code of BAD_REQUEST.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessValidationException extends RuntimeException {

  /**
   * Constructor for the BusinessValidationException class.
   *
   * @param message The detail message. The detail message is saved for later retrieval by the
   *     Throwable.getMessage() method.
   */
  public BusinessValidationException(String message) {
    super(message);
  }
}
