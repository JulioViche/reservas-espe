# Requirements

### Person

- RF-01 - List persons
- RF-02 - Get person by ID
- RF-03 - Create person
  - Unique email validation
  - Auto-generate username from name
  - Auto-generate alphanumeric password with special characters
- RF-04 - Update person
  - Unique email validation (exclude current)
- RF-05 - Toggle active

### User

- RF-06 - List users
- RF-07 - Get user by ID
- RF-08 - Assign role to user
  - Validate user exists
  - Validate role exists
  - Prevent duplicate assignment
- RF-09 - Remove role from user
- RF-10 - Toggle user active

### Role

- RF-11 - List roles
- RF-12 - Get role by ID
- RF-13 - Create role
  - Unique name validation
- RF-14 - Update role
  - Unique name validation (exclude current)
- RF-15 - Toggle role active

### Business Rules

- **Person creation**: Automatically creates an associated `User` with auto-generated username and password.
- **Username format**: `{firstLetter}{middleLetter}{lastName}` (e.g., `jeviche`). If the username already exists, appends a numeric suffix (`jeviche2`, `jeviche3`).
- **Password generation**: 14 characters, alphanumeric + special characters, no spaces, generated with `SecureRandom`.
- **One-to-one**: Each person corresponds to exactly one user (shared primary key via `@MapsId`).
- **Many-to-many**: Users can have multiple roles; roles can be assigned to multiple users.
