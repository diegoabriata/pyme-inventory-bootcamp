package com.portfolio.pymeinventory.warehouse.api.dto;
import jakarta.validation.constraints.*;
public record CreateWarehouseRequest(@NotBlank @Size(max=50) String code,@NotBlank @Size(max=160) String name) {}
