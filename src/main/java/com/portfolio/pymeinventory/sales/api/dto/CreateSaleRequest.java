package com.portfolio.pymeinventory.sales.api.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
public record CreateSaleRequest(@NotNull UUID warehouseId,@NotBlank @Size(max=120) String idempotencyKey,@NotEmpty List<@Valid CreateSaleItemRequest> items) {}
