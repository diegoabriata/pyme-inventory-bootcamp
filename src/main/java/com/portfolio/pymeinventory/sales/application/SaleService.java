package com.portfolio.pymeinventory.sales.application;
import com.portfolio.pymeinventory.inventory.domain.*;
import com.portfolio.pymeinventory.inventory.infrastructure.*;
import com.portfolio.pymeinventory.product.domain.Product;
import com.portfolio.pymeinventory.product.infrastructure.ProductRepository;
import com.portfolio.pymeinventory.sales.api.dto.*;
import com.portfolio.pymeinventory.sales.domain.Sale;
import com.portfolio.pymeinventory.sales.infrastructure.SaleRepository;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import com.portfolio.pymeinventory.warehouse.infrastructure.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SaleService {
 private final SaleRepository sales; private final WarehouseRepository warehouses; private final ProductRepository products; private final InventoryBalanceRepository balances; private final StockMovementRepository movements;
 public SaleService(SaleRepository s,WarehouseRepository w,ProductRepository p,InventoryBalanceRepository b,StockMovementRepository m){sales=s;warehouses=w;products=p;balances=b;movements=m;}
 @Transactional public SaleResponse create(CreateSaleRequest request){
  var previous=sales.findByIdempotencyKey(request.idempotencyKey()); if(previous.isPresent()) return response(previous.get());
  Warehouse warehouse=warehouses.findById(request.warehouseId()).orElseThrow(()->new IllegalArgumentException("Warehouse not found"));
  Sale sale=new Sale(warehouse,request.idempotencyKey());
  for(CreateSaleItemRequest item:request.items()){
   Product product=products.findById(item.productId()).orElseThrow(()->new IllegalArgumentException("Product not found"));
   if(!product.isActive())throw new IllegalArgumentException("Product is inactive");
   InventoryBalance balance=balances.findByProductIdAndWarehouseId(product.getId(),warehouse.getId()).orElseThrow(()->new IllegalArgumentException("No stock balance for product"));
   balance.remove(item.quantity());
   sale.addItem(product,item.quantity(),product.getSalePrice());
   movements.save(new StockMovement(product,warehouse,MovementType.SALE,item.quantity(),"SALE",sale.getId()));
  }
  return response(sales.save(sale));
 }
 private SaleResponse response(Sale s){return new SaleResponse(s.getId(),s.getWarehouse().getId(),s.getTotalAmount(),"CONFIRMED");}
}
