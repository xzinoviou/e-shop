package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.dto.order.OrderCreateDto;
import com.xzinoviou.eshop.dto.product.ProductCreateDto;
import com.xzinoviou.eshop.model.Order;
import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
public interface OrderService {

  Order getById(Long id);

  List<Order> getAll();

  Order create(OrderCreateDto orderCreateDto);

  Order update(Order order);

  void delete(Long id);
}
