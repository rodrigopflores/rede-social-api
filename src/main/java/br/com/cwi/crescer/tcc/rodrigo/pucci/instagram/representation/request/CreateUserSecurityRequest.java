package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * CreateUserSecurityRequest class. This class is responsible for representing the request to create
 * a user with security roles. It contains the user's first name, last name, email, password, and a
 * list of roles.
 */
@Getter
@Setter
public class CreateUserSecurityRequest {

  /** The user's first name. This field is optional. */
  private String firstName;

  /** The user's last name. This field is optional. */
  private String lastName;

  /** The user's email. This field is optional. */
  private String email;

  /** The user's password. This field is optional. */
  private String password;

  /** The list of roles for the user. By default, a user is assigned the "ROLE_USER" role. */
  private List<String> roles = Collections.singletonList("ROLE_USER");
}
