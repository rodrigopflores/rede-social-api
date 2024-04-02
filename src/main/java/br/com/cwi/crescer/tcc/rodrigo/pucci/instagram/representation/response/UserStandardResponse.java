package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStandardResponse {

  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String nickName;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateOfBirth;

  private String profilePic;
}
