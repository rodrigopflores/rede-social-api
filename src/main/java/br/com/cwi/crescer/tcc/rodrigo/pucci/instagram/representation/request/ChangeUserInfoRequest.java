package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeUserInfoRequest {

  private String firstName;
  private String lastName;
  private String nickName;
  private String profilePic;
}
