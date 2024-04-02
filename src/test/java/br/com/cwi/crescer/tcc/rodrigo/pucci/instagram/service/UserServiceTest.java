package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception.BusinessValidationException;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.UserMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.UserRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * UserServiceTest class. This class is responsible for testing the UserService class. It uses
 * Mockito for mocking dependencies and JUnit for running the tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  /** The UserService object to be tested. */
  @InjectMocks private UserService service;

  /** The UserRepository object used for user operations. */
  @Mock private UserRepository repository;

  /** The UserMapper object used for mapping between domain and representation objects. */
  @Mock private UserMapper mapper;

  /**
   * Tests the getValidatedUserById method of the UserService class. It checks if the method
   * correctly retrieves a user by its ID.
   */
  @Test
  public void deveRetornarUserStandardResponseDeOutroUsuarioQuandoInformarIdValido() {

    // Arrange

    Integer id = 3;
    User foundUser = UserFixture.user();

    // Act

    Mockito.when(repository.findById(id)).thenReturn(Optional.of(foundUser));

    User result = service.getValidatedUserById(id);

    Mockito.verify(repository).findById(id);

    // Assert

    Assertions.assertEquals(id, result.getId());
  }

  /**
   * Tests the getValidatedUserById method of the UserService class. It checks if the method throws
   * an exception Mockito.when the user is not found.
   */
  @Test(expected = BusinessValidationException.class)
  public void deveLancarExceptionQuandoInformadoIdDeUsuarioInexistente() {

    // Arrange

    Integer id = 3;

    // Act

    Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

    User result = service.getValidatedUserById(id);

    Mockito.verify(repository).findById(id);
  }

  /**
   * Tests the getUserFriendsPage method of the UserService class. It checks if the method throws an
   * exception Mockito.when the page size is greater than 50.
   */
  @Test(expected = BusinessValidationException.class)
  public void deveLancarExceptionQuandoTamanhoDaPaginaForMaiorQueCinquenta() {

    // Arrange

    Pageable pageable = PageRequest.of(0, 51);

    // Act

    Page<UserStandardResponse> result = service.getUserFriendsPage(pageable);
  }

  /**
   * Tests the areFriends method of the UserService class. It checks if the method correctly
   * identifies if two users are friends.
   */
  @Test
  public void deveRetornarVerdeiroQuandoOsUsuariosForemAmigos() {

    // Arrange
    User user1 = UserFixture.user();
    Integer id1 = 2;
    user1.setId(id1);
    User user2 = UserFixture.user();
    Integer id2 = 6;
    user2.setId(id2);
    user2.setEmail("friend@email.com");
    user1.getFriends().add(user2);
    user2.getFriends().add(user1);

    // Act

    Mockito.when(repository.findById(id1)).thenReturn(Optional.of(user1));
    Mockito.when(repository.findById(id2)).thenReturn(Optional.of(user2));

    boolean result = service.areFriends(id1, id2);

    Mockito.verify(repository).findById(id1);
    Mockito.verify(repository).findById(id2);

    // Assert

    Assertions.assertTrue(result);
  }

  /**
   * Tests the getUserById method of the UserService class. It checks if the method correctly
   * retrieves a user by its ID.
   */
  @Test
  public void deveRetornarUsuarioQuandoInformadoOId() {

    // Arrange

    User user = UserFixture.user();
    Integer id = 2;
    user.setId(id);

    // Act

    Mockito.when(repository.findById(id)).thenReturn(Optional.of(user));

    User result = service.getUserById(id);

    // Assert

    Assertions.assertEquals(id, result.getId());
  }
}
