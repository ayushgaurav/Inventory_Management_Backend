# Store API

Base URL: `http://localhost:8080/api/stores`

**Note:** All endpoints require JWT token in Authorization header

---

## 1. Create Store

```bash
curl -X POST http://localhost:8080/api/stores \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Main Warehouse",
    "locationType": "WAREHOUSE",
    "managerName": "John Manager",
    "fullAddress": "123 Main St, City, State 12345",
    "phoneNumber": "1234567890",
    "email": "warehouse@example.com",
    "notes": "Main storage facility"
  }'
```

**Request:**
```json
{
  "name": "Main Warehouse",
  "locationType": "WAREHOUSE",
  "managerName": "John Manager",
  "fullAddress": "123 Main St, City, State 12345",
  "phoneNumber": "1234567890",
  "email": "warehouse@example.com",
  "notes": "Main storage facility"
}
```

**Location Types:**
- `RETAIL_STORE`
- `WAREHOUSE`
- `DISTRIBUTION_CENTER`

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "Main Warehouse",
    "locationType": "WAREHOUSE",
    "managerName": "John Manager",
    "fullAddress": "123 Main St, City, State 12345",
    "phoneNumber": "1234567890",
    "email": "warehouse@example.com",
    "notes": "Main storage facility",
    "orgId": 1,
    "isActive": true,
    "createdAt": 1700000000000,
    "updatedAt": 1700000000000
  },
  "message": "Store created successfully",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Store with this name already exists in your organization",
  "message": "Failed to create store",
  "timestamp": 1700000000000
}
```

---

## 2. Get All Stores

```bash
curl -X GET http://localhost:8080/api/stores \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "Main Warehouse",
      "locationType": "WAREHOUSE",
      "managerName": "John Manager",
      "fullAddress": "123 Main St, City, State 12345",
      "phoneNumber": "1234567890",
      "email": "warehouse@example.com",
      "notes": "Main storage facility",
      "orgId": 1,
      "isActive": true,
      "createdAt": 1700000000000,
      "updatedAt": 1700000000000
    },
    {
      "id": 2,
      "name": "Downtown Store",
      "locationType": "RETAIL_STORE",
      "managerName": "Jane Store",
      "fullAddress": "456 Downtown Ave, City, State 12346",
      "phoneNumber": "0987654321",
      "email": "downtown@example.com",
      "notes": null,
      "orgId": 1,
      "isActive": true,
      "createdAt": 1700000000000,
      "updatedAt": 1700000000000
    }
  ],
  "message": "Stores retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 3. Get Active Stores Only

```bash
curl -X GET http://localhost:8080/api/stores/active \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "Main Warehouse",
      "locationType": "WAREHOUSE",
      "isActive": true,
      ...
    }
  ],
  "message": "Active stores retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 4. Get Store by ID

```bash
curl -X GET http://localhost:8080/api/stores/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "Main Warehouse",
    "locationType": "WAREHOUSE",
    "managerName": "John Manager",
    "fullAddress": "123 Main St, City, State 12345",
    "phoneNumber": "1234567890",
    "email": "warehouse@example.com",
    "notes": "Main storage facility",
    "orgId": 1,
    "isActive": true,
    "createdAt": 1700000000000,
    "updatedAt": 1700000000000
  },
  "message": "Store retrieved successfully",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Store not found or access denied",
  "message": "Failed to retrieve store",
  "timestamp": 1700000000000
}
```


