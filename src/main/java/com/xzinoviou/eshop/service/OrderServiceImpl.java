package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.dto.order.OrderCreateDto;
import com.xzinoviou.eshop.dto.order.OrderProductCreateDto;
import com.xzinoviou.eshop.exception.JpaException;
import com.xzinoviou.eshop.model.Order;
import com.xzinoviou.eshop.model.OrderProduct;
import com.xzinoviou.eshop.model.Product;
import com.xzinoviou.eshop.model.User;
import com.xzinoviou.eshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    private final OrderProductService orderProductService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService,
                            ProductService productService, OrderProductService orderProductService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new JpaException("Failed to retrieve Order with id: " + id));
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllByUserId(Long id) {
        return orderRepository.findAllByUser_Id(id);
    }

    @Override
    public Order create(OrderCreateDto orderCreateDto) {

        Order order = new Order();
        order.setTotalPrice(BigDecimal.ZERO);
        order.setTotalQuantity(0);
        order.setCreatedAt(LocalDateTime.now());

        User user;

        //validate and set user
        try {
            user = userService.getById(orderCreateDto.getUserId());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Order creation failed - Invalid user");
        }

        order.setUser(user);

        //validate and set products
        for (OrderProductCreateDto item : orderCreateDto.getProducts()) {
            try {

                Product product = productService.getById(item.getProductId());

                if (product.getStock() < item.getQuantity()) {
                    throw new RuntimeException(
                            "Insufficient stock for product with id: " + item.getProductId());
                }

                order.addProduct(product, item.getQuantity());
                product.setStock(product.getStock() - item.getQuantity());

                order.setTotalQuantity(order.getTotalQuantity() + item.getQuantity());
                order.setTotalPrice(order.getTotalPrice()
                        .add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))));

            } catch (RuntimeException ex) {
                throw new RuntimeException("Order creation failed - " + ex.getMessage());
            }
        }

        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Order order = getById(id);

        List<OrderProduct> orderProducts = orderProductService.getAllByOrderId(id);

        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.getProduct().setStock(
                    orderProduct.getProduct().getStock() + orderProduct.getQuantity());
            order.removeProduct(orderProduct.getProduct());
        }

        orderRepository.delete(order);
    }
}
