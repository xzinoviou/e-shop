package com.xzinoviou.eshop.dto.order;

import com.xzinoviou.eshop.dto.user.UserDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author : Xenofon Zinoviou
 */
public class OrderDto {

  private Long id;

  private Integer totalQuantity;

  private BigDecimal totalPrice;

  private LocalDateTime createdAt;

  private UserDto user;

  private Set<OrderProductDto> products;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(Integer totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public Set<OrderProductDto> getProducts() {
    return products;
  }

  public void setProducts(Set<OrderProductDto> products) {
    this.products = products;
  }
}
