package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.CommentFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * CommentMapperTest class. This class is responsible for testing the CommentMapper class. It uses
 * Mockito for mocking dependencies and JUnit for running the tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentMapperTest {

  /** The CommentMapper object to be tested. */
  @InjectMocks private CommentMapper mapper;

  /** The UserMapper object used for mapping between domain and representation objects. */
  @Mock private UserMapper userMapper;

  /**
   * Tests the toDomain method of the CommentMapper class. It checks if the method correctly maps a
   * CreateCommentRequest object, a User object and a Post object to a Comment object.
   */
  @Test
  public void deveRetornarCommentQuandoInformadoCreateCommentRequestUserPost() {

    // Arrange

    CreateCommentRequest request = CommentFixture.createCommentRequest();
    User commenter = UserFixture.user();
    Post post = PostFixture.post();

    // Act

    final Comment result = mapper.toDomain(request, commenter, post);

    // Assert

    Assertions.assertEquals(request.getContent(), result.getContent());
    Assertions.assertEquals(commenter.getId(), result.getCommenter().getId());
    Assertions.assertEquals(post.getId(), result.getPost().getId());
  }

  /**
   * Tests the toCommentResponse method of the CommentMapper class. It checks if the method
   * correctly maps a Comment object to a CommentResponse object.
   */
  @Test
  public void toCommentResponse() {

    // Arrange

    Comment comment = CommentFixture.comment();
    UserStandardResponse commenter = UserFixture.userStandardResponse();

    // Act

    Mockito.when(userMapper.toUserStandardResponse(comment.getCommenter())).thenReturn(commenter);

    final CommentResponse result = mapper.toCommentResponse(comment);

    Mockito.verify(userMapper).toUserStandardResponse(comment.getCommenter());

    // Assert

    Assertions.assertEquals(comment.getId(), result.getId());
    Assertions.assertEquals(comment.getContent(), result.getContent());
    Assertions.assertEquals(comment.getTime(), result.getTime());
    Assertions.assertEquals(commenter.getId(), result.getCommenter().getId());
    Assertions.assertEquals(comment.getPost().getId(), result.getPostId());
  }
}
