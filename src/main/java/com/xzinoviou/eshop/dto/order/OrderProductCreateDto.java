package com.xzinoviou.eshop.dto.order;

/**
 * @author : Xenofon Zinoviou
 */
public class OrderProductCreateDto {

  private Long productId;

  private int quantity;

  public Long getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }
}
