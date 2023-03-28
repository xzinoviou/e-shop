package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.exception.JpaException;
import com.xzinoviou.eshop.model.Role;
import com.xzinoviou.eshop.model.User;
import com.xzinoviou.eshop.repository.UserRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User getById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new JpaException("User not found, with id: " + id));
  }

  @Override
  public User getByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new JpaException("User not found, with username: " + username));
  }

  @Override
  public List<User> getAllByRole(Role role) {
    return userRepository.findByRole(role);
  }

  @Override
  public User create(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User update(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    User user = getById(id);
    userRepository.delete(user);
  }
}
