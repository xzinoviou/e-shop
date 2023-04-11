package com.xzinoviou.eshop.repository;

import com.xzinoviou.eshop.model.Role;
import com.xzinoviou.eshop.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Xenofon Zinoviou
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByRole(Role role);

}
