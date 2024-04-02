package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.controller;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.LoginRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.LoginResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.security.TokenService;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for public user operations. All endpoints in this controller do not require
 * authentication.
 */
@RestController
@RequestMapping("/publico/user")
public class UserPublicController {

  @Autowired private UserService service;

  @Autowired private TokenService tokenService;

  @Autowired private AuthenticationManager authenticationManager;

  /**
   * Create a new user.
   *
   * @param request CreateUserRequest object containing the new user data.
   * @return UserStandardResponse object containing the created user data.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserStandardResponse createUser(@Valid @RequestBody CreateUserRequest request) {
    return service.createUser(request);
  }

  /**
   * Login a user.
   *
   * @param request LoginRequest object containing the user's login data.
   * @return LoginResponse object containing the login response data.
   */
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    var usernamePassword =
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    var auth = authenticationManager.authenticate(usernamePassword);
    var token = tokenService.generateToken((User) auth.getPrincipal());

    return new LoginResponse(token);
  }
}
