package com.portfolio.pymeinventory.warehouse.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="warehouses")
public class Warehouse {
 @Id private UUID id;
 @Column(nullable=false,unique=true,length=50) private String code;
 @Column(nullable=false,length=160) private String name;
 @Column(nullable=false) private boolean active;
 @Column(name="created_at",nullable=false,updatable=false) private Instant createdAt;
 protected Warehouse(){}
 public Warehouse(String code,String name){this.id=UUID.randomUUID();this.code=code;this.name=name;this.active=true;this.createdAt=Instant.now();}
 public UUID getId(){return id;} public String getCode(){return code;} public String getName(){return name;} public boolean isActive(){return active;}
}
