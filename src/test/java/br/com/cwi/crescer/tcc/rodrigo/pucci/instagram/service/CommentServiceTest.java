package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    when(userService.getUser()).thenReturn(commenter);
    when(postService.getValidatedPostById(request.getPostId())).thenReturn(post);
    when(mapper.toDomain(request, commenter, post)).thenReturn(comment);
    when(repository.save(comment)).thenReturn(comment);
    when(mapper.toCommentResponse(comment)).thenReturn(response);

    CommentResponse result = service.createComment(request);

    verify(userService).getUser();
    verify(postService).getValidatedPostById(request.getPostId());
    verify(mapper).toDomain(request, commenter, post);
    verify(repository).save(comment);
    verify(mapper).toCommentResponse(comment);

    // Assert

    assertEquals(response.getId(), result.getId());
  }

  /**
   * Tests the getPostComments method of the CommentService class. It checks if the method correctly
   * retrieves the comments of a post.
   */
  @Test
  public void deveRestornarPageDeCommentResponseQuandoInformarPostIdEPageable() {

    // Arrange

    Integer postId = 2;
    Post post = PostFixture.post();
    Pageable pageable = PageRequest.of(0, 5);
    Comment comment = CommentFixture.comment();
    Page<Comment> commentPage = new PageImpl<>(Collections.singletonList(comment), pageable, 1);
    CommentResponse response = CommentFixture.commentResponse();
    response.setId(comment.getId());

    // Act

    when(postService.getValidatedPostById(postId)).thenReturn(post);
    when(repository.findByPostIdOrderByTimeAsc(postId, pageable)).thenReturn(commentPage);
    when(mapper.toCommentResponse(comment)).thenReturn(response);

    Page<CommentResponse> result = service.getPostComments(postId, pageable);

    verify(postService).getValidatedPostById(postId);
    verify(repository).findByPostIdOrderByTimeAsc(postId, pageable);
    verify(mapper).toCommentResponse(comment);

    // Assert

    assertEquals(comment.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }
}
