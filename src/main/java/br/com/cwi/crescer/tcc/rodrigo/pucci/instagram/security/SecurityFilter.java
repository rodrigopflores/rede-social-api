package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.security;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception.AutenticacaoException;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

  private final UserRepository userRepository;
  private final TokenService tokenService;

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

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    return authHeader == null ? null : authHeader.replace("Bearer ", "");
  }
}
