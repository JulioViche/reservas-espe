# Project Configuration

## NestJS Initialization

```bash
$ nest new vehicles
```

- NestJS version: 11.x
- Language: TypeScript
- Package manager: npm
- Port: 3000

## Dependencies

| Package | Version | Purpose |
|---------|---------|---------|
| `@nestjs/common` | ^11.0.1 | Core decorators, guards, pipes |
| `@nestjs/config` | ^4.0.4 | Environment configuration |
| `@nestjs/core` | ^11.0.1 | NestJS runtime |
| `@nestjs/mapped-types` | ^2.1.1 | PartialType for DTOs |
| `@nestjs/platform-express` | ^11.0.1 | Express adapter |
| `@nestjs/typeorm` | ^11.0.1 | TypeORM integration |
| `class-transformer` | ^0.5.1 | Object transformations |
| `class-validator` | ^0.15.1 | Decorator-based validation |
| `pg` | ^8.21.0 | PostgreSQL driver |
| `typeorm` | ^0.3.21 | ORM with Single Table Inheritance |

## Environment Variables (.env)

```env
DB_HOST=localhost
DB_PORT=5434
DB_USERNAME=jvc21
DB_PASSWORD=passwd123
DB_NAME=vehicles
PORT=3000
```

## Scripts

| Command | Description |
|---------|-------------|
| `npm run build` | Compile TypeScript to `dist/` |
| `npm start` | Run the application |
| `npm run start:dev` | Watch mode with auto-reload |
| `npm run start:prod` | Run compiled output |
| `npm run lint` | ESLint with auto-fix |
| `npm test` | Run unit tests |
| `npm run test:e2e` | Run end-to-end tests |
| `npm run test:cov` | Run tests with coverage |

## Docker Compose

The service runs as part of the project's `docker-compose.yml`:

```yaml
vehicles-db:
  image: postgres:16-alpine
  environment:
    POSTGRES_DB: vehicles
    POSTGRES_USER: jvc21
    POSTGRES_PASSWORD: passwd123
  ports:
    - "5434:5432"

vehicles-api:
  build: ./vehicles
  ports:
    - "3000:3000"
  environment:
    DB_HOST: vehicles-db
    DB_PORT: "5432"
    DB_USERNAME: jvc21
    DB_PASSWORD: passwd123
    DB_NAME: vehicles
  depends_on:
    vehicles-db:
      condition: service_healthy
```

## Database Only (dev)

```bash
docker run -d \
  --name vehicles-db \
  -e POSTGRES_DB=vehicles \
  -e POSTGRES_USER=jvc21 \
  -e POSTGRES_PASSWORD=passwd123 \
  -p 5434:5432 \
  postgres:16-alpine
```

> **Note:** The `vehicles-db` uses port `5434` to avoid conflicts with `parking-db` (`5432`) and `users-db` (`5433`).
