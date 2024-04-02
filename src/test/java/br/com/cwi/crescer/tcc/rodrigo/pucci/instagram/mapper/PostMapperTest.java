package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * PostMapperTest class. This class is responsible for testing the PostMapper class. It uses Mockito
 * for mocking dependencies and JUnit for running the tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class PostMapperTest {

  /** The PostMapper object to be tested. */
  @InjectMocks private PostMapper mapper;

  /** The UserMapper object used for mapping between domain and representation objects. */
  @Mock private UserMapper userMapper;

  /**
   * Tests the toDomain method of the PostMapper class. It checks if the method correctly maps a
   * CreatePostRequest object to a Post object.
   */
  @Test
  public void deveRetornarPostQuandoInformadoCreatePostRequest() {

    // Arrange

    CreatePostRequest request = PostFixture.createPostRequest();

    // Act

    Post result = mapper.toDomain(request);

    // Assert

    assertEquals(request.getMessage(), result.getMessage());
    assertEquals(request.getImage(), result.getImage());
    assertEquals(request.isPrivatePost(), result.isPrivatePost());
  }

  /**
   * Tests the toPostResponse method of the PostMapper class. It checks if the method correctly maps
   * a Post object to a PostResponse object.
   */
  @Test
  public void deveRetornarPostResponseQuandoInformadoPost() {

    // Arrange

    Post post = PostFixture.post();
    UserStandardResponse userStandardResponse = UserFixture.userStandardResponse();

    // Act

    when(userMapper.toUserStandardResponse(post.getUser())).thenReturn(userStandardResponse);

    PostResponse result = mapper.toPostResponse(post);

    verify(userMapper).toUserStandardResponse(post.getUser());

    // Assert

    assertEquals(post.getId(), result.getId());
    assertEquals(post.getMessage(), result.getMessage());
    assertEquals(post.getTime(), result.getTime());
    assertEquals(post.getImage(), result.getImage());
  }
}
