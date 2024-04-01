package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class CreateUserSecurityRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> roles = Collections.singletonList("ROLE_USER");
}
