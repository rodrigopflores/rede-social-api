package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * CommentResponse class. This class is responsible for representing the response after a comment is
 * made. It contains the comment's ID, the post's ID, the commenter's information, the time of the
 * comment, and the content of the comment.
 */
@Getter
@Setter
public class CommentResponse {

  /** The ID of the comment. This field is optional. */
  private Integer id;

  /** The ID of the post where the comment was made. This field is optional. */
  private Integer postId;

  /** The information of the user who made the comment. This field is optional. */
  private UserStandardResponse commenter;

  /**
   * The time when the comment was made. This field is optional and should follow the format
   * "dd/MM/yyyy HH:mm:ss".
   */
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime time;

  /** The content of the comment. This field is optional. */
  private String content;
}
