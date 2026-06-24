# Project Todo

## Microservicios
- [x] Parking (Spring Boot 4.0.6)
- [x] Users (Spring Boot 4.1.0 + Spring Security)
- [x] Vehicles (NestJS 11)
- [x] Assignments (NestJS 11)

## Infraestructura
- [x] Kong Gateway 3.9 (DB-less, declarative)
- [x] Swagger UI unificado (puerto 3100)
- [x] CORS habilitado en Kong
- [x] Forward headers strategy (Spring Boot services)

## Asignaciones (assignments)
- [x] CRUD asignaciones (POST, PATCH, DELETE)
- [x] Consulta de flota por propietario (GET /owner/:userId)
- [x] Trazabilidad / auditoría (GET /audit/:userId/:vehicleId)
- [x] UpdateAssignmentDto para Swagger
- [x] AssignmentAuditSubscriber funcional

## Pendientes / Mejoras
- [ ] Debug: POST persons retorna 400 sin detalles de validación
- [ ] Validar que los enum no sean case sensitive
- [ ] Agregar autenticación / JWT en Kong
- [ ] Pruebas unitarias y de integración
