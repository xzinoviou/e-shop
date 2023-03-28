package com.xzinoviou.eshop.dto.auth;

/**
 * @author : Xenofon Zinoviou
 */
public class AuthRequestDto {

  private String username;

  private String password;

  public AuthRequestDto() {
  }

  public AuthRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
