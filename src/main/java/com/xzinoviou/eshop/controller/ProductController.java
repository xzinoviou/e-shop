package com.xzinoviou.eshop.controller;

import com.xzinoviou.eshop.dto.product.ProductCreateDto;
import com.xzinoviou.eshop.dto.product.ProductDto;
import com.xzinoviou.eshop.mapper.GenericMapper;
import com.xzinoviou.eshop.model.Product;
import com.xzinoviou.eshop.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  private final GenericMapper genericMapper;

  public ProductController(ProductService productService, GenericMapper genericMapper,
      SecurityContext securityContext) {
    this.productService = productService;
    this.genericMapper = genericMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(genericMapper.mapToProductDto(productService.getById(id)));
  }

  @GetMapping
  public ResponseEntity<List<ProductDto>> getAll() {
    return ResponseEntity.ok(
        productService.getAll().stream().map(genericMapper::mapToProductDto).toList());
  }

  @PostMapping
  public ResponseEntity<ProductDto> create(@RequestBody ProductCreateDto productCreateDto) {
    Product product = genericMapper.mapToProduct(productCreateDto);
    return new ResponseEntity<>(
        genericMapper.mapToProductDto(productService.create(product)), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Product> update(@RequestBody Product product) {
    return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
