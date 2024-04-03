package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.CommentMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.CommentRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * CommentService class. This class is responsible for handling operations related to comments. It
 * is annotated with @Service to indicate that it is a Spring Bean.
 */
@Service
public class CommentService {

  /** CommentRepository object used for comment operations. */
  @Autowired private CommentRepository repository;

  /** CommentMapper object used for mapping between domain and representation objects. */
  @Autowired private CommentMapper mapper;

  /** UserService object used for user operations. */
  @Autowired private UserService userService;

  /** PostService object used for post operations. */
  @Autowired private PostService postService;

  /**
   * Creates a comment.
   *
   * @param request The CreateCommentRequest object containing the details of the comment to be
   *     created.
   * @return The created CommentResponse object.
   */
  public CommentResponse createComment(CreateCommentRequest request) {
    User commenter = userService.getUser();
    Post post = postService.getValidatedPostById(request.getPostId());

    Comment comment = mapper.toDomain(request, commenter, post);
    comment.setTime(LocalDateTime.now());
    comment = repository.save(comment);

    return mapper.toCommentResponse(comment);
  }

  /**
   * Retrieves the comments of a post.
   *
   * @param postId The id of the post.
   * @param pageable The pagination information.
   * @return A page of CommentResponse objects.
   */
  public Page<CommentResponse> getPostComments(Integer postId, Pageable pageable) {
    Post post = postService.getValidatedPostById(postId);

    Page<Comment> commentPage = repository.findByPostIdOrderByTimeAsc(postId, pageable);
    List<CommentResponse> commentResponseList =
        commentPage
            .get()
            .map(comment -> mapper.toCommentResponse(comment))
            .collect(Collectors.toList());

    return new PageImpl<>(commentResponseList, pageable, commentPage.getTotalElements());
  }
}
