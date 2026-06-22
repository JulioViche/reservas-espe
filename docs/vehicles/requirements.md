# Requirements

### Vehicle

- RF-01 - List vehicles
- RF-02 - Get vehicle by ID
- RF-03 - Create vehicle (type, plate, brand, model, year, color, classification + type-specific fields)
  - Unique plate validation
  - Plate format validation (`ABC-1234` for cars/trucks, `AB-123C` for motorcycles)
  - Year validation (1885 to current year + 1)
  - Classification must be a valid enum value
  - Dynamic DTO validation based on `type` (car/motorcycle/truck)
- RF-04 - Update vehicle (partial update of any field)
  - Validate vehicle exists
  - Plate uniqueness preserved on update
- RF-05 - Delete vehicle (soft delete)

### Business Rules

- **Vehicle creation**: Requires `type` (car/motorcycle/truck) and `data` object with type-specific fields.
  - **Car**: `plate`, `brand`, `model`, `year`, `color`, `classification`, `doors` (2-5), `trunkCapacity` (0-10000 L)
  - **Motorcycle**: `plate` (`AB-123C`), `brand`, `model`, `year`, `color`, `classification`, `type` (sport/motocross/scooter)
  - **Truck**: `plate`, `brand`, `model`, `year`, `color`, `classification`, `cargoCapacity` (0-100000 kg)
- **Plate uniqueness**: Plates are unique across all vehicle types (single table).
- **Soft delete**: Vehicles are soft-deleted; `deletedAt` is set on removal. Queries automatically exclude soft-deleted records.
- **Classification**: Used for future discount policies (electric, hybrid, gasoline, diesel).
- **Inheritance**: Uses Single Table Inheritance via TypeORM. All vehicle types share the `vehicle` table, with a `type` discriminator column.
