package com.portfolio.pymeinventory.product.infrastructure;

import com.portfolio.pymeinventory.product.domain.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, UUID> { boolean existsBySku(String sku); }
