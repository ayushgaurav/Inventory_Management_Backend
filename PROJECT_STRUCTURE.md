# Project Structure

## 📁 Directories

```
inventory_management/
├── api-docs/                      # API docs (curl + request/response)
│   ├── README.md
│   └── Authentication.md
│
├── src/main/resources/sql/        # SQL scripts
│   ├── create_tables.sql
│   ├── drop_tables.sql
│   └── sample_data.sql
│
└── src/main/java/com/inventory_management/
    ├── authentication/            # Auth module
    │   ├── controller/
    │   ├── dto/
    │   ├── entity/                # User, Company
    │   ├── repository/
    │   ├── security/              # JWT
    │   └── service/
    └── common/                    # Shared
        ├── config/
        └── response/
```

---

## 🚀 Quick Commands

### SQL
```bash
mysql -u root -p inventory_management_db < src/main/resources/sql/create_tables.sql
```

### API Docs
```bash
cat api-docs/Authentication.md
```

### Test
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"test@test.com","password":"test123","companyName":"Test"}'
```

---

## 📝 Add New API Docs

Create `api-docs/YourController.md`:

```markdown
# Your API

## 1. Endpoint Name
curl -X POST http://localhost:8080/api/endpoint

Request: {...}
Response: {...}
```

