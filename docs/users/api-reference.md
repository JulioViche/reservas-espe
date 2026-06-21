# API Reference

Base URL: `http://localhost:8081/api/v1`

## Persons

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `GET` | `/persons` | List all persons | 200 |
| `GET` | `/persons/{id}` | Get person by ID | 200 |
| `POST` | `/persons` | Create a person (auto-generates user) | 201 |
| `PUT` | `/persons/{id}` | Update a person | 200 |
| `PATCH` | `/persons/{id}/active` | Toggle person active/inactive | 204 |

### Create Person

```http
POST /api/v1/persons
Content-Type: application/json

{
    "dni": "1234567890",
    "firstName": "Julio",
    "middleName": "Enrique",
    "lastName": "Viche",
    "email": "julio.viche@example.com",
    "phone": "0999999999",
    "address": "Quito, Ecuador",
    "nationality": "Ecuatoriana",
    "active": true
}
```

Response: `201 Created`

```json
{
    "person": {
        "id": "uuid",
        "dni": "1234567890",
        "firstName": "Julio",
        "middleName": "Enrique",
        "lastName": "Viche",
        "email": "julio.viche@example.com",
        "phone": "0999999999",
        "address": "Quito, Ecuador",
        "nationality": "Ecuatoriana",
        "active": true,
        "createdAt": "2026-06-21T14:00:00",
        "updatedAt": "2026-06-21T14:00:00"
    },
    "username": "jeviche",
    "generatedPassword": "aK9$xR2!mN5@pQ"
}
```

> **Note:** The `username` is auto-generated (`{firstLetter}{middleLetter}{lastName}`). The `generatedPassword` is returned only once at creation.

### Update Person

```http
PUT /api/v1/persons/{id}
Content-Type: application/json

{
    "dni": "1234567890",
    "firstName": "Julio",
    "middleName": "Enrique",
    "lastName": "Viche",
    "email": "julio.nuevo@example.com",
    "phone": "0998888888",
    "address": "Quito, Ecuador",
    "nationality": "Ecuatoriana",
    "active": true
}
```

## Users

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `GET` | `/users` | List all users | 200 |
| `GET` | `/users/{id}` | Get user by ID | 200 |
| `POST` | `/users/{id}/roles` | Assign role to user | 201 |
| `DELETE` | `/users/{id}/roles/{roleId}` | Remove role from user | 204 |
| `PATCH` | `/users/{id}/active` | Toggle user active/inactive | 204 |

### Assign Role

```http
POST /api/v1/users/{id}/roles
Content-Type: application/json

{
    "idRole": "uuid-of-role"
}
```

### Remove Role

```http
DELETE /api/v1/users/{id}/roles/{roleId}
```

## Roles

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `GET` | `/roles` | List all roles | 200 |
| `GET` | `/roles/{id}` | Get role by ID | 200 |
| `POST` | `/roles` | Create a role | 201 |
| `PUT` | `/roles/{id}` | Update a role | 200 |
| `PATCH` | `/roles/{id}/active` | Toggle role active/inactive | 204 |

### Create Role

```http
POST /api/v1/roles
Content-Type: application/json

{
    "name": "ADMIN",
    "description": "Administrator role with full access",
    "active": true
}
```

### Update Role

```http
PUT /api/v1/roles/{id}
Content-Type: application/json

{
    "name": "ADMIN",
    "description": "Updated description",
    "active": true
}
```
