-- Create Organizations Table
CREATE TABLE IF NOT EXISTS organizations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    organization_name VARCHAR(100) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL
);

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone_number VARCHAR(15) UNIQUE,
    org_id BIGINT NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    is_active BOOLEAN DEFAULT TRUE,
    verified BOOLEAN DEFAULT FALSE,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    FOREIGN KEY (org_id) REFERENCES organizations(id)
);

-- Create Stores Table
CREATE TABLE IF NOT EXISTS stores (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location_type VARCHAR(20) NOT NULL,
    manager_name VARCHAR(100),
    full_address VARCHAR(500),
    phone_number VARCHAR(15),
    email VARCHAR(100),
    notes TEXT,
    org_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    FOREIGN KEY (org_id) REFERENCES organizations(id)
);

-- Create Categories Table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    org_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    FOREIGN KEY (org_id) REFERENCES organizations(id)
);

-- Create Suppliers Table
CREATE TABLE IF NOT EXISTS suppliers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(150) NOT NULL,
    contact_person VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(500),
    payment_terms VARCHAR(50),
    credit_limit DECIMAL(15,2) DEFAULT 0,
    notes TEXT,
    total_purchases DECIMAL(15,2) DEFAULT 0,
    credit_used DECIMAL(15,2) DEFAULT 0,
    rating DECIMAL(3,1) DEFAULT 0,
    org_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    FOREIGN KEY (org_id) REFERENCES organizations(id)
);

-- Create Indexes for better query performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_org_id ON users(org_id);
CREATE INDEX idx_organizations_name ON organizations(organization_name);
CREATE INDEX idx_stores_org_id ON stores(org_id);
CREATE INDEX idx_stores_name_org ON stores(name, org_id);
CREATE INDEX idx_categories_org_id ON categories(org_id);
CREATE INDEX idx_categories_name_org ON categories(name, org_id);
CREATE INDEX idx_suppliers_org_id ON suppliers(org_id);
CREATE INDEX idx_suppliers_company_org ON suppliers(company_name, org_id);
CREATE INDEX idx_suppliers_email_org ON suppliers(email, org_id);

