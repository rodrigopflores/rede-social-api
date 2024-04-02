package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    Assertions.assertEquals(request.getMessage(), result.getMessage());
    Assertions.assertEquals(request.getImage(), result.getImage());
    Assertions.assertEquals(request.isPrivatePost(), result.isPrivatePost());
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

    Mockito.when(userMapper.toUserStandardResponse(post.getUser()))
        .thenReturn(userStandardResponse);

    PostResponse result = mapper.toPostResponse(post);

    Mockito.verify(userMapper).toUserStandardResponse(post.getUser());

    // Assert

    Assertions.assertEquals(post.getId(), result.getId());
    Assertions.assertEquals(post.getMessage(), result.getMessage());
    Assertions.assertEquals(post.getTime(), result.getTime());
    Assertions.assertEquals(post.getImage(), result.getImage());
  }
}
