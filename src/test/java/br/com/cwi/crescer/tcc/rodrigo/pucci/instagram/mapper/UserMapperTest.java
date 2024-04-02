package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import static org.junit.Assert.assertEquals;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserSecurityRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.junit.Test;

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

    CreateUserRequest request = UserFixture.createUserRequest();

    // Act

    User result = mapper.toDomain(request);

    // Assert

    assertEquals(request.getNickName(), result.getNickName());
    assertEquals(request.getFirstName(), result.getFirstName());
    assertEquals(request.getLastName(), result.getLastName());
    assertEquals(request.getEmail(), result.getEmail());
    assertEquals(request.getProfilePic(), result.getProfilePic());
    assertEquals(request.getDateOfBirth(), result.getDateOfBirth());
  }

  /**
   * Tests the toUserStandardResponse method of the UserMapper class. It checks if the method
   * correctly maps a User object to a UserStandardResponse object.
   */
  @Test
  public void deveRetornarUmUserStandardResponseQuandoInformadoUmUser() {

    // Arrange

    User user = UserFixture.user();

    // Act

    UserStandardResponse result = mapper.toUserStandardResponse(user);

    // Assert

    assertEquals(user.getId(), result.getId());
    assertEquals(user.getFirstName(), result.getFirstName());
    assertEquals(user.getLastName(), result.getLastName());
    assertEquals(user.getNickName(), result.getNickName());
    assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
    assertEquals(user.getEmail(), result.getEmail());
    assertEquals(user.getProfilePic(), result.getProfilePic());
  }

  /**
   * Tests the toUserProfileResponse method of the UserMapper class. It checks if the method
   * correctly maps a User object to a UserProfileResponse object.
   */
  @Test
  public void deveRetornarUserProfileResponseQuandoInformadoUser() {

    // Arrange

    User user = UserFixture.user();

    // Act

    UserProfileResponse result = mapper.toUserProfileResponse(user);

    // Assert

    assertEquals(user.getId(), result.getId());
    assertEquals(user.getFirstName(), result.getFirstName());
    assertEquals(user.getLastName(), result.getLastName());
    assertEquals(user.getNickName(), result.getNickName());
    assertEquals(user.getEmail(), result.getEmail());
    assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
    assertEquals(user.getProfilePic(), result.getProfilePic());
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

    CreateUserSecurityRequest result = mapper.toCreateUserSecurityRequest(request);

    // Assert

    assertEquals(request.getFirstName(), result.getFirstName());
    assertEquals(request.getLastName(), result.getLastName());
    assertEquals(request.getEmail(), result.getEmail());
    assertEquals(request.getPassword(), result.getPassword());
  }
}
