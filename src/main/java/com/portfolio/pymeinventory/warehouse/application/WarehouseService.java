package com.portfolio.pymeinventory.warehouse.application;
import com.portfolio.pymeinventory.warehouse.api.dto.*;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import com.portfolio.pymeinventory.warehouse.infrastructure.WarehouseRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional(readOnly=true)
public class WarehouseService {
 private final WarehouseRepository repository;
 public WarehouseService(WarehouseRepository repository){this.repository=repository;}
 @Transactional public WarehouseResponse create(CreateWarehouseRequest request){
  if(repository.existsByCode(request.code()))throw new IllegalArgumentException("Warehouse code already exists: "+request.code());
  return toResponse(repository.save(new Warehouse(request.code(),request.name())));
 }
 public List<WarehouseResponse> list(){return repository.findAll().stream().map(this::toResponse).toList();}
 private WarehouseResponse toResponse(Warehouse w){return new WarehouseResponse(w.getId(),w.getCode(),w.getName(),w.isActive());}
}
