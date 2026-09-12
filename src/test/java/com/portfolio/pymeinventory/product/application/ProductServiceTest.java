package com.portfolio.pymeinventory.product.application;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.portfolio.pymeinventory.product.api.dto.CreateProductRequest;
import com.portfolio.pymeinventory.product.domain.Product;
import com.portfolio.pymeinventory.product.infrastructure.ProductRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
 @Mock ProductRepository repository; @InjectMocks ProductService service;
 @Test void createsProductWhenSkuIsAvailable(){
  when(repository.existsBySku("CHAIR-1")).thenReturn(false); when(repository.save(any(Product.class))).thenAnswer(i->i.getArgument(0));
  var r=service.create(new CreateProductRequest("CHAIR-1","Chair",null,new BigDecimal("10.00"),1));
  assertEquals("CHAIR-1",r.sku()); verify(repository).save(any(Product.class));
 }
 @Test void rejectsDuplicateSku(){
  when(repository.existsBySku("CHAIR-1")).thenReturn(true);
  assertThrows(IllegalArgumentException.class,()->service.create(new CreateProductRequest("CHAIR-1","Chair",null,new BigDecimal("10.00"),1)));
  verify(repository,never()).save(any());
 }
}
