package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * InstagramApplication class. This class is the entry point of the Instagram application. It uses
 * Spring Boot's SpringApplication.run() method to launch the application.
 */
@SpringBootApplication
public class InstagramApplication {

  /**
   * The main method. This method is the entry point of the Java application. It launches the Spring
   * Boot application.
   *
   * @param args The command-line arguments passed to the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(InstagramApplication.class, args);
  }
}
