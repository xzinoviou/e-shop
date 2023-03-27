package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.exception.JpaException;
import com.xzinoviou.eshop.model.Role;
import com.xzinoviou.eshop.model.User;
import com.xzinoviou.eshop.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new JpaException("User not found, with id: " + id));
  }

  @Override
  public User getByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new JpaException("User not found, with email: " + email));
  }

  @Override
  public List<User> getAllNyRole(Role role) {
    return userRepository.findByRole(role);
  }

  @Override
  public User create(User user) {
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
