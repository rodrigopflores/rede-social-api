package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * FriendshipRequest class. This class is responsible for representing the request to create a
 * friendship between two users. It contains the sender's ID and the receiver's ID.
 */
@Getter
@Setter
public class FriendshipRequest {

  /** The ID of the user sending the friendship request. This field is mandatory. */
  @NotNull private Integer senderId;

  /** The ID of the user receiving the friendship request. This field is mandatory. */
  @NotNull private Integer receiverId;
}
