package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import lombok.Getter;
import lombok.Setter;

/**
 * ChangeUserInfoRequest class. This class is responsible for representing the request to change a
 * user's information. It contains the user's first name, last name, nickname, and profile picture.
 */
@Getter
@Setter
public class ChangeUserInfoRequest {

  /** The user's first name. This field is optional. */
  private String firstName;

  /** The user's last name. This field is optional. */
  private String lastName;

  /** The user's nickname. This field is optional. */
  private String nickName;

  /** The user's profile picture. This field is optional. */
  private String profilePic;
}
