package com.xzinoviou.eshop.dto.jwt;

/**
 * @author : Xenofon Zinoviou
 */
public class JwtDto {

  private String jwtToken;

  public JwtDto() {
  }

  public JwtDto(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public String getJwtToken() {
    return jwtToken;
  }

}
