package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * PostResponse class. This class is responsible for representing the response after a post is made.
 * It contains the post's ID, the user's information, the message, the image, the time of the post,
 * whether the post is private or not, the number of likes, and whether the user liked the post or
 * not.
 */
@Getter
@Setter
public class PostResponse {

  /** The ID of the post. This field is optional. */
  private Integer id;

  /** The information of the user who made the post. This field is optional. */
  private UserStandardResponse user;

  /** The message of the post. This field is optional. */
  private String message;

  /** The image of the post. This field is optional. */
  private String image;

  /**
   * The time when the post was made. This field is optional and should follow the format
   * "dd/MM/yyyy HH:mm:ss".
   */
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime time;

  /** Whether the post is private or not. This field is optional. */
  private boolean privatePost;

  /** The number of likes the post has received. This field is optional. */
  private Integer likes;

  /** Whether the user liked the post or not. This field is optional. */
  private boolean userLiked;
}
