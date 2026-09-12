package com.portfolio.pymeinventory.warehouse.api;
import com.portfolio.pymeinventory.warehouse.api.dto.*;
import com.portfolio.pymeinventory.warehouse.application.WarehouseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/warehouses")
public class WarehouseController {
 private final WarehouseService service;
 public WarehouseController(WarehouseService service){this.service=service;}
 @PostMapping public ResponseEntity<WarehouseResponse> create(@Valid @RequestBody CreateWarehouseRequest request){
  WarehouseResponse r=service.create(request);return ResponseEntity.created(URI.create("/api/v1/warehouses/"+r.id())).body(r);
 }
 @GetMapping public List<WarehouseResponse> list(){return service.list();}
}
