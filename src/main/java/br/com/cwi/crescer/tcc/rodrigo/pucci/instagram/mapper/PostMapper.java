package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

  private static final ModelMapper modelMapper = new ModelMapper();
  @Autowired private UserMapper userMapper;

  public Post toDomain(CreatePostRequest request) {
    Post post = new Post();
    post.setMessage(request.getMessage());
    post.setImage(request.getImage());
    post.setPrivatePost(request.isPrivatePost());

    return post;
  }

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
