package com.xzinoviou.eshop.model;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author : Xenofon Zinoviou
 */
public class RegisteredUser implements UserDetails {


  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public RegisteredUser() {
  }

  public RegisteredUser(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authorities = createAuthorities(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  private Collection<? extends GrantedAuthority> createAuthorities(User user) {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    //return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
  }
}
