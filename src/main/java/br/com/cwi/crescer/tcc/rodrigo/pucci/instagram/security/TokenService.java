package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.security;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * TokenService class. This class is responsible for generating and validating JWT tokens. It is
 * annotated with @Service to indicate that it is a Spring Bean.
 */
@Service
public class TokenService {

  /** Secret key for JWT token generation and validation. */
  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * Generates a JWT token for a given user.
   *
   * @param user The User object for which the token is to be generated.
   * @return The generated JWT token as a String.
   */
  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token =
          JWT.create()
              .withIssuer("prjf-auth-api")
              .withSubject(user.getEmail())
              .withExpiresAt(genExpirationDate())
              .sign(algorithm);
      return token;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Validates a given JWT token.
   *
   * @param token The JWT token to be validated.
   * @return The subject of the JWT token if it is valid, otherwise an empty string.
   */
  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm).withIssuer("prjf-auth-api").build().verify(token).getSubject();
    } catch (JWTVerificationException exception) {
      return "";
    }
  }

  /**
   * Generates an expiration date for the JWT token. The expiration date is 2 hours from the current
   * time.
   *
   * @return The expiration date as an Instant object.
   */
  private Instant genExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
