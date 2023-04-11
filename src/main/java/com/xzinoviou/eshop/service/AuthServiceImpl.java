package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.dto.auth.AuthRequestDto;
import com.xzinoviou.eshop.dto.jwt.JwtDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    public JwtDto authorize(AuthRequestDto authRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
                            authRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authRequestDto.getUsername());

        return new JwtDto(jwtService.generateToken(userDetails));
    }
}
