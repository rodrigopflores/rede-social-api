package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * UserProfileResponse class. This class is responsible for representing the response after a user's
 * profile is requested. It contains the user's ID, first name, last name, email, nickname, date of
 * birth, profile picture, number of posts, number of friends, whether the users are friends or not,
 * and whether a friend request has been sent or not.
 */
@Getter
@Setter
public class UserProfileResponse {

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

  /** The number of posts the user has made. This field is optional. */
  private Integer numberOfPosts;

  /** The number of friends the user has. This field is optional. */
  private Integer numberOfFriends;

  /** Whether the users are friends or not. This field is optional. */
  private boolean areFriends;

  /** Whether a friend request has been sent or not. This field is optional. */
  private boolean requestSent;
}
