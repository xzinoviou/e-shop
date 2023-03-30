package com.xzinoviou.eshop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : Xenofon Zinoviou
 */

@Entity
@Table(name = "ORDERS")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Integer totalQuantity;

  private BigDecimal totalPrice;

  @Temporal(value = TemporalType.TIMESTAMP)
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "USER_ID")
  private User user;

  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "order")
  private Set<OrderProduct> products = new HashSet<>();

  public Order() {
  }

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<OrderProduct> getProducts() {
    return products;
  }

  public void setProducts(Set<OrderProduct> products) {
    this.products = products;
  }

  public void addProduct(Product product, int quantity) {
    OrderProduct orderProduct = new OrderProduct(this, product);
    orderProduct.setQuantity(quantity);
    orderProduct.setPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
    products.add(orderProduct);
    product.getOrders().add(orderProduct);
  }

  public void removeProduct(Product product) {
    for (Iterator<OrderProduct> iterator = products.iterator(); iterator.hasNext(); ) {
      OrderProduct orderProduct = iterator.next();

      if (orderProduct.getOrder().equals(this) && orderProduct.getProduct().equals(product)) {
        iterator.remove();
        orderProduct.getProduct().getOrders().remove(orderProduct);
        orderProduct.setOrder(null);
        orderProduct.setProduct(null);
      }
    }

  }
}
