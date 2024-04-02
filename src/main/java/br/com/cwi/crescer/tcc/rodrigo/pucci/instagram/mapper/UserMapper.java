package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserSecurityRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * UserMapper class. This class is responsible for mapping between User domain objects and User
 * representation objects.
 */
@Component
public class UserMapper {

  /** ModelMapper object used for object mapping. */
  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * Maps a CreateUserRequest object to a User object.
   *
   * @param request The CreateUserRequest object containing the data for the new user.
   * @return The User object.
   */
  public User toDomain(CreateUserRequest request) {
    return modelMapper.map(request, User.class);
  }

  /**
   * Maps a User object to a UserStandardResponse object.
   *
   * @param user The User object.
   * @return The UserStandardResponse object.
   */
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

  /**
   * Maps a User object to a UserProfileResponse object.
   *
   * @param user The User object.
   * @return The UserProfileResponse object.
   */
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

  /**
   * Maps a CreateUserRequest object to a CreateUserSecurityRequest object.
   *
   * @param request The CreateUserRequest object.
   * @return The CreateUserSecurityRequest object.
   */
  public CreateUserSecurityRequest toCreateUserSecurityRequest(CreateUserRequest request) {
    return modelMapper.map(request, CreateUserSecurityRequest.class);
  }
}
