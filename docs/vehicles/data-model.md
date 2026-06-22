# Data Model

```mermaid
erDiagram
    VEHICLE {
        uuid id PK
        string plate UK
        string brand
        string model
        int year
        string color
        enum classification "electric | hybrid | gasoline | diesel"
        string vehicleType "discriminator: car | motorcycle | truck"
        timestamp deletedAt "soft delete"
    }

    VEHICLE ||--o| CAR : is-a
    VEHICLE ||--o| MOTORCYCLE : is-a
    VEHICLE ||--o| TRUCK : is-a

    CAR {
        int doors "inherited from Vehicle"
        int trunkCapacity "inherited from Vehicle"
    }

    MOTORCYCLE {
        enum type "sport | motocross | scooter"
    }

    TRUCK {
        int cargoCapacity "inherited from Vehicle"
    }
```

## Single Table Inheritance

All types share the `vehicle` table. The `vehicleType` column discriminates between `car`, `motorcycle`, and `truck`.

| Column | Type | Constraints |
|--------|------|-------------|
| `id` | UUID | PK, auto-generated |
| `plate` | VARCHAR | Unique, not null |
| `brand` | VARCHAR | Not null |
| `model` | VARCHAR | Not null |
| `year` | INTEGER | Not null |
| `color` | VARCHAR | Not null |
| `classification` | ENUM | `electric`, `hybrid`, `gasoline`, `diesel` |
| `vehicleType` | VARCHAR | Discriminator: `car`, `motorcycle`, `truck` |
| `doors` | INTEGER | Nullable (car only) |
| `trunkCapacity` | INTEGER | Nullable (car only) |
| `type` | ENUM | Nullable (motorcycle only): `sport`, `motocross`, `scooter` |
| `cargoCapacity` | INTEGER | Nullable (truck only) |
| `deletedAt` | TIMESTAMP | Nullable, soft delete |

## Enums

#### Classification
`electric`, `hybrid`, `gasoline`, `diesel`

#### MotorcycleType (`type` column)
`sport`, `motocross`, `scooter`
