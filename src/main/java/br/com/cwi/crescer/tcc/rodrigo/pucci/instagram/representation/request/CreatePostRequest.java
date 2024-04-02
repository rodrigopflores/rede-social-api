package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * CreatePostRequest class. This class is responsible for representing the request to create a post.
 * It contains the message of the post, the image of the post, and a boolean indicating whether the
 * post is private or not.
 */
@Getter
@Setter
public class CreatePostRequest {

  /** The message of the post. This field is mandatory and cannot be empty. */
  @NotEmpty private String message;

  /** The image of the post. This field is optional. */
  private String image;

  /** A boolean indicating whether the post is private or not. This field is mandatory. */
  @NotNull private boolean privatePost;
}
