package com.portfolio.pymeinventory.sales.api.dto;
import java.math.BigDecimal;
import java.util.UUID;
public record SaleResponse(UUID id,UUID warehouseId,BigDecimal totalAmount,String status) {}
