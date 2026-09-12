package com.portfolio.pymeinventory.warehouse.infrastructure;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID>{ boolean existsByCode(String code); }
