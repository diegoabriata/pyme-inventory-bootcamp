package com.portfolio.pymeinventory.inventory.api.dto;
import jakarta.validation.constraints.*;
import java.util.UUID;
public record ReceiveStockRequest(@NotNull UUID productId,@NotNull UUID warehouseId,@Min(1) int quantity) {}
