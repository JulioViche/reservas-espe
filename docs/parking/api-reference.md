# API Reference

Base URL: `http://localhost:8080/api/v1`

## Zones

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `GET` | `/zones` | List all zones | 200 |
| `GET` | `/zones/{id}` | Get zone by ID | 200 |
| `POST` | `/zones` | Create a zone | 201 |
| `PUT` | `/zones/{id}` | Update a zone | 200 |
| `PATCH` | `/zones/{id}/status` | Toggle zone enabled/disabled | 204 |

### Create Zone

```http
POST /api/v1/zones
Content-Type: application/json

{
    "name": "VIP Zone",
    "description": "Exclusive VIP parking",
    "type": "VIP",
    "capacity": 10
}
```

### Update Zone

```http
PUT /api/v1/zones/{id}
Content-Type: application/json

{
    "name": "VIP Zone North",
    "description": "Updated VIP zone",
    "type": "VIP",
    "capacity": 15
}
```

## Spaces

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `GET` | `/spaces` | List all spaces | 200 |
| `GET` | `/spaces/{id}` | Get space by ID | 200 |
| `POST` | `/spaces` | Create a space | 201 |
| `PUT` | `/spaces/{id}` | Update a space | 200 |
| `PATCH` | `/spaces/{id}/status` | Toggle space enabled/disabled | 204 |
| `DELETE` | `/spaces/{id}` | Delete a space | 204 |

### Create Space

```http
POST /api/v1/spaces
Content-Type: application/json

{
    "zoneId": "uuid-of-existing-zone",
    "description": "Space 1",
    "type": "CAR",
    "status": "AVAILABLE"
}
```

### Update Space

```http
PUT /api/v1/spaces/{id}
Content-Type: application/json

{
    "zoneId": "uuid-of-same-zone",
    "description": "Updated space",
    "type": "BIKE",
    "status": "AVAILABLE"
}
```

> **Note:** The zone cannot be changed once a space is created.