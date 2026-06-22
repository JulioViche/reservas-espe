# API Reference

Base URL: `http://localhost:3000`

## Vehicles

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `GET` | `/vehicles` | List all vehicles | 200 |
| `GET` | `/vehicles/{id}` | Get vehicle by ID | 200 |
| `POST` | `/vehicles` | Create a vehicle | 201 |
| `PATCH` | `/vehicles/{id}` | Update a vehicle | 200 |
| `DELETE` | `/vehicles/{id}` | Soft delete a vehicle | 204 |

### Create Vehicle

```http
POST /vehicles
Content-Type: application/json

{
    "type": "car",
    "data": {
        "plate": "ABC-1234",
        "brand": "Toyota",
        "model": "Corolla",
        "year": 2024,
        "color": "Rojo",
        "classification": "gasoline",
        "doors": 4,
        "trunkCapacity": 470
    }
}
```

Response: `201 Created`

```json
{
    "id": "uuid",
    "plate": "ABC-1234",
    "brand": "Toyota",
    "model": "Corolla",
    "year": 2024,
    "color": "Rojo",
    "classification": "gasoline",
    "type": "car",
    "doors": 4,
    "trunkCapacity": 470
}
```

### Create Motorcycle

```http
POST /vehicles
Content-Type: application/json

{
    "type": "motorcycle",
    "data": {
        "plate": "AB-123C",
        "brand": "Yamaha",
        "model": "MT-07",
        "year": 2025,
        "color": "Negro",
        "classification": "gasoline",
        "type": "sport"
    }
}
```

### Create Truck

```http
POST /vehicles
Content-Type: application/json

{
    "type": "truck",
    "data": {
        "plate": "DEF-5678",
        "brand": "Volvo",
        "model": "FH16",
        "year": 2023,
        "color": "Blanco",
        "classification": "diesel",
        "cargoCapacity": 40000
    }
}
```

### Update Vehicle

Solo se pueden modificar los campos comunes (no los específicos de cada tipo).

```http
PATCH /vehicles/{id}
Content-Type: application/json

{
    "plate": "XYZ-9876",
    "brand": "Mazda",
    "model": "3",
    "year": 2025,
    "color": "Gris",
    "classification": "gasoline"
}
```

### Validation Error

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
    "message": [
        "Year must be no later than 2027",
        "Color must be at least 4 characters long"
    ],
    "error": "Bad Request",
    "statusCode": 400
}
```

### List Vehicles

```http
GET /vehicles
```

Response: `200 OK`

```json
[
    {
        "id": "uuid",
        "plate": "ABC-1234",
        "brand": "Toyota",
        "model": "Corolla",
        "year": 2024,
        "color": "Rojo",
        "classification": "gasoline",
        "type": "car",
        "doors": 4,
        "trunkCapacity": 470
    }
]
```

### Delete Vehicle

```http
DELETE /vehicles/{id}
```

Response: `204 No Content`

> **Note:** Deletion is soft (sets `deletedAt` timestamp). The record is not physically removed.
