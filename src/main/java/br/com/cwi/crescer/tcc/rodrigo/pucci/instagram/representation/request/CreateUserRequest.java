package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * CreateUserRequest class. This class is responsible for representing the request to create a user.
 * It contains the user's first name, last name, email, nickname, date of birth, profile picture,
 * and password.
 */
@Getter
@Setter
public class CreateUserRequest {

  /** The user's first name. This field is mandatory and cannot be empty. */
  @NotEmpty private String firstName;

  /** The user's last name. This field is mandatory and cannot be empty. */
  @NotEmpty private String lastName;

  /** The user's email. This field is mandatory and cannot be empty. */
  @NotEmpty private String email;

  /** The user's nickname. This field is optional. */
  private String nickName;

  /**
   * The user's date of birth. This field is mandatory and should follow the format "dd/MM/yyyy".
   */
  @NotNull
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateOfBirth;

  /** The user's profile picture. This field is optional. */
  private String profilePic;

  /**
   * The user's password. This field is mandatory and cannot be empty. Note: The validation regex
   * has been omitted to allow the server to return its message.
   */
  @NotEmpty private String password;
}
