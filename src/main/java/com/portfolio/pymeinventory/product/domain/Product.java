package com.portfolio.pymeinventory.product.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id private UUID id;
    @Column(nullable = false, unique = true, length = 80) private String sku;
    @Column(nullable = false, length = 160) private String name;
    private String description;
    @Column(name = "sale_price", nullable = false, precision = 19, scale = 2) private BigDecimal salePrice;
    @Column(name = "minimum_stock", nullable = false) private Integer minimumStock;
    @Column(nullable = false) private boolean active;
    @Version private Long version;
    @Column(name = "created_at", nullable = false, updatable = false) private Instant createdAt;
    @Column(name = "updated_at", nullable = false) private Instant updatedAt;
    protected Product() {}
    public Product(String sku, String name, String description, BigDecimal salePrice, Integer minimumStock) {
        this.id=UUID.randomUUID(); this.sku=sku; this.name=name; this.description=description; this.salePrice=salePrice; this.minimumStock=minimumStock; this.active=true; this.createdAt=Instant.now(); this.updatedAt=this.createdAt;
    }
    public UUID getId(){return id;} public String getSku(){return sku;} public String getName(){return name;} public String getDescription(){return description;} public BigDecimal getSalePrice(){return salePrice;} public Integer getMinimumStock(){return minimumStock;} public boolean isActive(){return active;}
}
