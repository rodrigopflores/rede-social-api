package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserSecurityRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  private static final ModelMapper modelMapper = new ModelMapper();

  public User toDomain(CreateUserRequest request) {
    return modelMapper.map(request, User.class);
  }

  public UserStandardResponse toUserStandardResponse(User user) {
    UserStandardResponse response = new UserStandardResponse();
    response.setId(user.getId());
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());
    response.setNickName(user.getNickName());
    response.setEmail(user.getEmail());
    response.setDateOfBirth(user.getDateOfBirth());
    response.setProfilePic(user.getProfilePic());

    return response;
  }

  public UserProfileResponse toUserProfileResponse(User user) {
    UserProfileResponse response = new UserProfileResponse();
    response.setId(user.getId());
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());
    response.setNickName(user.getNickName());
    response.setEmail(user.getEmail());
    response.setDateOfBirth(user.getDateOfBirth());
    response.setProfilePic(user.getProfilePic());

    return response;
  }

  public CreateUserSecurityRequest toCreateUserSecurityRequest(CreateUserRequest request) {
    return modelMapper.map(request, CreateUserSecurityRequest.class);
  }
}
