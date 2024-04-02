package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * PostFixture class. This class is responsible for creating dummy data for testing. It creates
 * instances of Post, CreatePostRequest, and PostResponse.
 */
public class PostFixture {

  /**
   * Creates a Post object with predefined data.
   *
   * @return a Post object.
   */
  public static Post post() {
    User user = UserFixture.user();
    Post post = new Post();
    post.setId(2);
    post.setUser(user);
    post.setImage("image");
    post.setMessage("Novo post");
    post.setTime(LocalDateTime.of(2021, 10, 17, 10, 45, 31));
    post.setPrivatePost(false);
    post.setLikes(new ArrayList<>());

    return post;
  }

  /**
   * Creates a CreatePostRequest object with predefined data.
   *
   * @return a CreatePostRequest object.
   */
  public static CreatePostRequest createPostRequest() {
    CreatePostRequest request = new CreatePostRequest();
    request.setImage("image");
    request.setMessage("Novo post");
    request.setPrivatePost(false);

    return request;
  }

  /**
   * Creates a PostResponse object with predefined data.
   *
   * @return a PostResponse object.
   */
  public static PostResponse postResponse() {
    UserStandardResponse user = UserFixture.userStandardResponse();

    PostResponse response = new PostResponse();
    response.setUser(user);
    response.setId(2);
    response.setLikes(5);
    response.setTime(LocalDateTime.of(2021, 10, 17, 10, 45, 31));
    response.setImage("image");
    response.setMessage("Novo post");
    response.setPrivatePost(false);
    response.setUserLiked(false);

    return response;
  }
}
