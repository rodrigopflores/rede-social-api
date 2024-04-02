package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {

  private Integer id;
  private UserStandardResponse user;
  private String message;
  private String image;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime time;

  private boolean privatePost;
  private Integer likes;
  private boolean userLiked;
}
