package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * LoginRequest class. This class is responsible for representing the request to login a user. It
 * contains the user's email and password.
 */
@Getter
@Setter
public class LoginRequest {

  /** The email of the user. This field is mandatory and cannot be empty. */
  @NotEmpty private String email;

  /** The password of the user. This field is mandatory and cannot be empty. */
  @NotEmpty private String password;
}
