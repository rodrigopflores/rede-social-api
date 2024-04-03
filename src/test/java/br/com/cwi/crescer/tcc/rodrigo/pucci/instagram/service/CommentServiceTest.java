package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.CommentFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.CommentMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.CommentRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import java.util.Collections;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * CommentServiceTest class. This class is responsible for testing the CommentService class. It uses
 * Mockito for mocking dependencies and JUnit for running the tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

  /** The CommentService object to be tested. */
  @InjectMocks private CommentService service;

  /** The CommentRepository object used for comment operations. */
  @Mock private CommentRepository repository;

  /** The CommentMapper object used for mapping between domain and representation objects. */
  @Mock private CommentMapper mapper;

  /** The UserService object used for user operations. */
  @Mock private UserService userService;

  /** The PostService object used for post operations. */
  @Mock private PostService postService;

  /**
   * Tests the createComment method of the CommentService class. It checks if the method correctly
   * creates a comment.
   */
  @Test
  public void deveCriarCommentQuandoInformadoCreateCommentRequest() {
    // Arrange

    User commenter = UserFixture.user();
    Post post = PostFixture.post();
    CreateCommentRequest request = CommentFixture.createCommentRequest();
    Comment comment = CommentFixture.comment();
    CommentResponse response = CommentFixture.commentResponse();

    // Act

    Mockito.when(userService.getUser()).thenReturn(commenter);
    Mockito.when(postService.getValidatedPostById(request.getPostId())).thenReturn(post);
    Mockito.when(mapper.toDomain(request, commenter, post)).thenReturn(comment);
    Mockito.when(repository.save(comment)).thenReturn(comment);
    Mockito.when(mapper.toCommentResponse(comment)).thenReturn(response);

    final CommentResponse result = service.createComment(request);

    Mockito.verify(userService).getUser();
    Mockito.verify(postService).getValidatedPostById(request.getPostId());
    Mockito.verify(mapper).toDomain(request, commenter, post);
    Mockito.verify(repository).save(comment);
    Mockito.verify(mapper).toCommentResponse(comment);

    // Assert

    Assertions.assertEquals(response.getId(), result.getId());
  }

  /**
   * Tests the getPostComments method of the CommentService class. It checks if the method correctly
   * retrieves the comments of a post.
   */
  @Test
  public void deveRestornarPageDeCommentResponseQuandoInformarPostIdePageable() {

    // Arrange

    Integer postId = 2;
    Post post = PostFixture.post();
    Pageable pageable = PageRequest.of(0, 5);
    Comment comment = CommentFixture.comment();
    Page<Comment> commentPage = new PageImpl<>(Collections.singletonList(comment), pageable, 1);
    CommentResponse response = CommentFixture.commentResponse();
    response.setId(comment.getId());

    // Act

    Mockito.when(postService.getValidatedPostById(postId)).thenReturn(post);
    Mockito.when(repository.findByPostIdOrderByTimeAsc(postId, pageable)).thenReturn(commentPage);
    Mockito.when(mapper.toCommentResponse(comment)).thenReturn(response);

    final Page<CommentResponse> result = service.getPostComments(postId, pageable);

    Mockito.verify(postService).getValidatedPostById(postId);
    Mockito.verify(repository).findByPostIdOrderByTimeAsc(postId, pageable);
    Mockito.verify(mapper).toCommentResponse(comment);

    // Assert

    Assertions.assertEquals(
        comment.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }
}
