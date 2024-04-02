package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CorsConfig class. This class is responsible for configuring Cross-Origin Resource Sharing (CORS)
 * in the application. It allows HTTP requests from different origins, enabling the interaction
 * between client-side web applications that are loaded in a domain that is different from the one
 * where the resource resides.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

  /**
   * Configures the CORS mappings. This method allows HTTP requests from any origin (*) and for all
   * HTTP methods (HEAD, GET, PUT, POST, DELETE, PATCH).
   *
   * @param registry the CorsRegistry object that allows the registration of CORS configurations.
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
  }
}
