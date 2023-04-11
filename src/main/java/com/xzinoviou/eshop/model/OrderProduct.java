package com.xzinoviou.eshop.model;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * @author : Xenofon Zinoviou
 */
@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = LAZY, cascade = {MERGE, PERSIST})
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = LAZY, cascade = {MERGE, PERSIST})
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int quantity;

    private BigDecimal price;

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderProductId(order.getId(), product.getId());
    }

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
