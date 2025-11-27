# API Documentation

## Base URL
```
http://localhost:8080
```

## Available APIs

### Authentication
- [Authentication.md](Authentication.md) - Register, Login, Get Current User

### Store Management
- [Store.md](Store.md) - Create Store, Get All Stores, Get Store by ID

### Category Management
- [Category.md](Category.md) - CRUD operations for Categories

## Quick Start

1. **Register a user:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"test123","organizationName":"Test Corp"}'
```

2. **Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"test123"}'
```

3. **Use the token from response for authenticated requests:**
```bash
curl http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

## Authentication
All protected endpoints require JWT token in Authorization header:
```
Authorization: Bearer <token>
```

## Response Format
All responses follow this structure:
```json
{
  "success": true/false,
  "data": { ... },
  "message": "Success/error message",
  "error": "Error details (if failed)",
  "timestamp": 1700000000000
}
```

## Timestamps
All timestamps are in epoch milliseconds format:
```
1700000000000 = Nov 15, 2023
```

