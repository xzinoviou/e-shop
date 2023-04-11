package com.xzinoviou.eshop.dto.order;

import com.xzinoviou.eshop.dto.product.ProductDto;

import java.math.BigDecimal;

/**
 * @author : Xenofon Zinoviou
 */
public class OrderProductDto {

    private ProductDto product;

    private int quantity;

    private BigDecimal subTotalPrice;

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(BigDecimal subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }
}
