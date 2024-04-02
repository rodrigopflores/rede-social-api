package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentMapperTest {

  @InjectMocks private CommentMapper mapper;

  @Mock private UserMapper userMapper;

  @Test
  public void deveRetornarCommentQuandoInformadoCreateCommentRequestUserEPost() {

    // Arrange

    CreateCommentRequest request = CommentFixture.createCommentRequest();
    User commenter = UserFixture.user();
    Post post = PostFixture.post();

    // Act

    Comment result = mapper.toDomain(request, commenter, post);

    // Assert

    assertEquals(request.getContent(), result.getContent());
    assertEquals(commenter.getId(), result.getCommenter().getId());
    assertEquals(post.getId(), result.getPost().getId());
  }

  @Test
  public void toCommentResponse() {

    // Arrange

    Comment comment = CommentFixture.comment();
    UserStandardResponse commenter = UserFixture.userStandardResponse();

    // Act

    when(userMapper.toUserStandardResponse(comment.getCommenter())).thenReturn(commenter);

    CommentResponse result = mapper.toCommentResponse(comment);

    verify(userMapper).toUserStandardResponse(comment.getCommenter());

    // Assert

    assertEquals(comment.getId(), result.getId());
    assertEquals(comment.getContent(), result.getContent());
    assertEquals(comment.getTime(), result.getTime());
    assertEquals(commenter.getId(), result.getCommenter().getId());
    assertEquals(comment.getPost().getId(), result.getPostId());
  }
}
