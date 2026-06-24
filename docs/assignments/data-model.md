# Data Model

```mermaid
erDiagram
    ASSIGNMENT {
        uuid userId PK
        uuid vehicleId PK
        timestamp assignedAt
        timestamp unassignedAt "nullable"
        boolean isActive
    }

    AUDIT_LOG {
        uuid id PK
        uuid userId
        uuid vehicleId
        enum action "CREATION | MODIFICATION | ELIMINATION"
        timestamp timestamp
        json previousState "nullable"
        json newState "nullable"
    }

    ASSIGNMENT ||--o{ AUDIT_LOG : registra
```

## Tables

### ASSIGNMENT

| Column | Type | Constraints |
|--------|------|-------------|
| `userId` | UUID | PK, not null |
| `vehicleId` | UUID | PK, not null |
| `assignedAt` | TIMESTAMP WITH TIME ZONE | Default `NOW()` |
| `unassignedAt` | TIMESTAMP WITH TIME ZONE | Nullable |
| `isActive` | BOOLEAN | Default `true` |

Composite primary key: (`userId`, `vehicleId`).

### AUDIT_LOG

| Column | Type | Constraints |
|--------|------|-------------|
| `id` | UUID | PK, auto-generated |
| `userId` | UUID | Not null |
| `vehicleId` | UUID | Not null |
| `action` | ENUM | `CREATION`, `MODIFICATION`, `ELIMINATION` |
| `timestamp` | TIMESTAMP WITH TIME ZONE | Default `NOW()` |
| `previousState` | JSONB | Nullable |
| `newState` | JSONB | Nullable |

## Enums

#### AuditAction
`CREATION`, `MODIFICATION`, `ELIMINATION`
