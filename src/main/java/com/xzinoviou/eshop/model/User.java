package com.xzinoviou.eshop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
@Table(name = "USERS")
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "USERNAME", unique = true)
  private String username;

  private String firstName;

  private String lastName;

  private String password;

  @Enumerated(value = EnumType.STRING)
  private Role role;

  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST}, mappedBy = "user")
  private List<Order> orders = new ArrayList<>();

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
