package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerFriendRequestRequest {

  @NotNull private Integer friendId;

  @NotNull private boolean accepted;
}
