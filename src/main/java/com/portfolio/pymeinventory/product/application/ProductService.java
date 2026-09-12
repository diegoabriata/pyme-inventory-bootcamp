package com.portfolio.pymeinventory.product.application;

import com.portfolio.pymeinventory.product.api.dto.*;
import com.portfolio.pymeinventory.product.domain.Product;
import com.portfolio.pymeinventory.product.infrastructure.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {
 private final ProductRepository repository;
 public ProductService(ProductRepository repository){this.repository=repository;}
 @Transactional public ProductResponse create(CreateProductRequest request){
  if(repository.existsBySku(request.sku())) throw new IllegalArgumentException("SKU already exists: " + request.sku());
  return toResponse(repository.save(new Product(request.sku(), request.name(), request.description(), request.salePrice(), request.minimumStock())));
 }
 public List<ProductResponse> list(){return repository.findAll().stream().map(this::toResponse).toList();}
 private ProductResponse toResponse(Product p){return new ProductResponse(p.getId(),p.getSku(),p.getName(),p.getDescription(),p.getSalePrice(),p.getMinimumStock(),p.isActive());}
}
