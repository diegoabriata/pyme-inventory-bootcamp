package com.portfolio.pymeinventory.inventory.application;
import static org.junit.jupiter.api.Assertions.*;
import com.portfolio.pymeinventory.inventory.domain.InventoryBalance;
import com.portfolio.pymeinventory.product.domain.Product;
import com.portfolio.pymeinventory.warehouse.domain.Warehouse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
class InventoryBalanceTest {
 private InventoryBalance balance(){return new InventoryBalance(new Product("P-1","Product",null,BigDecimal.TEN,0),new Warehouse("CENTRAL","Central"));}
 @Test void receivesAndRemovesStock(){var b=balance();b.receive(5);b.remove(2);assertEquals(3,b.getQuantity());}
 @Test void rejectsOverselling(){var b=balance();b.receive(1);assertThrows(IllegalArgumentException.class,()->b.remove(2));}
}
