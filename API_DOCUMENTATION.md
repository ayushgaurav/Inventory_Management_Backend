# Inventory Management API Documentation

## Authentication APIs

Base URL: `http://localhost:8080/api/auth`

### 1. Register User

**Endpoint:** `POST /api/auth/register`

**Description:** Register a new user account

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "1234567890"
}
```

**Validation Rules:**
- `email`: Required, must be a valid email format
- `password`: Required, minimum 6 characters
- `firstName`: Optional, maximum 50 characters
- `lastName`: Optional, maximum 50 characters
- `phoneNumber`: Optional, maximum 15 characters

**Success Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "role": "USER"
  },
  "message": "User registered successfully",
  "timestamp": 1700000000000
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "data": null,
  "message": "Email already registered",
  "timestamp": 1700000000000
}
```

---

### 2. Login User

**Endpoint:** `POST /api/auth/login`

**Description:** Login with existing credentials

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Validation Rules:**
- `email`: Required
- `password`: Required

**Success Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "role": "USER"
  },
  "message": "Login successful",
  "timestamp": 1700000000000
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "data": null,
  "message": "Invalid email or password",
  "timestamp": 1700000000000
}
```

---

### 3. Get Current User

**Endpoint:** `GET /api/auth/me`

**Description:** Get current logged-in user details

**Headers:**
```
Authorization: Bearer <JWT_TOKEN>
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890",
    "role": "USER",
    "isActive": true,
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  },
  "message": "User details retrieved successfully",
  "timestamp": 1700000000000
}
```

---

### 4. Test Endpoint

**Endpoint:** `GET /api/auth/test`

**Description:** Test if the authentication controller is working

**Success Response (200 OK):**
```json
{
  "success": true,
  "data": "Auth controller is working!",
  "message": "Test endpoint successful",
  "timestamp": 1700000000000
}
```

---

## cURL Examples

### Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

### Get Current User
```bash
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## Project Structure

```
src/main/java/com/inventory_management/
├── authentication/
│   ├── controller/
│   │   └── AuthController.java
│   ├── dto/
│   │   ├── AuthResponse.java
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   ├── entity/
│   │   └── User.java
│   ├── repository/
│   │   └── UserRepository.java
│   ├── security/
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── JwtUtils.java
│   │   └── SecurityConfig.java
│   └── service/
│       ├── AuthService.java
│       └── CustomUserDetailsService.java
└── common/
    ├── config/
    │   └── CorsConfig.java
    └── response/
        └── ApiResponseWrapper.java
```

---

## Database Configuration

Update `application.properties` with your MySQL database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_management_db?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

The database tables will be created automatically on first run.

---

## Security Configuration

- JWT tokens expire after 24 hours (configurable in `application.properties`)
- Passwords are encrypted using BCrypt
- CORS is enabled for localhost:3000, localhost:4200, and localhost:5173
- All authentication endpoints (`/api/auth/**`) are public
- All other endpoints require authentication

---

## Running the Application

1. Make sure MySQL is running
2. Update database credentials in `application.properties`
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`

