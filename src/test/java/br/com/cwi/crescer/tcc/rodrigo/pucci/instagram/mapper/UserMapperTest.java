package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserSecurityRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/** UserMapperTest class. This class is responsible for testing the UserMapper class. */
public class UserMapperTest {

  /** The UserMapper object to be tested. */
  private final UserMapper mapper = new UserMapper();

  /**
   * Tests the toDomain method of the UserMapper class. It checks if the method correctly maps a
   * CreateUserRequest object to a User object.
   */
  @Test
  public void deveRetornarUmUserQuandoInformadoUmCreateUserRequest() {

    // Arrange

    final CreateUserRequest request = UserFixture.createUserRequest();

    // Act

    final User result = mapper.toDomain(request);

    // Assert

    Assertions.assertEquals(request.getNickName(), result.getNickName());
    Assertions.assertEquals(request.getFirstName(), result.getFirstName());
    Assertions.assertEquals(request.getLastName(), result.getLastName());
    Assertions.assertEquals(request.getEmail(), result.getEmail());
    Assertions.assertEquals(request.getProfilePic(), result.getProfilePic());
    Assertions.assertEquals(request.getDateOfBirth(), result.getDateOfBirth());
  }

  /**
   * Tests the toUserStandardResponse method of the UserMapper class. It checks if the method
   * correctly maps a User object to a UserStandardResponse object.
   */
  @Test
  public void deveRetornarUmUserStandardResponseQuandoInformadoUmUser() {

    // Arrange

    final User user = UserFixture.user();

    // Act

    final UserStandardResponse result = mapper.toUserStandardResponse(user);

    // Assert

    Assertions.assertEquals(user.getId(), result.getId());
    Assertions.assertEquals(user.getFirstName(), result.getFirstName());
    Assertions.assertEquals(user.getLastName(), result.getLastName());
    Assertions.assertEquals(user.getNickName(), result.getNickName());
    Assertions.assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
    Assertions.assertEquals(user.getEmail(), result.getEmail());
    Assertions.assertEquals(user.getProfilePic(), result.getProfilePic());
  }

  /**
   * Tests the toUserProfileResponse method of the UserMapper class. It checks if the method
   * correctly maps a User object to a UserProfileResponse object.
   */
  @Test
  public void deveRetornarUserProfileResponseQuandoInformadoUser() {

    // Arrange

    final User user = UserFixture.user();

    // Act

    final UserProfileResponse result = mapper.toUserProfileResponse(user);

    // Assert

    Assertions.assertEquals(user.getId(), result.getId());
    Assertions.assertEquals(user.getFirstName(), result.getFirstName());
    Assertions.assertEquals(user.getLastName(), result.getLastName());
    Assertions.assertEquals(user.getNickName(), result.getNickName());
    Assertions.assertEquals(user.getEmail(), result.getEmail());
    Assertions.assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
    Assertions.assertEquals(user.getProfilePic(), result.getProfilePic());
  }

  /**
   * Tests the toCreateUserSecurityRequest method of the UserMapper class. It checks if the method
   * correctly maps a CreateUserRequest object to a CreateUserSecurityRequest object.
   */
  @Test
  public void deveRetornarCreateUserSecurityRequestQuandoInformadoCreateUserRequest() {

    // Arrange

    CreateUserRequest request = UserFixture.createUserRequest();

    // Act

    final CreateUserSecurityRequest result = mapper.toCreateUserSecurityRequest(request);

    // Assert

    Assertions.assertEquals(request.getFirstName(), result.getFirstName());
    Assertions.assertEquals(request.getLastName(), result.getLastName());
    Assertions.assertEquals(request.getEmail(), result.getEmail());
    Assertions.assertEquals(request.getPassword(), result.getPassword());
  }
}
