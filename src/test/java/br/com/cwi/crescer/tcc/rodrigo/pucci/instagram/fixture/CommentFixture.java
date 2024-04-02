package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import java.time.LocalDateTime;

/**
 * CommentFixture class. This class is used to create dummy Comment, CreateCommentRequest and
 * CommentResponse objects for testing purposes.
 */
public class CommentFixture {

  /**
   * Creates a dummy Comment object.
   *
   * @return The created Comment object.
   */
  public static Comment comment() {
    Comment comment = new Comment();
    User commenter = UserFixture.user();
    comment.setCommenter(commenter);
    Post post = PostFixture.post();
    comment.setPost(post);
    comment.setContent("Esse é o meu comentário");
    comment.setId(3);
    comment.setTime(LocalDateTime.now());

    return comment;
  }

  /**
   * Creates a dummy CreateCommentRequest object.
   *
   * @return The created CreateCommentRequest object.
   */
  public static CreateCommentRequest createCommentRequest() {
    CreateCommentRequest request = new CreateCommentRequest();
    request.setPostId(2);
    request.setContent("Esse é o meu comentário");

    return request;
  }

  /**
   * Creates a dummy CommentResponse object.
   *
   * @return The created CommentResponse object.
   */
  public static CommentResponse commentResponse() {
    CommentResponse response = new CommentResponse();
    response.setPostId(2);
    response.setContent("Esse é o meu comentário");
    response.setTime(LocalDateTime.now());
    response.setId(3);

    return response;
  }
}
