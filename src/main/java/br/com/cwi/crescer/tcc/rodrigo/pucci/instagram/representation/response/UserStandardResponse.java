package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
