package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.model.OrderProduct;
import com.xzinoviou.eshop.repository.OrderProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class OrderProductServiceImpl implements OrderProductService {

  private final OrderProductRepository orderProductRepository;

  public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
    this.orderProductRepository = orderProductRepository;
  }

  @Override
  public List<OrderProduct> getAllByOrderId(Long orderId) {
    return orderProductRepository.getAllProductsByOrderId(orderId);
  }
}
