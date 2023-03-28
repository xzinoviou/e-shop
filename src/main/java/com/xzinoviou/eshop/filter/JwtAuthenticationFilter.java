package com.xzinoviou.eshop.filter;

import com.xzinoviou.eshop.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author : Xenofon Zinoviou
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;

  private final JwtService jwtService;

  public static final String AUTHORIZATION = "Authorization";

  public static final String BEARER = "Bearer";

  public static final String SPACE = " ";

  public JwtAuthenticationFilter(UserDetailsService userDetailsServiceImpl, JwtService jwtService) {
    this.userDetailsService = userDetailsServiceImpl;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    final String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION);

    String username = null;
    String token = null;

    if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {

      token = authorizationHeader.split(SPACE)[1];
      username = jwtService.extractUsername(token);

    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (jwtService.validateToken(token, userDetails)) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
