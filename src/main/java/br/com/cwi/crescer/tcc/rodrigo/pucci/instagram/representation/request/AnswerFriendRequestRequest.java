package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * AnswerFriendRequestRequest class. This class is responsible for representing the request to
 * answer a friend request. It contains the friend's ID and a boolean indicating whether the friend
 * request was accepted or not.
 */
@Getter
@Setter
public class AnswerFriendRequestRequest {

  /** The ID of the friend who sent the request. This field is mandatory. */
  @NotNull private Integer friendId;

  /**
   * A boolean indicating whether the friend request was accepted or not. This field is mandatory.
   */
  @NotNull private boolean accepted;
}
