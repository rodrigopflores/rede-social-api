package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    public LoginResponse(String token) {
        this.token = token;
    }

    private String token;
}
