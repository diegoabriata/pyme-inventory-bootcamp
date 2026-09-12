package com.portfolio.pymeinventory.sales.api.dto;
import jakarta.validation.constraints.*;
import java.util.UUID;
public record CreateSaleItemRequest(@NotNull UUID productId,@Min(1) int quantity) {}
