package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

  @InjectMocks private PostService service;

  @Mock private PostRepository repository;

  @Mock private PostMapper mapper;

  @Mock private UserService userService;

  @Test
  public void deveCriarPostComSucessoQuandoInformadoCreatePostRequest() {

    // Arrange

    CreatePostRequest request = PostFixture.createPostRequest();
    User user = UserFixture.user();
    Post post = PostFixture.post();
    post.setUser(user);
    PostResponse response = PostFixture.postResponse();

    // Act

    when(userService.getUser()).thenReturn(user);
    when(mapper.toDomain(request)).thenReturn(post);
    when(repository.save(post)).thenReturn(post);
    when(mapper.toPostResponse(post)).thenReturn(response);

    PostResponse result = service.createPost(request);

    verify(userService).getUser();
    verify(repository).save(post);
    verify(mapper).toPostResponse(post);

    // Assert

    assertEquals(response.getId(), result.getId());
  }

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

    when(userService.getUser()).thenReturn(user);
    when(userService.getUserById(userId)).thenReturn(postsOwner);
    when(repository.findByUserIdOrderByTimeDesc(postsOwner.getId(), pageable))
        .thenReturn(postsPage);
    when(mapper.toPostResponse(post)).thenReturn(response);

    Page<PostResponse> result = service.getUserPosts(userId, pageable);

    verify(userService).getUser();
    verify(userService).getUserById(userId);
    verify(repository).findByUserIdOrderByTimeDesc(postsOwner.getId(), pageable);
    verify(mapper).toPostResponse(post);

    // Assert

    assertEquals(response.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }

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

    when(userService.getUser()).thenReturn(user);
    when(userService.getUserById(userId)).thenReturn(postsOwner);
    when(repository.findByUserIdAndPrivatePostFalseOrderByTimeDesc(postsOwner.getId(), pageable))
        .thenReturn(postsPage);
    when(mapper.toPostResponse(post)).thenReturn(response);

    Page<PostResponse> result = service.getUserPosts(userId, pageable);

    verify(userService).getUser();
    verify(userService).getUserById(userId);
    verify(repository).findByUserIdAndPrivatePostFalseOrderByTimeDesc(postsOwner.getId(), pageable);
    verify(mapper).toPostResponse(post);

    // Assert

    assertEquals(response.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }

  @Test
  public void deveRetornarPostQuandoInformadoId() {

    // Arrange

    Post post = PostFixture.post();
    Integer id = 2;

    // Act

    when(repository.findById(id)).thenReturn(Optional.of(post));

    Post result = service.getValidatedPostById(id);

    verify(repository).findById(id);

    // Assert

    assertEquals(id, result.getId());
  }

  @Test(expected = BusinessValidationException.class)
  public void deveLancarExceptionQuandoPostNaoForEncontrado() {

    // Arrange

    Post post = PostFixture.post();
    Integer id = 2;

    // Act

    when(repository.findById(id)).thenReturn(Optional.empty());

    Post result = service.getValidatedPostById(id);

    verify(repository).findById(id);
  }

  @Test
  public void deveCurtirPostComSucesso() {

    // Arrange

    User user = UserFixture.user();
    Post post = PostFixture.post();
    Integer postId = 2;

    // Act

    when(userService.getUser()).thenReturn(user);
    when(repository.findById(postId)).thenReturn(Optional.of(post));

    service.likePost(postId);

    verify(userService).getUser();
    verify(repository).findById(postId);
    verify(repository).save(post);
  }

  @Test
  public void deveDescurtirPostComSucesso() {

    // Arrange

    User user = UserFixture.user();
    Post post = PostFixture.post();
    post.getLikes().add(user);
    Integer postId = 2;

    // Act

    when(userService.getUser()).thenReturn(user);
    when(repository.findById(postId)).thenReturn(Optional.of(post));

    service.likePost(postId);

    verify(userService).getUser();
    verify(repository).findById(postId);
    verify(repository).save(post);
  }

  @Test
  public void deveRetornarPageDePostResponseQuandoInformadoPageable() {

    // Arrange

    User user = UserFixture.user();
    Post post = PostFixture.post();
    Pageable pageable = PageRequest.of(0, 5);
    Page<Post> feedPage = new PageImpl<>(Collections.singletonList(post), pageable, 1);
    PostResponse response = PostFixture.postResponse();

    // Act

    when(userService.getUser()).thenReturn(user);
    when(repository.findDistinctByUserFriendsIdOrUserIdOrderByTimeDesc(
            user.getId(), user.getId(), pageable))
        .thenReturn(feedPage);
    when(mapper.toPostResponse(post)).thenReturn(response);

    Page<PostResponse> result = service.getUserFeed(pageable);

    verify(userService).getUser();
    verify(repository)
        .findDistinctByUserFriendsIdOrUserIdOrderByTimeDesc(user.getId(), user.getId(), pageable);
    verify(mapper).toPostResponse(post);

    // Assert

    assertEquals(post.getId(), result.get().collect(Collectors.toList()).get(0).getId());
  }
}
