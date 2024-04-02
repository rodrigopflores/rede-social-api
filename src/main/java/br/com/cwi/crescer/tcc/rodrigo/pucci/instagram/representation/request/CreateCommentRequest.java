package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * CreateCommentRequest class. This class is responsible for representing the request to create a
 * comment. It contains the post's ID and the content of the comment.
 */
@Getter
@Setter
public class CreateCommentRequest {

  /** The ID of the post where the comment will be created. This field is mandatory. */
  @NotNull private Integer postId;

  /** The content of the comment. This field is mandatory and cannot be empty. */
  @NotEmpty private String content;
}
