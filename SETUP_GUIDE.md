# Setup Guide - Inventory Management Authentication System

## Overview

This project implements a complete authentication system following the folder structure from the abussl_backend project. The authentication module includes user registration, login, and JWT-based authentication.

---

## ✅ What Has Been Implemented

### 1. **Folder Structure**
Following the reference project pattern:
```
authentication/
├── controller/     - REST API endpoints
├── dto/           - Data Transfer Objects
├── entity/        - Database entities
├── repository/    - JPA repositories
├── security/      - JWT and Security configuration
└── service/       - Business logic

common/
├── config/        - Application configurations
└── response/      - Standard API response wrapper
```

### 2. **Features Implemented**

✅ **User Registration API** (`POST /api/auth/register`)
- Email validation
- Password encryption
- Phone number validation
- Duplicate user check
- JWT token generation

✅ **User Login API** (`POST /api/auth/login`)
- Email/password authentication
- JWT token generation
- User details response

✅ **Get Current User** (`GET /api/auth/me`)
- Protected endpoint
- Requires JWT token
- Returns user profile

✅ **Test Endpoint** (`GET /api/auth/test`)
- Public endpoint to verify setup

### 3. **Security Features**

✅ JWT Authentication
- Token-based authentication
- 24-hour token expiration
- Secure token generation with HS256

✅ Password Encryption
- BCrypt password hashing
- Secure password storage

✅ CORS Configuration
- Enabled for common development ports
- Configurable origins

✅ Role-Based Access Control
- USER and ADMIN roles
- Extensible permission system

---

## 🚀 Quick Start

### Prerequisites
1. Java 21
2. Maven
3. MySQL (or H2 for testing)

### Step 1: Database Setup

**Option A: MySQL (Recommended for Production)**
```sql
CREATE DATABASE inventory_management_db;
```

Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_management_db?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**Option B: H2 (Quick Testing)**
Uncomment H2 configuration in `application.properties`:
```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Step 2: Install Dependencies
```bash
./mvnw clean install
```

### Step 3: Run Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

---

## 🧪 Testing the APIs

### 1. Test the Server
```bash
curl http://localhost:8080/api/auth/test
```

Expected response:
```json
{
  "success": true,
  "data": "Auth controller is working!",
  "message": "Test endpoint successful",
  "timestamp": 1700000000000
}
```

### 2. Register a New User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890"
  }'
```

Expected response:
```json
{
  "success": true,
  "data": {
    "userId": 1,
    "email": "john.doe@example.com",
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

### 3. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "password123"
  }'
```

### 4. Get Current User (Protected Endpoint)
```bash
# Replace YOUR_JWT_TOKEN with the token received from register/login
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 📁 Project Files Created

### Authentication Module
- ✅ `AuthController.java` - REST API endpoints
- ✅ `RegisterRequest.java` - Registration DTO
- ✅ `LoginRequest.java` - Login DTO
- ✅ `AuthResponse.java` - Authentication response DTO
- ✅ `User.java` - User entity with JPA annotations
- ✅ `UserRepository.java` - JPA repository interface
- ✅ `AuthService.java` - Authentication business logic
- ✅ `CustomUserDetailsService.java` - Spring Security user details
- ✅ `JwtUtils.java` - JWT token generation and validation
- ✅ `JwtAuthenticationFilter.java` - JWT request filter
- ✅ `SecurityConfig.java` - Spring Security configuration

### Common Module
- ✅ `ApiResponseWrapper.java` - Standard API response format
- ✅ `CorsConfig.java` - CORS configuration

### Configuration
- ✅ `pom.xml` - Updated with required dependencies
- ✅ `application.properties` - Database and JWT configuration

### Documentation
- ✅ `API_DOCUMENTATION.md` - Complete API documentation
- ✅ `SETUP_GUIDE.md` - This file

---

## 🔧 Configuration Options

### JWT Settings
Modify in `application.properties`:
```properties
# Secret key for JWT (should be changed in production)
jwt.secret=mySecretKeyForJWTTokenGenerationThatIsLongEnoughForHS256Algorithm

# Token expiration (in milliseconds) - default 24 hours
jwt.expiration=86400000
```

### CORS Settings
Modify `CorsConfig.java` to add/remove allowed origins:
```java
configuration.setAllowedOrigins(Arrays.asList(
    "http://localhost:3000",    // React
    "http://localhost:4200",    // Angular
    "http://localhost:5173"     // Vite
));
```

### Database Settings
Change in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## 🔐 Security Best Practices

1. **Change JWT Secret in Production**
   - Generate a strong random secret
   - Store in environment variables
   - Don't commit to version control

2. **Use HTTPS in Production**
   - All authentication endpoints should use HTTPS
   - Configure SSL certificates

3. **Database Security**
   - Use strong database passwords
   - Restrict database access
   - Use connection pooling

4. **Token Management**
   - Implement token refresh mechanism
   - Add token blacklisting for logout
   - Consider shorter token expiration for sensitive operations

---

## 📚 Dependencies Added

The following dependencies were added to `pom.xml`:

- `spring-boot-starter-security` - Spring Security framework
- `spring-boot-starter-validation` - Bean validation
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson` - JWT library (v0.12.3)
- `lombok` (v1.18.30) - Reduce boilerplate code
- `h2` - In-memory database for testing

---

## 🐛 Troubleshooting

### Issue: Port 8080 already in use
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Database connection failed
**Solution:** 
1. Verify MySQL is running: `mysql -u root -p`
2. Check credentials in `application.properties`
3. Ensure database exists

### Issue: 401 Unauthorized on protected endpoints
**Solution:**
1. Verify JWT token is included in Authorization header
2. Check token format: `Bearer <token>`
3. Ensure token hasn't expired

### Issue: Bean creation errors
**Solution:**
1. Clean and rebuild: `./mvnw clean install`
2. Delete `target/` folder
3. Reimport Maven dependencies in IDE

---

## 📝 Next Steps

1. **Add More Endpoints**
   - Password reset
   - Email verification
   - Phone number verification
   - Profile update

2. **Enhance Security**
   - Implement refresh tokens
   - Add rate limiting
   - Implement account lockout

3. **Add User Management**
   - Admin endpoints for user management
   - User roles and permissions
   - User activity logging

4. **Testing**
   - Unit tests for services
   - Integration tests for controllers
   - Security tests

5. **Create Inventory Module**
   - Product management
   - Stock tracking
   - Order management

---

## 📞 Support

For issues or questions, refer to:
- API Documentation: `API_DOCUMENTATION.md`
- Spring Security Docs: https://docs.spring.io/spring-security/reference/
- JWT.io: https://jwt.io/

---

**✨ Your authentication system is now ready to use! ✨**

