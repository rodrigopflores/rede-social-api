package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception.BusinessValidationException;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.UserMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.UserRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;


    @Test
    public void deveRetornarUserStandardResponseDeOutroUsuarioQuandoInformarIdValido() {

        // Arrange

        Integer id = 3;
        User foundUser = UserFixture.user();

        // Act

        when(repository.findById(id))
                .thenReturn(Optional.of(foundUser));

        User result = service.getValidatedUserById(id);

        verify(repository).findById(id);

        // Assert

        assertEquals(id, result.getId());
    }

    @Test(expected = BusinessValidationException.class)
    public void deveLancarExceptionQuandoInformadoIdDeUsuarioInexistente() {

        // Arrange

        Integer id = 3;

        // Act

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        User result = service.getValidatedUserById(id);

        verify(repository).findById(id);
    }



    @Test(expected = BusinessValidationException.class)
    public void deveLancarExceptionQuandoTamanhoDaPaginaForMaiorQueCinquenta() {

        // Arrange

        Pageable pageable = PageRequest.of(0, 51);

        // Act

        Page<UserStandardResponse> result = service.getUserFriendsPage(pageable);
    }

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

        when(repository.findById(id1))
                .thenReturn(Optional.of(user1));
        when(repository.findById(id2))
                .thenReturn(Optional.of(user2));

        boolean result = service.areFriends(id1, id2);

        verify(repository).findById(id1);
        verify(repository).findById(id2);

        // Assert

        assertTrue(result);

    }

    @Test
    public void deveRetornarUsuarioQuandoInformadoOId() {

        // Arrange

        User user = UserFixture.user();
        Integer id = 2;
        user.setId(id);

        // Act

        when(repository.findById(id))
                .thenReturn(Optional.of(user));

        User result = service.getUserById(id);

        // Assert

        assertEquals(id, result.getId());

    }
}