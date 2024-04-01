package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    private Integer id;
    private Integer postId;
    private UserStandardResponse commenter;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime time;
    private String content;
}
