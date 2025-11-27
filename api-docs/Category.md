# Category API

Base URL: `http://localhost:8080/api/categories`

**Note:** All endpoints require JWT token in Authorization header

---

## 1. Create Category

```bash
curl -X POST http://localhost:8080/api/categories \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Electronics",
    "description": "Electronic devices and components"
  }'
```

**Request:**
```json
{
  "name": "Electronics",
  "description": "Electronic devices and components"
}
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "Electronics",
    "description": "Electronic devices and components",
    "orgId": 1,
    "isActive": true,
    "createdAt": 1700000000000,
    "updatedAt": 1700000000000
  },
  "message": "Category created successfully",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Category with this name already exists in your organization",
  "message": "Failed to create category",
  "timestamp": 1700000000000
}
```

---

## 2. Get All Categories

```bash
curl -X GET http://localhost:8080/api/categories \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "Electronics",
      "description": "Electronic devices and components",
      "orgId": 1,
      "isActive": true,
      "createdAt": 1700000000000,
      "updatedAt": 1700000000000
    },
    {
      "id": 2,
      "name": "Furniture",
      "description": "Office and workspace furniture",
      "orgId": 1,
      "isActive": true,
      "createdAt": 1700000000000,
      "updatedAt": 1700000000000
    }
  ],
  "message": "Categories retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 3. Get Active Categories Only

```bash
curl -X GET http://localhost:8080/api/categories/active \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "Electronics",
      "description": "Electronic devices and components",
      "isActive": true,
      ...
    }
  ],
  "message": "Active categories retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 4. Get Category by ID

```bash
curl -X GET http://localhost:8080/api/categories/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "Electronics",
    "description": "Electronic devices and components",
    "orgId": 1,
    "isActive": true,
    "createdAt": 1700000000000,
    "updatedAt": 1700000000000
  },
  "message": "Category retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 5. Update Category

```bash
curl -X PUT http://localhost:8080/api/categories/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Electronics & Gadgets",
    "description": "Electronic devices, components and gadgets"
  }'
```

**Request:**
```json
{
  "name": "Electronics & Gadgets",
  "description": "Electronic devices, components and gadgets"
}
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "Electronics & Gadgets",
    "description": "Electronic devices, components and gadgets",
    "orgId": 1,
    "isActive": true,
    "createdAt": 1700000000000,
    "updatedAt": 1700000100000
  },
  "message": "Category updated successfully",
  "timestamp": 1700000100000
}
```

---

## 6. Delete Category (Soft Delete)

```bash
curl -X DELETE http://localhost:8080/api/categories/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": "Category deleted successfully",
  "message": "Category deleted",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Category not found or access denied",
  "message": "Failed to delete category",
  "timestamp": 1700000000000
}
```

