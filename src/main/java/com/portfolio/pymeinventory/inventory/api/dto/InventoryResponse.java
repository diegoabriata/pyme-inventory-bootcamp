package com.portfolio.pymeinventory.inventory.api.dto;
import java.util.UUID;
public record InventoryResponse(UUID productId,UUID warehouseId,int quantity) {}
