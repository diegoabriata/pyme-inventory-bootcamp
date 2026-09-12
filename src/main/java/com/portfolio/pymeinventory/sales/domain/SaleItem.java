package com.portfolio.pymeinventory.sales.domain;
import com.portfolio.pymeinventory.product.domain.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
@Entity @Table(name="sale_items")
public class SaleItem {
 @Id private UUID id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="sale_id",nullable=false) private Sale sale;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id",nullable=false) private Product product;
 @Column(nullable=false) private int quantity;
 @Column(name="unit_price",nullable=false,precision=19,scale=2) private BigDecimal unitPrice;
 @Column(name="line_total",nullable=false,precision=19,scale=2) private BigDecimal lineTotal;
 protected SaleItem(){}
 public SaleItem(Sale s,Product p,int q,BigDecimal price){id=UUID.randomUUID();sale=s;product=p;quantity=q;unitPrice=price;lineTotal=price.multiply(BigDecimal.valueOf(q));}
 public Product getProduct(){return product;} public int getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;}
}
