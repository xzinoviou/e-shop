package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.exception.JpaException;
import com.xzinoviou.eshop.model.Product;
import com.xzinoviou.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new JpaException("Failed to retrieve Product with id: " + id));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }
}
