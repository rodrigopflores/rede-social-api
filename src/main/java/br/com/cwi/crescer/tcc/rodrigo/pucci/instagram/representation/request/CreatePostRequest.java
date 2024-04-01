package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {

    @NotEmpty
    private String message;

    private String image;

    @NotNull
    private boolean privatePost;

}
