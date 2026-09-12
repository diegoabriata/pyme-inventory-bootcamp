package com.portfolio.pymeinventory.sales.domain;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="sales")
public class Sale {
 @Id private UUID id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="warehouse_id",nullable=false) private Warehouse warehouse;
 @Column(name="idempotency_key",nullable=false,unique=true,length=120) private String idempotencyKey;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private SaleStatus status;
 @Column(name="total_amount",nullable=false,precision=19,scale=2) private BigDecimal totalAmount;
 @Column(name="created_at",nullable=false) private Instant createdAt;
 @OneToMany(mappedBy="sale",cascade=CascadeType.ALL,orphanRemoval=true) private List<SaleItem> items=new ArrayList<>();
 protected Sale(){}
 public Sale(Warehouse w,String key){id=UUID.randomUUID();warehouse=w;idempotencyKey=key;status=SaleStatus.CONFIRMED;totalAmount=BigDecimal.ZERO;createdAt=Instant.now();}
 public void addItem(com.portfolio.pymeinventory.product.domain.Product p,int q,BigDecimal price){items.add(new SaleItem(this,p,q,price)); totalAmount=totalAmount.add(price.multiply(BigDecimal.valueOf(q)));}
 public UUID getId(){return id;} public Warehouse getWarehouse(){return warehouse;} public String getIdempotencyKey(){return idempotencyKey;} public BigDecimal getTotalAmount(){return totalAmount;} public List<SaleItem> getItems(){return List.copyOf(items);}
}
