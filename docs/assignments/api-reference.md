# API Reference

Base URL: `http://localhost:3000`

> **Note:** El gateway unifica todos los microservicios bajo `localhost:3000`.

## Assignments

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `POST` | `/assignments` | Assign a vehicle to an owner | 201 |
| `PATCH` | `/assignments/{userId}/{vehicleId}` | Modify assignment (unassign) | 200 |
| `DELETE` | `/assignments/{userId}/{vehicleId}` | Remove assignment (soft) | 204 |
| `GET` | `/assignments/owner/{userId}` | Get fleet by owner | 200 |
| `GET` | `/assignments/audit/{userId}/{vehicleId}` | Get audit trail | 200 |

### Create Assignment

```http
POST /assignments
Content-Type: application/json

{
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "vehicleId": "123e4567-e89b-12d3-a456-426614174001"
}
```

Response: `201 Created`

```json
{
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "vehicleId": "123e4567-e89b-12d3-a456-426614174001",
    "assignedAt": "2026-06-24T12:00:00.000Z",
    "unassignedAt": null,
    "isActive": true
}
```

### Update Assignment (Unassign)

```http
PATCH /assignments/{userId}/{vehicleId}
Content-Type: application/json

{
    "isActive": false
}
```

Response: `200 OK`

```json
{
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "vehicleId": "123e4567-e89b-12d3-a456-426614174001",
    "assignedAt": "2026-06-24T12:00:00.000Z",
    "unassignedAt": "2026-06-24T14:00:00.000Z",
    "isActive": false
}
```

### Delete Assignment

```http
DELETE /assignments/{userId}/{vehicleId}
```

Response: `204 No Content`

> **Note:** Deletion is soft — sets `isActive = false` and `unassignedAt`.

### Get Fleet by Owner

```http
GET /assignments/owner/{userId}
```

Response: `200 OK`

```json
{
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "vehicles": [
        {
            "id": "123e4567-e89b-12d3-a456-426614174001",
            "plate": "ABC-1234",
            "brand": "Toyota",
            "model": "Corolla",
            "type": "car",
            "category": "hybrid"
        }
    ]
}
```

### Get Audit Trail

```http
GET /assignments/audit/{userId}/{vehicleId}
```

Response: `200 OK`

```json
[
    {
        "id": "uuid",
        "userId": "123e4567-e89b-12d3-a456-426614174000",
        "vehicleId": "123e4567-e89b-12d3-a456-426614174001",
        "action": "MODIFICATION",
        "timestamp": "2026-06-24T14:00:00.000Z",
        "previousState": {
            "isActive": true,
            "unassignedAt": null
        },
        "newState": {
            "isActive": false,
            "unassignedAt": "2026-06-24T14:00:00.000Z"
        }
    }
]
```

### Validation Error

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
    "message": [
        "userId must be a valid UUID",
        "vehicleId must be a valid UUID"
    ],
    "error": "Bad Request",
    "statusCode": 400
}
```
