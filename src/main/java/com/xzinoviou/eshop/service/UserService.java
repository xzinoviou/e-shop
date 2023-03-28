package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.model.Role;
import com.xzinoviou.eshop.model.User;
import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
public interface UserService {

  User getById(Long id);

  User getByUsername(String username);

  List<User> getAllByRole(Role role);

  User create(User user);

  User update(User user);

  void delete(Long id);

}
