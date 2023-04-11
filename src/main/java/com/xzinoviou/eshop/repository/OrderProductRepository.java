package com.xzinoviou.eshop.repository;

import com.xzinoviou.eshop.model.OrderProduct;
import com.xzinoviou.eshop.model.OrderProductId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author : Xenofon Zinoviou
 */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {

    List<OrderProduct> findAllByOrderId(Long orderId);

    @Query("SELECT op FROM OrderProduct op JOIN FETCH op.order JOIN FETCH op.product WHERE op.order.id = :orderId")
    List<OrderProduct> getAllProductsByOrderId(@Param("orderId") Long orderId);
}
