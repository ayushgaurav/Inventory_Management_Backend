# Supplier API

Base URL: `http://localhost:8080/api/suppliers`

**Note:** All endpoints require JWT token in Authorization header

---

## 1. Create Supplier

```bash
curl -X POST http://localhost:8080/api/suppliers \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "companyName": "Tech Supplies Inc.",
    "contactPerson": "John Smith",
    "email": "contact@techsupplies.com",
    "phone": "+1 (555) 123-4567",
    "address": "123 Tech Street, Silicon Valley, CA 94000",
    "paymentTerms": "Net 30",
    "creditLimit": 50000.00,
    "notes": "Preferred supplier for electronics"
  }'
```

**Request:**
```json
{
  "companyName": "Tech Supplies Inc.",
  "contactPerson": "John Smith",
  "email": "contact@techsupplies.com",
  "phone": "+1 (555) 123-4567",
  "address": "123 Tech Street, Silicon Valley, CA 94000",
  "paymentTerms": "Net 30",
  "creditLimit": 50000.00,
  "notes": "Preferred supplier for electronics"
}
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "companyName": "Tech Supplies Inc.",
    "contactPerson": "John Smith",
    "email": "contact@techsupplies.com",
    "phone": "+1 (555) 123-4567",
    "address": "123 Tech Street, Silicon Valley, CA 94000",
    "paymentTerms": "Net 30",
    "creditLimit": 50000.00,
    "notes": "Preferred supplier for electronics",
    "totalPurchases": 0.00,
    "creditUsed": 0.00,
    "rating": 0.0,
    "orgId": 1,
    "isActive": true,
    "createdAt": 1700000000000,
    "updatedAt": 1700000000000
  },
  "message": "Supplier created successfully",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Supplier with this company name already exists in your organization",
  "message": "Failed to create supplier",
  "timestamp": 1700000000000
}
```

---

## 2. Get All Suppliers

```bash
curl -X GET http://localhost:8080/api/suppliers \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "companyName": "Tech Supplies Inc.",
      "contactPerson": "John Smith",
      "email": "contact@techsupplies.com",
      "phone": "+1 (555) 123-4567",
      "address": "123 Tech Street, Silicon Valley, CA 94000",
      "paymentTerms": "Net 30",
      "creditLimit": 50000.00,
      "totalPurchases": 145000.00,
      "creditUsed": 12500.00,
      "rating": 5.0,
      "notes": "Preferred supplier for electronics",
      "orgId": 1,
      "isActive": true,
      "createdAt": 1700000000000,
      "updatedAt": 1700000000000
    },
    {
      "id": 2,
      "companyName": "Cable World",
      "contactPerson": "Sarah Johnson",
      "email": "info@cableworld.com",
      "phone": "+1 (555) 234-5678",
      "address": "456 Cable Ave, New York, NY 10001",
      "paymentTerms": "Net 45",
      "creditLimit": 30000.00,
      "totalPurchases": 89000.00,
      "creditUsed": 5000.00,
      "rating": 4.0,
      "notes": "Fast shipping, reliable quality",
      "orgId": 1,
      "isActive": true,
      "createdAt": 1700000000000,
      "updatedAt": 1700000000000
    }
  ],
  "message": "Suppliers retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 3. Get Active Suppliers Only

```bash
curl -X GET http://localhost:8080/api/suppliers/active \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "companyName": "Tech Supplies Inc.",
      "isActive": true,
      ...
    }
  ],
  "message": "Active suppliers retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 4. Get Supplier by ID

```bash
curl -X GET http://localhost:8080/api/suppliers/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "companyName": "Tech Supplies Inc.",
    "contactPerson": "John Smith",
    "email": "contact@techsupplies.com",
    "phone": "+1 (555) 123-4567",
    "address": "123 Tech Street, Silicon Valley, CA 94000",
    "paymentTerms": "Net 30",
    "creditLimit": 50000.00,
    "totalPurchases": 145000.00,
    "creditUsed": 12500.00,
    "rating": 5.0,
    "notes": "Preferred supplier for electronics",
    "orgId": 1,
    "isActive": true,
    "createdAt": 1700000000000,
    "updatedAt": 1700000000000
  },
  "message": "Supplier retrieved successfully",
  "timestamp": 1700000000000
}
```

---

## 5. Update Supplier

```bash
curl -X PUT http://localhost:8080/api/suppliers/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "companyName": "Tech Supplies Inc. (Updated)",
    "contactPerson": "John Smith",
    "email": "contact@techsupplies.com",
    "phone": "+1 (555) 123-4567",
    "address": "123 Tech Street, Silicon Valley, CA 94000",
    "paymentTerms": "Net 30",
    "creditLimit": 60000.00,
    "notes": "Updated notes"
  }'
```

**Request:**
```json
{
  "companyName": "Tech Supplies Inc. (Updated)",
  "contactPerson": "John Smith",
  "email": "contact@techsupplies.com",
  "phone": "+1 (555) 123-4567",
  "address": "123 Tech Street, Silicon Valley, CA 94000",
  "paymentTerms": "Net 30",
  "creditLimit": 60000.00,
  "notes": "Updated notes"
}
```

**Response (200):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "companyName": "Tech Supplies Inc. (Updated)",
    "creditLimit": 60000.00,
    "notes": "Updated notes",
    "updatedAt": 1700000100000,
    ...
  },
  "message": "Supplier updated successfully",
  "timestamp": 1700000100000
}
```

---

## 6. Delete Supplier (Soft Delete)

```bash
curl -X DELETE http://localhost:8080/api/suppliers/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response (200):**
```json
{
  "success": true,
  "data": "Supplier deleted successfully",
  "message": "Supplier deleted",
  "timestamp": 1700000000000
}
```

**Error (400):**
```json
{
  "success": false,
  "error": "Supplier not found or access denied",
  "message": "Failed to delete supplier",
  "timestamp": 1700000000000
}
```

---

## Payment Terms Options

Common payment terms values:
- `Net 15` - Payment due in 15 days
- `Net 30` - Payment due in 30 days
- `Net 45` - Payment due in 45 days
- `Net 60` - Payment due in 60 days
- `Due on Receipt` - Payment due immediately
- `COD` - Cash on Delivery


