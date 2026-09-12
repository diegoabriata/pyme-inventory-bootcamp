package com.portfolio.pymeinventory.inventory.infrastructure;
import com.portfolio.pymeinventory.inventory.domain.InventoryBalance;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InventoryBalanceRepository extends JpaRepository<InventoryBalance,UUID>{ Optional<InventoryBalance> findByProductIdAndWarehouseId(UUID productId,UUID warehouseId); }
