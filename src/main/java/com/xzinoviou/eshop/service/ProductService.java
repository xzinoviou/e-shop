package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.model.Product;

import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
public interface ProductService {

    Product getById(Long id);

    List<Product> getAll();

    Product create(Product product);

    Product update(Product product);

    void delete(Long id);
}
