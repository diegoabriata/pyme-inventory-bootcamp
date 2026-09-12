package com.portfolio.pymeinventory.inventory.domain;

import com.portfolio.pymeinventory.product.domain.Product;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import jakarta.persistence.*;
import java.util.UUID;
@Entity @Table(name="inventory_balances", uniqueConstraints=@UniqueConstraint(columnNames={"product_id","warehouse_id"}))
public class InventoryBalance {
 @Id private UUID id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id",nullable=false) private Product product;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="warehouse_id",nullable=false) private Warehouse warehouse;
 @Column(nullable=false) private int quantity;
 @Version private Long version;
 protected InventoryBalance(){}
 public InventoryBalance(Product product,Warehouse warehouse){this.id=UUID.randomUUID();this.product=product;this.warehouse=warehouse;this.quantity=0;}
 public void receive(int amount){if(amount<=0)throw new IllegalArgumentException("Amount must be greater than zero");quantity+=amount;}
 public void remove(int amount){if(amount<=0)throw new IllegalArgumentException("Amount must be greater than zero");if(quantity<amount)throw new IllegalArgumentException("Insufficient stock");quantity-=amount;}
 public UUID getId(){return id;} public Product getProduct(){return product;} public Warehouse getWarehouse(){return warehouse;} public int getQuantity(){return quantity;}
}
