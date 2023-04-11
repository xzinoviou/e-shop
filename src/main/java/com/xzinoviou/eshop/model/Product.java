package com.xzinoviou.eshop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : Xenofon Zinoviou
 */
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String photoUrl;

    private BigDecimal price;

    private int stock;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "product")
    private Set<OrderProduct> orders = new LinkedHashSet<>();

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<OrderProduct> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderProduct> orders) {
        this.orders = orders;
    }
}
