package com.portfolio.pymeinventory.product.api;

import com.portfolio.pymeinventory.product.api.dto.*;
import com.portfolio.pymeinventory.product.application.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
 private final ProductService service;
 public ProductController(ProductService service){this.service=service;}
 @PostMapping public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest request){
  ProductResponse response=service.create(request); return ResponseEntity.created(URI.create("/api/v1/products/"+response.id())).body(response);
 }
 @GetMapping public List<ProductResponse> list(){return service.list();}
}
