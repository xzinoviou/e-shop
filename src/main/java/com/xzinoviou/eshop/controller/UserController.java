package com.xzinoviou.eshop.controller;

import com.xzinoviou.eshop.dto.user.UserCreateDto;
import com.xzinoviou.eshop.dto.user.UserDto;
import com.xzinoviou.eshop.mapper.GenericMapper;
import com.xzinoviou.eshop.model.Role;
import com.xzinoviou.eshop.model.User;
import com.xzinoviou.eshop.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final GenericMapper genericMapper;

  public UserController(UserService userService, GenericMapper genericMapper) {
    this.userService = userService;
    this.genericMapper = genericMapper;
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(genericMapper.mapToUserDto(userService.getById(id)));
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
    return ResponseEntity.ok(
        genericMapper.mapToUserDto(userService.getByEmail(email)));
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return ResponseEntity.ok(
        userService.getAllNyRole(Role.USER).stream().map(genericMapper::mapToUserDto).toList());
  }

  @PostMapping
  public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userCreateDto) {
    User user = genericMapper.mapToUser(userCreateDto);
    return new ResponseEntity<>(
        genericMapper.mapToUserDto(userService.create(user)), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<UserDto> update(@RequestBody User user) {
    return new ResponseEntity<>(genericMapper.mapToUserDto(userService.update(user)),
        HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
