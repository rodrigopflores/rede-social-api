package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * UserStandardResponse class. This class is responsible for representing the standard response for
 * a user. It contains the user's ID, first name, last name, email, nickname, date of birth, and
 * profile picture.
 */
@Getter
@Setter
public class UserStandardResponse {

  /** The ID of the user. This field is optional. */
  private Integer id;

  /** The first name of the user. This field is optional. */
  private String firstName;

  /** The last name of the user. This field is optional. */
  private String lastName;

  /** The email of the user. This field is optional. */
  private String email;

  /** The nickname of the user. This field is optional. */
  private String nickName;

  /**
   * The date of birth of the user. This field is optional and should follow the format
   * "dd/MM/yyyy".
   */
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateOfBirth;

  /** The profile picture of the user. This field is optional. */
  private String profilePic;
}
