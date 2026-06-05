# Data Model

```mermaid
erDiagram
    direction LR

    Zone {
        UUID id PK
        String code UK
        String name UK
        String description
        ZoneType type
        int capacity
        boolean enabled
        LocalDateTime createdAt
        LocalDateTime updatedAt
    }

    Space {
        UUID id PK
        String code UK
        String description
        SpaceType type
        SpaceStatus status
        boolean enabled
        LocalDateTime createdAt
        LocalDateTime updatedAt
    }

    Zone ||--o{ Space : has
```

## Enums

#### ZoneType
`VIP`, `REGULAR`, `INTERNAL`, `EXTERNAL`, `PREFERENTIAL`

#### SpaceType
`CAR`, `BIKE`, `TRUCK`

#### SpaceStatus
`AVAILABLE`, `OCCUPIED`, `RESERVED`, `MAINTENANCE`
