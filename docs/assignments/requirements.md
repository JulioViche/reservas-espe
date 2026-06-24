# Requirements

### Assignment

- RF-01 - Assign vehicle to owner (userId + vehicleId)
  - Validate both UUIDs exist
  - Vehicle must not already be actively assigned
  - Sets `assignedAt` = now, `isActive` = true
- RF-02 - Modify assignment
  - Toggle `isActive` (unassign)
  - If setting `isActive = false`, auto-sets `unassignedAt`
  - Logs modification in audit trail
- RF-03 - Delete assignment (soft, sets `isActive = false`)
  - Only active assignments can be "deleted"
  - Sets `unassignedAt` = now
- RF-04 - Get fleet by owner
  - Returns all active assignments for a userId
  - Enriches each assignment with vehicle details (plate, brand, model, type, category) via vehicles API
- RF-05 - Get audit trail for an assignment
  - Returns ordered history of modifications

### Business Rules

- **One vehicle, one owner**: A vehicle can only be actively assigned to one owner at a time. Creating a new active assignment for an already-assigned vehicle returns `409 Conflict`.
- **Soft unassign**: Removing or "deleting" an assignment does not physically delete the row — it sets `isActive = false` and `unassignedAt`.
- **Fleet enrichment**: The fleet endpoint calls the vehicles microservice internally to fetch full vehicle details.
- **Audit trail**: Every modification (`PATCH`) to an assignment logs the previous and new state in `audit_logs` for traceability.
- **Composite PK**: The `assignments` table uses a composite primary key (`userId`, `vehicleId`).
