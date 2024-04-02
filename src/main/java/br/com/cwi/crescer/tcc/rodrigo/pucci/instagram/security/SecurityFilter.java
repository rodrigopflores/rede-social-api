package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.security;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception.AutenticacaoException;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * SecurityFilter class. This class extends OncePerRequestFilter and is responsible for filtering
 * every request once. It is annotated with @Component to indicate that it is a Spring Bean.
 */
@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

  /** UserRepository object used for user operations. */
  private final UserRepository userRepository;

  /** TokenService object used for token operations. */
  private final TokenService tokenService;

  /**
   * Filters internal requests.
   *
   * @param request The HttpServletRequest object.
   * @param response The HttpServletResponse object.
   * @param filterChain The FilterChain object.
   * @throws ServletException If a servlet-specific error occurs.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = recoverToken(request);
    if (token != null) {
      var username = tokenService.validateToken(token);
      UserDetails user =
          userRepository
              .findByEmail(username)
              .orElseThrow(() -> new AutenticacaoException("Usuário não encontrado"));
      var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(request, response);
  }

  /**
   * Recovers the token from the request.
   *
   * @param request The HttpServletRequest object.
   * @return The token as a String.
   */
  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    return authHeader == null ? null : authHeader.replace("Bearer ", "");
  }
}
