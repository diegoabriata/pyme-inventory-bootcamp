package com.portfolio.pymeinventory.sales.infrastructure;
import com.portfolio.pymeinventory.sales.domain.Sale;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SaleRepository extends JpaRepository<Sale,UUID>{ Optional<Sale> findByIdempotencyKey(String idempotencyKey); }
