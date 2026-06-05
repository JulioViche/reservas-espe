# Parking Reservation System

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

RESTful API for managing parking zones and spaces.

## Quick Start

```bash
# 1. Start PostgreSQL and create the database
psql -U postgres -c "CREATE DATABASE parking;"

# 2. Configure credentials
#    Edit parking/src/main/resources/application.properties

# 3. Run
cd parking
mvn spring-boot:run
```

> Tables are created automatically by Hibernate (`ddl-auto=update`).

## Documentation

| Document | Description |
|----------|-------------|
| [📋 Requirements](docs/requirements.md) | Functional requirements and business rules |
| [🗄️ Data Model](docs/data-model.md) | ER diagram, entities, and enums |
| [🔌 API Reference](docs/api-reference.md) | Endpoints, request/response examples |
| [⚙️ Configuration](docs/config.md) | Database setup and project config |

## Postman

Use the environment variable `{{id}}` to reuse UUIDs across requests. See [API Reference](docs/api-reference.md) for all endpoints.

## Project Structure

```
parking/
└── src/main/java/ec/edu/espe/parking/
    ├── controllers/     # REST controllers
    ├── dtos/            # Request/Response DTOs
    ├── entities/        # JPA entities
    ├── utils/           # Entity <-> DTO mappers
    ├── repositories/    # Spring Data JPA
    └── services/        # Business logic
