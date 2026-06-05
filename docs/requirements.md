# Requirements

### Zone

- RF-01 - List zones
- RF-02 - Create zone (name, description, type, capacity)
  - Unique name validation
  - Auto-generate code
  - enabled = true
  - Update createdAt and updatedAt
- RF-03 - Update zone (name, description, type, capacity)
  - Unique name validation
  - Capacity change validation
  - Auto-generate code (only if type changes)
  - Update updatedAt
- RF-04 - Toggle enabled
  - Validate spaces for cascade propagation
  - Propagate to spaces in cascade
  - Update updatedAt

### Space

- RF-05 - List spaces
- RF-06 - Create space (zoneId, description, type)
  - Validate zoneId exists
  - Validate zone capacity
  - Auto-generate code
  - status = AVAILABLE
  - enabled = true
  - Update createdAt and updatedAt
- RF-07 - Update space (description, type, status)
  - Validate zone does not change
  - Validate status change
  - Update updatedAt
- RF-08 - Toggle enabled
  - Validate status
  - Validate zone enabled
  - Update updatedAt
- RF-09 - Delete space

### Business Rules

- **Zone creation**: Validates unique name, auto-generates code (`{TYPE}-{NN}`), sets enabled = true.
- **Zone update**: Cannot change type if it has associated spaces. Capacity cannot be less than current spaces count.
- **Zone toggle**: Cannot disable a zone with occupied spaces. Disabling/enabling propagates to all its spaces.
- **Space creation**: Validates zone existence and capacity. Auto-generates code (`{ZONE_TYPE}-{ZONE_NUM}-{NNN}`). Sets status = `AVAILABLE` and enabled = true.
- **Space update**: Cannot change the parent zone. Cannot occupy a disabled space.
- **Space toggle**: Cannot disable an occupied space. Cannot enable a space whose zone is disabled.
- **Space deletion**: Cannot delete an occupied space.