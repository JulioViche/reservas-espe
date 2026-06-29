# Evaluación Conjunta

## 1. Contexto

Una empresa de tecnología está desarrollando un sistema integral para la gestión de parqueaderos inteligentes. El sistema requiere permitir que los propietarios de vehículos registrados puedan asociar sus vehículos a sus cuentas, considerando las características específicas de cada automotor (tipo y categoría de energía) para la correcta asignación de tarifas y espacios automatizados. Adicionalmente, por motivos de auditoría y seguridad, se debe mantener un registro estricto e histórico (trazabilidad) de todos los movimientos y cambios de estado de estas asignaciones.

## 2. Estado Actual de la Arquitectura

El ecosistema ya cuenta con dos microservicios core completamente funcionales y desplegados:

1. **Microservicio de Usuarios y Roles**: Administra la información de los propietarios, cuentas, credenciales y perfiles.
2. **Microservicio de Gestión de Vehículos**: Administra el catálogo global de vehículos.

Cada vehículo está definido por:
- **Tipo**: Moto, Automóvil, Camioneta.
- **Categoría**: Eléctrico, Híbrido, Combustión.

## 3. Objetivos

El candidato deberá diseñar e implementar un nuevo **Microservicio de Asignación y Trazabilidad** que interactúe con los servicios existentes y cumpla con los requerimientos de negocio detallados a continuación.

## 4. Requerimientos Funcionales

### RF1. Asignación de Vehículos a Propietarios

- El servicio debe permitir asociar uno o varios vehículos a un propietario.
- **Claves Compuestas**: La persistencia de la relación de propiedad debe implementarse obligatoriamente utilizando una clave compuesta conformada por el identificador único del usuario (`user_id`) y el identificador único del vehículo (`vehicle_id`).
- Un vehículo solo puede estar asignado a un propietario activo a la vez.

### RF2. Registro de Trazabilidad (Auditoría)

- Cada vez que se cree, modifique o elimine una asignación de vehículo, el sistema debe registrar de forma automática un evento de trazabilidad.
- El registro de auditoría debe guardarse en una entidad/colección separada y debe contener al menos:
  - ID del evento.
  - Clave compuesta afectada (`user_id` y `vehicle_id`).
  - Tipo de acción (CREACIÓN, MODIFICACIÓN, ELIMINACIÓN).
  - Timestamp (fecha y hora exacta con zona horaria).
  - Estado o payload del cambio (datos anteriores vs. datos nuevos, si aplica).

### RF3. Consulta de Flota por Propietario

- Exponer un endpoint que, dado el ID de un propietario, retorne la lista de sus vehículos asignados, detallando el tipo (moto, automóvil, etc.) y la categoría (eléctrico, híbrido, combustión).
- Nota: Esta información requerirá una comunicación o agregación de datos con el Microservicio de Vehículos existente.

## 5. Criterios de Evaluación

- **Modelado de Datos**: Correcta implementación y manejo de la clave compuesta en la capa de persistencia.
- **Diseño de la Trazabilidad**: Robustez y desacoplamiento del registro de auditoría (por ejemplo, mediante patrones como Interceptores, AOP, o Eventos de Dominio).
- **Integración y Arquitectura**: Estrategia para comunicarse con los microservicios preexistentes de usuarios y vehículos.
- **Calidad de Código y Pruebas**: Legibilidad, buenas prácticas (Clean Code/SOLID) y cobertura de pruebas.
