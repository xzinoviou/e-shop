package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.dto.auth.AuthRequestDto;
import com.xzinoviou.eshop.dto.jwt.JwtDto;

/**
 * @author : Xenofon Zinoviou
 */
public interface AuthService {

  JwtDto authorize(AuthRequestDto authRequestDto);
}
