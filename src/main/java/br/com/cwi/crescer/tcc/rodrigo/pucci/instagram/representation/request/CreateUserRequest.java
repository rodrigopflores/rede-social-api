package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

  @NotEmpty private String firstName;

  @NotEmpty private String lastName;

  @NotEmpty private String email;

  private String nickName;

  @NotNull
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateOfBirth;

  private String profilePic;

  // Não botei a validação regex para que voltasse a mensagem do servidor
  @NotEmpty private String password;
}
