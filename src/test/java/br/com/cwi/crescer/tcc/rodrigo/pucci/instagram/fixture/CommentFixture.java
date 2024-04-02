package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import java.time.LocalDateTime;

public class CommentFixture {

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

  public static CreateCommentRequest createCommentRequest() {
    CreateCommentRequest request = new CreateCommentRequest();
    request.setPostId(2);
    request.setContent("Esse é o meu comentário");

    return request;
  }

  public static CommentResponse commentResponse() {
    CommentResponse response = new CommentResponse();
    response.setPostId(2);
    response.setContent("Esse é o meu comentário");
    response.setTime(LocalDateTime.now());
    response.setId(3);

    return response;
  }
}
