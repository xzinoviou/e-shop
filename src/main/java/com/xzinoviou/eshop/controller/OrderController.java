package com.xzinoviou.eshop.controller;

import com.xzinoviou.eshop.dto.order.OrderCreateDto;
import com.xzinoviou.eshop.dto.order.OrderDto;
import com.xzinoviou.eshop.mapper.GenericMapper;
import com.xzinoviou.eshop.service.OrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/orders")
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
