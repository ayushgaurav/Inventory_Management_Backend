-- Sample Data for Testing

-- Insert sample organizations
INSERT INTO organizations (organization_name, is_active, created_at, updated_at) 
VALUES ('Acme Corp', TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO organizations (organization_name, is_active, created_at, updated_at) 
VALUES ('Tech Inc', TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- Insert sample users (password is "password123" encoded with BCrypt)
INSERT INTO users (email, password, first_name, last_name, phone_number, org_id, role, is_active, verified, created_at, updated_at)
VALUES ('admin@acme.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Admin', 'User', '1234567890', 1, 'ADMIN', TRUE, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO users (email, password, first_name, last_name, phone_number, org_id, role, is_active, verified, created_at, updated_at)
VALUES ('user@acme.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'John', 'Doe', '9876543210', 1, 'USER', TRUE, FALSE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- Insert sample stores
INSERT INTO stores (name, location_type, manager_name, full_address, phone_number, email, org_id, is_active, created_at, updated_at)
VALUES ('Main Warehouse', 'WAREHOUSE', 'Sarah Johnson', '123 Warehouse Blvd, City, State 12345', '1555-100-0001', 'warehouse@acme.com', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO stores (name, location_type, manager_name, full_address, phone_number, email, org_id, is_active, created_at, updated_at)
VALUES ('Downtown Store', 'RETAIL_STORE', 'Michael Chen', '456 Main Street, Downtown, State 12346', '1555-100-0002', 'downtown@acme.com', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO stores (name, location_type, manager_name, full_address, phone_number, email, org_id, is_active, created_at, updated_at)
VALUES ('Northside Branch', 'RETAIL_STORE', 'Emily Rodriguez', '789 North Ave, Northside, State 12347', '1555-100-0003', 'northside@acme.com', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- Insert sample categories
INSERT INTO categories (name, description, org_id, is_active, created_at, updated_at)
VALUES ('Electronics', 'Electronic devices and components', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO categories (name, description, org_id, is_active, created_at, updated_at)
VALUES ('Accessories', 'Various accessories and add-ons', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO categories (name, description, org_id, is_active, created_at, updated_at)
VALUES ('Furniture', 'Office and workspace furniture', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO categories (name, description, org_id, is_active, created_at, updated_at)
VALUES ('Lighting', 'Lighting solutions and fixtures', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- Insert sample suppliers
INSERT INTO suppliers (company_name, contact_person, email, phone, address, payment_terms, credit_limit, total_purchases, credit_used, rating, notes, org_id, is_active, created_at, updated_at)
VALUES ('Tech Supplies Inc.', 'John Smith', 'contact@techsupplies.com', '+1 (555) 123-4567', '123 Tech Street, Silicon Valley, CA 94000', 'Net 30', 50000.00, 145000.00, 12500.00, 5.0, 'Preferred supplier for electronics', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

INSERT INTO suppliers (company_name, contact_person, email, phone, address, payment_terms, credit_limit, total_purchases, credit_used, rating, notes, org_id, is_active, created_at, updated_at)
VALUES ('Cable World', 'Sarah Johnson', 'info@cableworld.com', '+1 (555) 234-5678', '456 Cable Ave, New York, NY 10001', 'Net 45', 30000.00, 89000.00, 5000.00, 4.0, 'Fast shipping, reliable quality', 1, TRUE, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

