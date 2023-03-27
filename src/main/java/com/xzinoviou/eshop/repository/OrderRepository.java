package com.xzinoviou.eshop.repository;

import com.xzinoviou.eshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Xenofon Zinoviou
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
