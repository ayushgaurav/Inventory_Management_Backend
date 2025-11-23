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

