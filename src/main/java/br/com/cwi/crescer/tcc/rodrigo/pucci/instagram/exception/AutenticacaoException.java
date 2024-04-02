package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception;

/**
 * Custom exception class for authentication-related errors. This class extends the RuntimeException
 * class, so it is an unchecked exception.
 */
public class AutenticacaoException extends RuntimeException {

  /**
   * Constructor for the AutenticacaoException class.
   *
   * @param message The detail message. The detail message is saved for later retrieval by the
   *     Throwable.getMessage() method.
   */
  public AutenticacaoException(String message) {
    super(message);
  }
}
