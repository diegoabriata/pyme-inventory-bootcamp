package com.portfolio.pymeinventory.inventory.domain;
import com.portfolio.pymeinventory.product.domain.Product;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="stock_movements")
public class StockMovement {
 @Id private UUID id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id",nullable=false) private Product product;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="warehouse_id",nullable=false) private Warehouse warehouse;
 @Enumerated(EnumType.STRING) @Column(name="movement_type",nullable=false) private MovementType movementType;
 @Column(nullable=false) private int quantity;
 @Column(name="reference_type") private String referenceType;
 @Column(name="reference_id") private UUID referenceId;
 @Column(name="occurred_at",nullable=false) private Instant occurredAt;
 protected StockMovement(){}
 public StockMovement(Product p,Warehouse w,MovementType type,int qty,String refType,UUID refId){id=UUID.randomUUID();product=p;warehouse=w;movementType=type;quantity=qty;referenceType=refType;referenceId=refId;occurredAt=Instant.now();}
}
