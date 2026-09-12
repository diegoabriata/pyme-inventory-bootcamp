package com.portfolio.pymeinventory.sales.api;
import com.portfolio.pymeinventory.sales.api.dto.*;
import com.portfolio.pymeinventory.sales.application.SaleService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/sales")
public class SaleController {
 private final SaleService service; public SaleController(SaleService service){this.service=service;}
 @PostMapping public ResponseEntity<SaleResponse> create(@Valid @RequestBody CreateSaleRequest r){SaleResponse s=service.create(r);return ResponseEntity.created(URI.create("/api/v1/sales/"+s.id())).body(s);}
}
