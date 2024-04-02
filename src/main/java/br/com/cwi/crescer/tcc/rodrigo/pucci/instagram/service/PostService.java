package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception.BusinessValidationException;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.PostMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.PostRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * PostService class. This class is responsible for handling operations related to posts. It is
 * annotated with @Service to indicate that it is a Spring Bean.
 */
@Service
public class PostService {

  /** PostRepository object used for post operations. */
  @Autowired private PostRepository repository;

  /** PostMapper object used for mapping between domain and representation objects. */
  @Autowired private PostMapper mapper;

  /** UserService object used for user operations. */
  @Autowired private UserService userService;

  /**
   * Creates a post.
   *
   * @param request The CreatePostRequest object containing the details of the post to be created.
   * @return The created PostResponse object.
   */
  public PostResponse createPost(CreatePostRequest request) {

    User user = userService.getUser();
    Post post = mapper.toDomain(request);
    post.setUser(user);
    post.setTime(LocalDateTime.now());
    post = repository.save(post);

    PostResponse response = mapper.toPostResponse(post);
    response.setLikes(0);
    response.setUserLiked(false);

    return response;
  }

  /**
   * Retrieves the posts of a user.
   *
   * @param userId The id of the user.
   * @param pageable The pagination information.
   * @return A page of PostResponse objects.
   */
  public Page<PostResponse> getUserPosts(Integer userId, Pageable pageable) {
    User user = userService.getUser();
    User postsOwner = userService.getUserById(userId);
    boolean hasAccess = user.equals(postsOwner) || user.getFriends().contains(postsOwner);

    Page<Post> postsPage;

    if (hasAccess) {
      postsPage = repository.findByUserIdOrderByTimeDesc(postsOwner.getId(), pageable);
    } else {
      postsPage =
          repository.findByUserIdAndPrivatePostFalseOrderByTimeDesc(postsOwner.getId(), pageable);
    }
    List<PostResponse> postResponseList =
        postsPage
            .get()
            .map(
                post -> {
                  PostResponse response = mapper.toPostResponse(post);
                  response.setLikes(post.getLikes().size());
                  boolean userLiked = post.getLikes().contains(user);
                  response.setUserLiked(userLiked);
                  return response;
                })
            .collect(Collectors.toList());

    return new PageImpl<>(postResponseList, pageable, postsPage.getTotalElements());
  }

  /**
   * Retrieves a post by its id.
   *
   * @param id The id of the post.
   * @return The Post object.
   */
  public Post getValidatedPostById(Integer id) {
    Post post = repository.findById(id).orElse(null);
    if (post == null) {
      throw new BusinessValidationException("Post not found");
    }
    return post;
  }

  /**
   * Likes a post.
   *
   * @param postId The id of the post.
   */
  public void likePost(Integer postId) {
    User user = userService.getUser();
    Post post = getValidatedPostById(postId);
    List<User> likes = post.getLikes();
    if (likes.contains(user)) {
      likes.remove(user);
    } else {
      likes.add(user);
    }
    repository.save(post);
  }

  /**
   * Retrieves the feed of a user.
   *
   * @param pageable The pagination information.
   * @return A page of PostResponse objects.
   */
  public Page<PostResponse> getUserFeed(Pageable pageable) {
    User user = userService.getUser();
    Page<Post> feedPage =
        repository.findDistinctByUserFriendsIdOrUserIdOrderByTimeDesc(
            user.getId(), user.getId(), pageable);
    List<PostResponse> feedList =
        feedPage
            .get()
            .map(
                post -> {
                  PostResponse response = mapper.toPostResponse(post);
                  response.setLikes(post.getLikes().size());
                  boolean userLiked = post.getLikes().contains(user);
                  response.setUserLiked(userLiked);
                  return response;
                })
            .collect(Collectors.toList());
    return new PageImpl<>(feedList, pageable, feedPage.getTotalElements());
  }
}
