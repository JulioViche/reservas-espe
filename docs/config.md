# Configuración del proyecto

## Spring Initializr Configuration

- Spring Boot version: 4.0.6
- Project languaje: Java
- Group id: ec.edu.espe
- Input artifact id: zones
- Packaging type: Jar
- java version: 21
- Dependencies:
  - Spring Web
  - Spring Data JPA
  - Spring Boot DevTools
  - Lombok
  - Validation
  - Postgre SQL Driver

## Database Docker Configuration

```
docker run -d \
  --name parking-db \
  -e POSTGRES_DB=parking \
  -e POSTGRES_USER=jvc21 \
  -e POSTGRES_PASSWORD=passwd123 \
  -p 5432:5432 \
  postgres:16-alpine
```