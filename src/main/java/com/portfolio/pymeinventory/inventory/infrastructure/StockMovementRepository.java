package com.portfolio.pymeinventory.inventory.infrastructure;
import com.portfolio.pymeinventory.inventory.domain.StockMovement;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StockMovementRepository extends JpaRepository<StockMovement,UUID>{}
