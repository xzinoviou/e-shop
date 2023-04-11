package com.xzinoviou.eshop.controller;

import com.xzinoviou.eshop.dto.order.OrderCreateDto;
import com.xzinoviou.eshop.dto.order.OrderDto;
import com.xzinoviou.eshop.mapper.GenericMapper;
import com.xzinoviou.eshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    private final OrderService orderService;
    private final GenericMapper genericMapper;

    public OrderController(OrderService orderService, GenericMapper genericMapper) {
        this.orderService = orderService;
        this.genericMapper = genericMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(genericMapper.mapToOrderDto(orderService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(
                orderService.getAll().stream().map(genericMapper::mapToOrderDto).toList());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderDto>> getAllByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(
                orderService.getAllByUserId(id).stream().map(genericMapper::mapToOrderDto).toList());
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderCreateDto orderCreateDto) {
        return new ResponseEntity<>(
                genericMapper.mapToOrderDto(orderService.create(orderCreateDto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


