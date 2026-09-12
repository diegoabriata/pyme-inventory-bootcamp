# Mini-Pyme Inventory Bootcamp

Base práctica completa hasta PYME-301/302. Es un laboratorio de portfolio y estudio: no reproduce código ni información interna de ningún empleador.

## Requisitos
- Java 21 (Java 17 también suele funcionar si cambias `java.version` en `pom.xml`)
- Maven
- Docker Desktop

## Inicio rápido
```bash
docker compose up -d
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

Health: `curl http://localhost:8081/actuator/health`

## Ejecutar tests
```bash
./mvnw test
```
Los tests de integración requieren Docker porque Testcontainers inicia PostgreSQL temporal.

## Próximo paso práctico
El proyecto termina en PYME-301/302. El próximo laboratorio es **PYME-303: SonarQube + Quality Gate**, seguido por Jenkins y despliegue.
