package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.model.RegisteredUser;
import com.xzinoviou.eshop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new RegisteredUser(
        userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(username)));
  }
}
