# Project Configuration

## Spring Initializr Configuration

- Spring Boot version: 4.1.0
- Project language: Java
- Group id: ec.edu.espe
- Artifact id: users
- Packaging type: Jar
- Java version: 21
- Dependencies:
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Boot DevTools
  - Lombok
  - Validation
  - PostgreSQL Driver

## Database Docker Configuration

```
docker run -d \
  --name users-db \
  -e POSTGRES_DB=users \
  -e POSTGRES_USER=jvc21 \
  -e POSTGRES_PASSWORD=passwd123 \
  -p 5432:5432 \
  postgres:16-alpine
```

## Application Properties

```properties
spring.application.name=users
spring.datasource.url=jdbc:postgresql://localhost:5432/users
spring.datasource.username=jvc21
spring.datasource.password=passwd123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8081
```
