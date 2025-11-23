# Authentication API

Base URL: `http://localhost:8080/api/auth`

---

## 1. Register User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "organizationName": "Acme Corp",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890"
  }'
```

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "organizationName": "Acme Corp",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "1234567890"
}
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890",
    "orgId": 1,
    "organizationName": "Acme Corp",
    "verified": false,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "role": "USER"
  },
  "message": "User registered successfully",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Email already registered",
  "message": "Registration failed",
  "timestamp": 1700000000000
}
```

---

## 2. Login User

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890",
    "orgId": 1,
    "organizationName": "Acme Corp",
    "verified": false,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "role": "USER"
  },
  "message": "Login successful",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Invalid credentials",
  "message": "Invalid email or password",
  "timestamp": 1700000000000
}
```

---

## 3. Get Current User

```bash
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890",
    "orgId": 1,
    "role": "USER",
    "isActive": true,
    "verified": false,
    "createdAt": 1700000000000,
    "updatedAt": 1700000000000
  },
  "message": "User details retrieved successfully",
  "timestamp": 1700000000000
}
```

**Error (401):**
```json
{
  "success": false,
  "error": "Unauthorized",
  "message": "Invalid or expired token"
}
```

