package com.xzinoviou.eshop.dto.order;

import java.util.Set;

/**
 * @author : Xenofon Zinoviou
 */

public class OrderCreateDto {

    private Long userId;

    private Set<OrderProductCreateDto> products;

    public Long getUserId() {
        return userId;
    }

    public Set<OrderProductCreateDto> getProducts() {
        return products;
    }

}
