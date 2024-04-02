package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig class. This class is responsible for configuring the security settings of the
 * application. It is annotated with @Configuration and @EnableWebSecurity to indicate that it is a
 * configuration class and that it should enable Spring Security's web security support.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /** SecurityFilter object used for filtering security. */
  @Autowired SecurityFilter securityFilter;

  /**
   * Configures the SecurityFilterChain.
   *
   * @param httpSecurity The HttpSecurity object.
   * @return The SecurityFilterChain object.
   * @throws Exception If an error occurs during the configuration.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            (authorization) ->
                authorization
                    .requestMatchers("/publico/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  /**
   * Creates a BCryptPasswordEncoder bean.
   *
   * @return The BCryptPasswordEncoder object.
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Creates an AuthenticationManager bean.
   *
   * @param authenticationConfiguration The AuthenticationConfiguration object.
   * @return The AuthenticationManager object.
   * @throws Exception If an error occurs during the creation of the AuthenticationManager.
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
