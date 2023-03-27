package com.xzinoviou.eshop.repository;

import com.xzinoviou.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Xenofon Zinoviou
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
