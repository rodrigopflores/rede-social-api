package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.AnswerFriendRequestRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.ChangeUserInfoRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserSecurityRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.FriendshipRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.LoginRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * UserFixture class. This class is responsible for creating dummy data for testing. It creates
 * instances of User, CreateUserRequest, CreateUserSecurityRequest, ChangeUserInfoRequest,
 * AnswerFriendRequestRequest, FriendshipRequest, LoginRequest, UserStandardResponse, and
 * UserProfileResponse.
 */
public class UserFixture {
  /**
   * Creates a User object with predefined data.
   *
   * @return a User object.
   */
  public static User user() {
    User user = new User();
    user.setId(3);
    user.setNickName("JoaoTestador");
    user.setFirstName("João");
    user.setLastName("Farias");
    user.setEmail("joao@cwi.com.br");
    user.setDateOfBirth(LocalDate.now());
    user.setFriends(new ArrayList<>());
    user.setFriendOf(new ArrayList<>());
    user.setFriendRequestsSent(new ArrayList<>());
    user.setFriendRequestsReceived(new ArrayList<>());
    user.setLikedPosts(new ArrayList<>());
    user.setPosts(new ArrayList<>());

    return user;
  }

  /**
   * Creates a UserStandardResponse object with predefined data.
   *
   * @return a UserStandardResponse object.
   */
  public static UserStandardResponse userStandardResponse() {
    UserStandardResponse response = new UserStandardResponse();
    response.setId(3);
    response.setNickName("JoaoTestador");
    response.setFirstName("João");
    response.setLastName("Farias");
    response.setEmail("joao@cwi.com.br");
    response.setDateOfBirth(LocalDate.now());
    response.setProfilePic("image");

    return response;
  }

  /**
   * Creates a UserProfileResponse object with predefined data.
   *
   * @return a UserProfileResponse object.
   */
  public static UserProfileResponse userProfileResponse() {
    UserProfileResponse response = new UserProfileResponse();
    response.setId(3);
    response.setNickName("JoaoTestador");
    response.setFirstName("João");
    response.setLastName("Farias");
    response.setEmail("joao@cwi.com.br");
    response.setDateOfBirth(LocalDate.now());
    response.setProfilePic("image");
    response.setNumberOfPosts(4);
    response.setNumberOfFriends(3);
    response.setAreFriends(true);
    response.setRequestSent(false);

    return response;
  }

  /**
   * Creates a CreateUserRequest object with predefined data.
   *
   * @return a CreateUserRequest object.
   */
  public static CreateUserRequest createUserRequest() {
    CreateUserRequest request = new CreateUserRequest();
    request.setFirstName("João");
    request.setLastName("Farias");
    request.setNickName("JoaoTestador");
    request.setEmail("joao@cwi.com.br");
    request.setDateOfBirth(LocalDate.now());
    request.setPassword("Senha12345678");
    request.setProfilePic("image");

    return request;
  }

  /**
   * Creates a CreateUserSecurityRequest object with predefined data.
   *
   * @return a CreateUserSecurityRequest object.
   */
  public static CreateUserSecurityRequest createUserSecurityRequest() {
    CreateUserSecurityRequest request = new CreateUserSecurityRequest();
    request.setFirstName("João");
    request.setLastName("Farias");
    request.setEmail("joao@cwi.com.br");
    request.setPassword("Senha12345678");

    return request;
  }

  /**
   * Creates a ChangeUserInfoRequest object with predefined data.
   *
   * @return a ChangeUserInfoRequest object.
   */
  public static ChangeUserInfoRequest changeUserInfoRequest() {
    ChangeUserInfoRequest request = new ChangeUserInfoRequest();
    request.setFirstName("Maria");
    request.setLastName("Silva");
    request.setNickName("MariaTestadora");
    request.setProfilePic("image");

    return request;
  }

  /**
   * Creates an AnswerFriendRequestRequest object with predefined data.
   *
   * @return an AnswerFriendRequestRequest object.
   */
  public static AnswerFriendRequestRequest answerFriendRequestRequest() {
    AnswerFriendRequestRequest request = new AnswerFriendRequestRequest();
    request.setFriendId(2);
    request.setAccepted(true);

    return request;
  }

  /**
   * Creates a FriendshipRequest object with predefined data.
   *
   * @return a FriendshipRequest object.
   */
  public static FriendshipRequest friendshipRequest() {
    FriendshipRequest request = new FriendshipRequest();
    request.setSenderId(3);
    request.setReceiverId(2);

    return request;
  }

  /**
   * Creates a LoginRequest object with predefined data.
   *
   * @return a LoginRequest object.
   */
  public static LoginRequest loginRequest() {
    LoginRequest request = new LoginRequest();
    request.setEmail("joao@cwi.com.br");
    request.setPassword("12345678");

    return request;
  }
}
