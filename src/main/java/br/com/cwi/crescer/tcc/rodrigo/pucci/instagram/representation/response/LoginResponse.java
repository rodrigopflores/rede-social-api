package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import lombok.Getter;
import lombok.Setter;

/**
 * LoginResponse class. This class is responsible for representing the response after a user logs
 * in. It contains the token generated for the user.
 */
@Getter
@Setter
public class LoginResponse {

  /** The token generated for the user. This field is mandatory. */
  private String token;

  /**
   * Constructor for the LoginResponse class. It initializes the token with the provided value.
   *
   * @param token The token generated for the user.
   */
  public LoginResponse(String token) {
    this.token = token;
  }
}
