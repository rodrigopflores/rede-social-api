package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.*;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserFixture {

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

  public static UserStandardResponse userStandardResponse() {
    UserStandardResponse response = new UserStandardResponse();
    response.setId(3);
    response.setNickName("JoaoTestador");
    response.setFirstName("João");
    response.setLastName("Farias");
    response.setEmail("joao@cwi.com.br");
    response.setDateOfBirth(LocalDate.now());
    response.setProfilePic(
        "https://randomwordgenerator.com/img/picture-generator/57e4d04a4950ae14f1dc8460962e33791c3ad6e04e5074417d2d73d3934ac3_640.jpg");

    return response;
  }

  public static UserProfileResponse userProfileResponse() {
    UserProfileResponse response = new UserProfileResponse();
    response.setId(3);
    response.setNickName("JoaoTestador");
    response.setFirstName("João");
    response.setLastName("Farias");
    response.setEmail("joao@cwi.com.br");
    response.setDateOfBirth(LocalDate.now());
    response.setProfilePic(
        "https://randomwordgenerator.com/img/picture-generator/57e4d04a4950ae14f1dc8460962e33791c3ad6e04e5074417d2d73d3934ac3_640.jpg");
    response.setNumberOfPosts(4);
    response.setNumberOfFriends(3);
    response.setAreFriends(true);
    response.setRequestSent(false);

    return response;
  }

  public static CreateUserRequest createUserRequest() {
    CreateUserRequest request = new CreateUserRequest();
    request.setFirstName("João");
    request.setLastName("Farias");
    request.setNickName("JoaoTestador");
    request.setEmail("joao@cwi.com.br");
    request.setDateOfBirth(LocalDate.now());
    request.setPassword("Senha12345678");
    request.setProfilePic(
        "https://randomwordgenerator.com/img/picture-generator/57e4d04a4950ae14f1dc8460962e33791c3ad6e04e5074417d2d73d3934ac3_640.jpg");

    return request;
  }

  public static CreateUserSecurityRequest createUserSecurityRequest() {
    CreateUserSecurityRequest request = new CreateUserSecurityRequest();
    request.setFirstName("João");
    request.setLastName("Farias");
    request.setEmail("joao@cwi.com.br");
    request.setPassword("Senha12345678");

    return request;
  }

  public static ChangeUserInfoRequest changeUserInfoRequest() {
    ChangeUserInfoRequest request = new ChangeUserInfoRequest();
    request.setFirstName("Maria");
    request.setLastName("Silva");
    request.setNickName("MariaTestadora");
    request.setProfilePic(
        "https://randomwordgenerator.com/img/picture-generator/50e2d3444350b10ff3d8992cc12c30771037dbf852547940772c7ed59345_640.jpg");

    return request;
  }

  public static AnswerFriendRequestRequest answerFriendRequestRequest() {
    AnswerFriendRequestRequest request = new AnswerFriendRequestRequest();
    request.setFriendId(2);
    request.setAccepted(true);

    return request;
  }

  public static FriendshipRequest friendshipRequest() {
    FriendshipRequest request = new FriendshipRequest();
    request.setSenderId(3);
    request.setReceiverId(2);

    return request;
  }

  public static LoginRequest loginRequest() {
    LoginRequest request = new LoginRequest();
    request.setEmail("joao@cwi.com.br");
    request.setPassword("Senha12345678");

    return request;
  }
}
