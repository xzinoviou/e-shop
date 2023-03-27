package com.xzinoviou.eshop.mapper;

import com.xzinoviou.eshop.dto.order.OrderCreateDto;
import com.xzinoviou.eshop.dto.order.OrderDto;
import com.xzinoviou.eshop.dto.order.OrderProductDto;
import com.xzinoviou.eshop.dto.product.ProductCreateDto;
import com.xzinoviou.eshop.dto.product.ProductDto;
import com.xzinoviou.eshop.dto.user.UserCreateDto;
import com.xzinoviou.eshop.dto.user.UserDto;
import com.xzinoviou.eshop.model.Order;
import com.xzinoviou.eshop.model.OrderProduct;
import com.xzinoviou.eshop.model.Product;
import com.xzinoviou.eshop.model.Role;
import com.xzinoviou.eshop.model.User;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * @author : Xenofon Zinoviou
 */
@Component
public class GenericMapper {

  public Order mapToOrder(OrderCreateDto orderCreateDto) {
    return null;
  }

  public Product mapToProduct(ProductCreateDto productCreateDto) {
    Product product = new Product();
    product.setTitle(productCreateDto.getTitle());
    product.setPhotoUrl(productCreateDto.getPhotoUrl());
    product.setPrice(productCreateDto.getPrice());
    product.setStock(productCreateDto.getStock());
    return product;
  }

  public ProductDto mapToProductDto(Product product) {
    ProductDto dto = new ProductDto();
    dto.setId(product.getId());
    dto.setTitle(product.getTitle());
    dto.setPhotoUrl(product.getPhotoUrl());
    dto.setPrice(product.getPrice());
    dto.setStock(product.getStock());
    return dto;
  }

  public UserDto mapToUserDto(User user) {
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setEmail(user.getEmail());
    dto.setPassword("*******");
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());
    dto.setRole(user.getRole());
    return dto;
  }

  public User mapToUser(UserCreateDto dto) {
    User user = new User();
    user.setEmail(dto.getEmail());
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setRole(Role.USER);
    user.setPassword(dto.getPassword());
    return user;
  }

  public OrderDto mapToOrderDto(Order order) {
    OrderDto dto = new OrderDto();
    dto.setId(order.getId());
    dto.setCreatedAt(order.getCreatedAt());
    dto.setTotalPrice(order.getTotalPrice());
    dto.setTotalQuantity(order.getTotalQuantity());
    dto.setUser(mapToUserDto(order.getUser()));
    dto.setProducts(
        order.getProducts().stream().map(this::mapToOrderProductDto).collect(Collectors.toSet()));
    return dto;
  }

  public OrderProductDto mapToOrderProductDto(OrderProduct orderProduct) {
    OrderProductDto dto = new OrderProductDto();
    dto.setProduct(mapToProductDto(orderProduct.getProduct()));
    dto.setQuantity(orderProduct.getQuantity());
    dto.setSubTotalPrice(orderProduct.getPrice());
    return dto;
  }
}
