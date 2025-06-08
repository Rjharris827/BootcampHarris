CREATE TABLE dealerships (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(50),
    phone VARCHAR(12)
);

INSERT INTO dealerships (name, address, phone) VALUES
('Sunrise Auto', '123 Main St', '555-123-4567'),
('Moonlight Motors', '456 Elm St', '555-987-6543');

CREATE TABLE vehicles (
    VIN VARCHAR(17) PRIMARY KEY,
    make VARCHAR(30),
    model VARCHAR(30),
    year INT,
    color VARCHAR(20),
    mileage INT,
    price DECIMAL(10,2),
    sold BOOLEAN DEFAULT FALSE
);

INSERT INTO vehicles (VIN, make, model, year, color, mileage, price, sold) VALUES
('1HGCM82633A004352', 'Honda', 'Accord', 2020, 'Red', 30000, 18000.00, FALSE),
('1FAFP404X1F145678', 'Ford', 'Mustang', 2018, 'Red', 45000, 22000.00, TRUE),
('2T1BURHE5JC073456', 'Toyota', 'Corolla', 2021, 'Blue', 10000, 19000.00, FALSE),
('SCA664S52EUX53000', 'Rolls-Royce', 'Ghost', 2022, 'Black', 5000, 315000.00, TRUE);

CREATE TABLE inventory (
    dealership_id INT,
    VIN VARCHAR(17),
    PRIMARY KEY (dealership_id, VIN),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id) ON DELETE CASCADE,
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN) ON DELETE CASCADE
);

INSERT INTO inventory (dealership_id, VIN) VALUES
(1, '1HGCM82633A004352'),
(1, '1FAFP404X1F145678'),
(2, '2T1BURHE5JC073456'),
(2, 'SCA664S52EUX53000');

CREATE TABLE sales_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    dealership_id INT,
    customer_name VARCHAR(50),
    date_of_sale DATE,
    sale_price DECIMAL(10,2),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id)
);

INSERT INTO sales_contracts (VIN, dealership_id, customer_name, date_of_sale, sale_price) VALUES
('1FAFP404X1F145678', 1, 'John Doe', '2023-06-15', 21000.00),
('SCA664S52EUX53000', 2, 'Elon Richman', '2024-12-25', 310000.00);

CREATE TABLE lease_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    dealership_id INT,
    customer_name VARCHAR(50),
    lease_start_date DATE,
    lease_end_date DATE,
    monthly_payment DECIMAL(10,2),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id)
);

INSERT INTO lease_contracts (VIN, dealership_id, customer_name, lease_start_date, lease_end_date, monthly_payment) VALUES
('2T1BURHE5JC073456', 2, 'Jane Smith', '2024-01-01', '2026-01-01', 350.00);

