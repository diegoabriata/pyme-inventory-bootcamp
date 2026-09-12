package com.portfolio.pymeinventory.inventory.application;
import com.portfolio.pymeinventory.inventory.api.dto.*;
import com.portfolio.pymeinventory.inventory.domain.*;
import com.portfolio.pymeinventory.inventory.infrastructure.*;
import com.portfolio.pymeinventory.product.domain.Product;
import com.portfolio.pymeinventory.product.infrastructure.ProductRepository;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import com.portfolio.pymeinventory.warehouse.infrastructure.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
public class InventoryService {
 private final ProductRepository products; private final WarehouseRepository warehouses; private final InventoryBalanceRepository balances; private final StockMovementRepository movements;
 public InventoryService(ProductRepository p,WarehouseRepository w,InventoryBalanceRepository b,StockMovementRepository m){products=p;warehouses=w;balances=b;movements=m;}
 @Transactional public InventoryResponse receive(ReceiveStockRequest req){
  Product product=products.findById(req.productId()).orElseThrow(()->new IllegalArgumentException("Product not found"));
  Warehouse warehouse=warehouses.findById(req.warehouseId()).orElseThrow(()->new IllegalArgumentException("Warehouse not found"));
  InventoryBalance balance=balances.findByProductIdAndWarehouseId(product.getId(),warehouse.getId()).orElseGet(()->new InventoryBalance(product,warehouse));
  balance.receive(req.quantity()); balances.save(balance);
  movements.save(new StockMovement(product,warehouse,MovementType.RECEIPT,req.quantity(),"RECEIPT",null));
  return new InventoryResponse(product.getId(),warehouse.getId(),balance.getQuantity());
 }
 @Transactional(readOnly=true) public InventoryResponse get(UUID productId,UUID warehouseId){
  InventoryBalance b=balances.findByProductIdAndWarehouseId(productId,warehouseId).orElseThrow(()->new IllegalArgumentException("Inventory balance not found"));
  return new InventoryResponse(productId,warehouseId,b.getQuantity());
 }
}
