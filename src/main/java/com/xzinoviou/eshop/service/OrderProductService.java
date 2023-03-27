package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.model.OrderProduct;
import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
public interface OrderProductService {

  List<OrderProduct> getAllByOrderId(Long orderId);
}
