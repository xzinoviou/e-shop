package com.xzinoviou.eshop.controller;

import com.xzinoviou.eshop.dto.auth.AuthRequestDto;
import com.xzinoviou.eshop.dto.jwt.JwtDto;
import com.xzinoviou.eshop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/authorize")
  public ResponseEntity<JwtDto> authorize(@RequestBody AuthRequestDto authRequestDto) {
    return new ResponseEntity<>(authService.authorize(authRequestDto), HttpStatus.CREATED);
  }
}
