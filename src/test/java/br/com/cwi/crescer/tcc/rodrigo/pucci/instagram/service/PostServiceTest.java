package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception.BusinessValidationException;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.PostMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.PostRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import java.util.Collections;
import java.util.Optional;
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
 * PostServiceTest class. This class is responsible for testing the PostService class. It uses
 * Mockito for mocking dependencies and JUnit for running the tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

  /** The PostService object to be tested. */
  @InjectMocks private PostService service;

  /** The PostRepository object used for post operations. */
  @Mock private PostRepository repository;

  /** The PostMapper object used for mapping between domain and representation objects. */
  @Mock private PostMapper mapper;

  /** The UserService object used for user operations. */
  @Mock private UserService userService;

  /**
   * Tests the createPost method of the PostService class. It checks if the method correctly creates
   * a post.
   */
  @Test
  public void deveCriarPostComSucessoQuandoInformadoCreatePostRequest() {
    // Arrange

    CreatePostRequest request = PostFixture.createPostRequest();
    User user = UserFixture.user();
    Post post = PostFixture.post();
    post.setUser(user);
    PostResponse response = PostFixture.postResponse();

    // Act

    Mockito.when(userService.getUser()).thenReturn(user);
    Mockito.when(mapper.toDomain(request)).thenReturn(post);
    Mockito.when(repository.save(post)).thenReturn(post);
    Mockito.when(mapper.toPostResponse(post)).thenReturn(response);

    PostResponse result = service.createPost(request);

    Mockito.verify(userService).getUser();
    Mockito.verify(repository).save(post);
    Mockito.verify(mapper).toPostResponse(post);

    // Assert

    Assertions.assertEquals(response.getId(), result.getId());
  }

  /**
   * Tests the getUserPosts method of the PostService class. It checks if the method correctly
   * retrieves the posts of a user.
   */
  @Test
  public void
      deveRetornarPageDePostResponsePublicosEPrivadosDeAmigoQuandoInformarUserIdEPageable() {

    // Arrange

    User user = UserFixture.user();
    User postsOwner = UserFixture.user();
    Integer userId = 6;
    postsOwner.setId(userId);
    postsOwner.setFirstName("Mario");
    postsOwner.setEmail("friend@email.com");
    user.getFriends().add(postsOwner);
    Post post = PostFixture.post();
    Pageable pageable = PageRequest.of(0, 5);
    Page<Post> postsPage = new PageImpl<>(Collections.singletonList(post), pageable, 1);
    PostResponse response = PostFixture.postResponse();

    // Act

    Mockito.when(userService.getUser()).thenReturn(user);
    Mockito.when(userService.getUserById(userId)).thenReturn(postsOwner);
    Mockito.when(repository.findByUserIdOrderByTimeDesc(postsOwner.getId(), pageable))
        .thenReturn(postsPage);
    Mockito.when(mapper.toPostResponse(post)).thenReturn(response);

    Page<PostResponse> result = service.getUserPosts(userId, pageable);

    Mockito.verify(userService).getUser();
    Mockito.verify(userService).getUserById(userId);
    Mockito.verify(repository).findByUserIdOrderByTimeDesc(postsOwner.getId(), pageable);
    Mockito.verify(mapper).toPostResponse(post);

    // Assert

    Assertions.assertEquals(
        response.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }

  /**
   * Tests the getUserPosts method of the PostService class. It checks if the method correctly
   * retrieves the public posts of a user who is not a friend.
   */
  @Test
  public void
      deveRetornarPageDePostResponseApenasPublicosDeUserQuandoNaoForAmigoEInformarUserIdEPageable() {

    // Arrange

    User user = UserFixture.user();
    User postsOwner = UserFixture.user();
    Integer userId = 6;
    postsOwner.setId(userId);
    postsOwner.setFirstName("Mario");
    postsOwner.setEmail("friend@email.com");
    Post post = PostFixture.post();
    Pageable pageable = PageRequest.of(0, 5);
    Page<Post> postsPage = new PageImpl<>(Collections.singletonList(post), pageable, 1);
    PostResponse response = PostFixture.postResponse();

    // Act

    Mockito.when(userService.getUser()).thenReturn(user);
    Mockito.when(userService.getUserById(userId)).thenReturn(postsOwner);
    Mockito.when(
            repository.findByUserIdAndPrivatePostFalseOrderByTimeDesc(postsOwner.getId(), pageable))
        .thenReturn(postsPage);
    Mockito.when(mapper.toPostResponse(post)).thenReturn(response);

    Page<PostResponse> result = service.getUserPosts(userId, pageable);

    Mockito.verify(userService).getUser();
    Mockito.verify(userService).getUserById(userId);
    Mockito.verify(repository)
        .findByUserIdAndPrivatePostFalseOrderByTimeDesc(postsOwner.getId(), pageable);
    Mockito.verify(mapper).toPostResponse(post);

    // Assert

    Assertions.assertEquals(
        response.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }

  /**
   * Tests the getValidatedPostById method of the PostService class. It checks if the method
   * correctly retrieves a post by its ID.
   */
  @Test
  public void deveRetornarPostQuandoInformadoId() {

    // Arrange

    Post post = PostFixture.post();
    Integer id = 2;

    // Act

    Mockito.when(repository.findById(id)).thenReturn(Optional.of(post));

    Post result = service.getValidatedPostById(id);

    Mockito.verify(repository).findById(id);

    // Assert

    Assertions.assertEquals(id, result.getId());
  }

  /**
   * Tests the getValidatedPostById method of the PostService class. It checks if the method throws
   * an exception Mockito.when the post is not found.
   */
  @Test(expected = BusinessValidationException.class)
  public void deveLancarExceptionQuandoPostNaoForEncontrado() {

    // Arrange

    Post post = PostFixture.post();
    Integer id = 2;

    // Act

    Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

    Post result = service.getValidatedPostById(id);

    Mockito.verify(repository).findById(id);
  }

  /**
   * Tests the likePost method of the PostService class. It checks if the method correctly likes a
   * post.
   */
  @Test
  public void deveCurtirPostComSucesso() {

    // Arrange

    User user = UserFixture.user();
    Post post = PostFixture.post();
    Integer postId = 2;

    // Act

    Mockito.when(userService.getUser()).thenReturn(user);
    Mockito.when(repository.findById(postId)).thenReturn(Optional.of(post));

    service.likePost(postId);

    Mockito.verify(userService).getUser();
    Mockito.verify(repository).findById(postId);
    Mockito.verify(repository).save(post);
  }

  /**
   * Tests the likePost method of the PostService class. It checks if the method correctly unlikes a
   * post.
   */
  @Test
  public void deveDescurtirPostComSucesso() {

    // Arrange

    User user = UserFixture.user();
    Post post = PostFixture.post();
    post.getLikes().add(user);
    Integer postId = 2;

    // Act

    Mockito.when(userService.getUser()).thenReturn(user);
    Mockito.when(repository.findById(postId)).thenReturn(Optional.of(post));

    service.likePost(postId);

    Mockito.verify(userService).getUser();
    Mockito.verify(repository).findById(postId);
    Mockito.verify(repository).save(post);
  }

  /**
   * Tests the getUserFeed method of the PostService class. It checks if the method correctly
   * retrieves the user's feed.
   */
  @Test
  public void deveRetornarPageDePostResponseQuandoInformadoPageable() {

    // Arrange

    User user = UserFixture.user();
    Post post = PostFixture.post();
    Pageable pageable = PageRequest.of(0, 5);
    Page<Post> feedPage = new PageImpl<>(Collections.singletonList(post), pageable, 1);
    PostResponse response = PostFixture.postResponse();

    // Act

    Mockito.when(userService.getUser()).thenReturn(user);
    Mockito.when(
            repository.findDistinctByUserFriendsIdOrUserIdOrderByTimeDesc(
                user.getId(), user.getId(), pageable))
        .thenReturn(feedPage);
    Mockito.when(mapper.toPostResponse(post)).thenReturn(response);

    Page<PostResponse> result = service.getUserFeed(pageable);

    Mockito.verify(userService).getUser();
    Mockito.verify(repository)
        .findDistinctByUserFriendsIdOrUserIdOrderByTimeDesc(user.getId(), user.getId(), pageable);
    Mockito.verify(mapper).toPostResponse(post);

    // Assert

    Assertions.assertEquals(post.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }
}
