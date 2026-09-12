package com.portfolio.pymeinventory.integration;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.portfolio.pymeinventory.product.api.dto.CreateProductRequest;
import com.portfolio.pymeinventory.product.application.ProductService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class PostgresIntegrationTest {
 @Container static PostgreSQLContainer<?> postgres=new PostgreSQLContainer<>("postgres:16-alpine").withDatabaseName("testdb").withUsername("test").withPassword("test");
 @DynamicPropertySource static void properties(DynamicPropertyRegistry r){r.add("spring.datasource.url",postgres::getJdbcUrl);r.add("spring.datasource.username",postgres::getUsername);r.add("spring.datasource.password",postgres::getPassword);}
 @Autowired ProductService products;
 @Test void savesProductAgainstRealPostgres(){var p=products.create(new CreateProductRequest("TEST-1","Test product",null,new BigDecimal("12.50"),0));assertTrue(p.id()!=null);}
}
