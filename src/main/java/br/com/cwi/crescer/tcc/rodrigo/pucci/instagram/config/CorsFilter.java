package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * CorsFilter class. This class is responsible for configuring Cross-Origin Resource Sharing (CORS)
 * at the filter level. It allows HTTP requests from different origins, enabling the interaction
 * between client-side web applications that are loaded in a domain that is different from the one
 * where the resource resides.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

  /**
   * Configures the CORS filter. This method allows HTTP requests from any origin (*) and for all
   * HTTP methods. It also sets the Access-Control-Allow-Credentials header to "true" and the
   * Access-Control-Max-Age header to "3600". If the HTTP method is OPTIONS, it returns an OK
   * status; otherwise, it continues with the filter chain.
   *
   * @param req the ServletRequest object that contains the client's request.
   * @param res the ServletResponse object that contains the server's response.
   * @param chain the FilterChain object that gives a view into the invocation chain of a filtered
   *     request.
   * @throws IOException if an input or output error is detected when the servlet handles the
   *     doFilter request.
   * @throws ServletException if the request for the doFilter could not be handled.
   */
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader(
        "Access-Control-Allow-Methods",
        "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS," +
            "POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader(
        "Access-Control-Allow-Headers",
        "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

    HttpServletRequest request = (HttpServletRequest) req;
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(req, res);
    }
  }

  /**
   * This method is not needed for this filter.
   *
   * @param filterConfig the FilterConfig object that contains the filter's configuration and
   *     initialization parameters.
   */
  public void init(FilterConfig filterConfig) {
    // not needed
  }

  /** This method is not needed for this filter. */
  public void destroy() {
    // not needed
  }
}
