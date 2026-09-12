package com.portfolio.pymeinventory.inventory.api;
import com.portfolio.pymeinventory.inventory.api.dto.*;
import com.portfolio.pymeinventory.inventory.application.InventoryService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/inventory")
public class InventoryController {
 private final InventoryService service; public InventoryController(InventoryService service){this.service=service;}
 @PostMapping("/receipts") public ResponseEntity<InventoryResponse> receive(@Valid @RequestBody ReceiveStockRequest r){return ResponseEntity.ok(service.receive(r));}
 @GetMapping public InventoryResponse get(@RequestParam UUID productId,@RequestParam UUID warehouseId){return service.get(productId,warehouseId);}
}
