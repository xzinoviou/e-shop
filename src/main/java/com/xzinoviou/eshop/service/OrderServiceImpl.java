package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.dto.order.OrderCreateDto;
import com.xzinoviou.eshop.dto.order.OrderProductCreateDto;
import com.xzinoviou.eshop.exception.JpaException;
import com.xzinoviou.eshop.model.Order;
import com.xzinoviou.eshop.model.OrderProduct;
import com.xzinoviou.eshop.model.Product;
import com.xzinoviou.eshop.model.User;
import com.xzinoviou.eshop.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final UserService userService;
  private final ProductService productService;

  private final OrderProductService orderProductService;

  public OrderServiceImpl(OrderRepository orderRepository, UserService userService,
      ProductService productService, OrderProductService orderProductService) {
    this.orderRepository = orderRepository;
    this.userService = userService;
    this.productService = productService;
    this.orderProductService = orderProductService;
  }

  @Override
  public Order getById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new JpaException("Failed to retrieve Order with id: " + id));
  }

  @Override
  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  @Override
  public Order create(OrderCreateDto orderCreateDto) {

    Order order = new Order();
    order.setCreatedAt(LocalDateTime.now());

    User user;

    //validate and set user
    try {
      user = userService.getById(orderCreateDto.getUserId());
    } catch (RuntimeException ex) {
      throw new RuntimeException("Order creation failed - Invalid user");
    }

    order.setUser(user);

    int totalQuanity = 0;
    BigDecimal totalPrice = BigDecimal.ZERO;

    //validate and set products
    for (OrderProductCreateDto item : orderCreateDto.getProducts()) {
      try {

        Product product = productService.getById(item.getProductId());

        if (product.getStock() < item.getQuantity()) {
          throw new RuntimeException(
              "Insufficient stock for product with id: " + item.getProductId());
        }

        order.addProduct(product, item.getQuantity());
        product.setStock(product.getStock() - item.getQuantity());

        totalQuanity += item.getQuantity();
        totalPrice = totalPrice.add(
            product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

      } catch (RuntimeException ex) {
        throw new RuntimeException("Order creation failed - " + ex.getMessage());
      }
    }

    order.setTotalPrice(totalPrice);
    order.setTotalQuantity(totalQuanity);

    return orderRepository.save(order);
  }

  @Override
  public Order update(Order order) {
    return null;
  }

  @Override
  public void delete(Long id) {
    Order order = getById(id);

    List<OrderProduct> orderProducts = orderProductService.getAllByOrderId(id);

    for (OrderProduct orderProduct : orderProducts) {
      order.removeProduct(orderProduct.getProduct());
//      int currentStock = orderProduct.getProduct().getStock();
//      orderProduct.getProduct().setStock(currentStock + orderProduct.getQuantity());
    }

    orderRepository.delete(order);
  }
}
