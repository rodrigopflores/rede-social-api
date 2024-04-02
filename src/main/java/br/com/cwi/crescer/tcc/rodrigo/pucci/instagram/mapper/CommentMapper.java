package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CommentMapper class. This class is responsible for mapping between Comment domain objects and
 * Comment representation objects.
 */
@Component
public class CommentMapper {

  /** ModelMapper object used for object mapping. */
  private static final ModelMapper modelMapper = new ModelMapper();

  /** UserMapper object used for mapping User objects. */
  @Autowired private UserMapper userMapper;

  /**
   * Maps a CreateCommentRequest and User and Post objects to a Comment object.
   *
   * @param request The CreateCommentRequest object containing the data for the new comment.
   * @param commenter The User who made the comment.
   * @param post The Post on which the comment was made.
   * @return The Comment object.
   */
  public Comment toDomain(CreateCommentRequest request, User commenter, Post post) {
    Comment comment = new Comment();
    comment.setContent(request.getContent());
    comment.setCommenter(commenter);
    comment.setPost(post);

    return comment;
  }

  /**
   * Maps a Comment object to a CommentResponse object.
   *
   * @param comment The Comment object.
   * @return The CommentResponse object.
   */
  public CommentResponse toCommentResponse(Comment comment) {
    CommentResponse response = new CommentResponse();
    UserStandardResponse commenter = userMapper.toUserStandardResponse(comment.getCommenter());
    response.setCommenter(commenter);
    response.setId(comment.getId());
    response.setPostId(comment.getPost().getId());
    response.setTime(comment.getTime());
    response.setContent(comment.getContent());

    return response;
  }
}
