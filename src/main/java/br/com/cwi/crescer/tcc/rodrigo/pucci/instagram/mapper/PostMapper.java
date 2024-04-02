package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PostMapper class. This class is responsible for mapping between Post domain objects and Post
 * representation objects.
 */
@Component
public class PostMapper {

  /** ModelMapper object used for object mapping. */
  private static final ModelMapper modelMapper = new ModelMapper();

  /** UserMapper object used for mapping User objects. */
  @Autowired private UserMapper userMapper;

  /**
   * Maps a CreatePostRequest object to a Post object.
   *
   * @param request The CreatePostRequest object containing the data for the new post.
   * @return The Post object.
   */
  public Post toDomain(CreatePostRequest request) {
    Post post = new Post();
    post.setMessage(request.getMessage());
    post.setImage(request.getImage());
    post.setPrivatePost(request.isPrivatePost());

    return post;
  }

  /**
   * Maps a Post object to a PostResponse object.
   *
   * @param post The Post object.
   * @return The PostResponse object.
   */
  public PostResponse toPostResponse(Post post) {
    UserStandardResponse user = userMapper.toUserStandardResponse(post.getUser());

    PostResponse response = new PostResponse();
    response.setId(post.getId());
    response.setMessage(post.getMessage());
    response.setTime(post.getTime());
    response.setImage(post.getImage());
    response.setPrivatePost(post.isPrivatePost());
    response.setUser(user);

    return response;
  }
}
