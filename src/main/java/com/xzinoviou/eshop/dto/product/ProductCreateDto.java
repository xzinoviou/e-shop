package com.xzinoviou.eshop.dto.product;

import java.math.BigDecimal;

/**
 * @author : Xenofon Zinoviou
 */
public class ProductCreateDto {

  private String title;

  private String photoUrl;

  private BigDecimal price;

  private int stock;

  public String getTitle() {
    return title;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }
}
