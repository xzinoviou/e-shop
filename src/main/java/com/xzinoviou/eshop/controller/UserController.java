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
import org.springframework.web.bind.annotation.*;

/**
 * @author : Xenofon Zinoviou
 */
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(
                genericMapper.mapToUserDto(userService.getByUsername(username)));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAllByRole(null).stream().map(genericMapper::mapToUserDto).toList());
    }

    @PostMapping("/register")
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
