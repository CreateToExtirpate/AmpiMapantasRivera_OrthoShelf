-- =====================================================
-- ORTHOPEDIC INVENTORY SYSTEM - DATABASE SCHEMA
-- =====================================================

CREATE orthoshelf_db;
USE orthoshelf_db;

-- Drop tables in reverse order of dependencies (if needed for recreation)
DROP TABLE IF EXISTS consumption_transactions;
DROP TABLE IF EXISTS borrowing_transactions;
DROP TABLE IF EXISTS gauze_pads;
DROP TABLE IF EXISTS wire_instruments;
DROP TABLE IF EXISTS impacting_instruments;
DROP TABLE IF EXISTS retractor_instruments;
DROP TABLE IF EXISTS grasping_instruments;
DROP TABLE IF EXISTS cutting_instruments;
DROP TABLE IF EXISTS consumable_instruments;
DROP TABLE IF EXISTS borrowable_instruments;
DROP TABLE IF EXISTS instruments;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS instrument_sizes;
DROP TABLE IF EXISTS instrument_types;
DROP VIEW IF EXISTS all_instruments_view;

-- =====================================================
-- LOOKUP TABLES (Enumerations)
-- =====================================================

-- Typ
CREATE TABLE instrument_types (
    type_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    type_name VARCHAR(30) UNIQUE NOT NULL
);

-- Insert type values 
INSERT INTO instrument_types (type_name)
VALUES ('NARROW'), ('BROAD'), ('SKIN'), ('DEEP_TISSUE'),
('TISSUE'), ('FABRIC'), ('FIX_ANGLE'), ('WITH-ARM'), ('N/A');

-- Size
CREATE TABLE instrument_sizes (
    size_id INT PRIMARY KEY AUTO_INCREMENT,
    size_name VARCHAR(30) UNIQUE NOT NULL
);

-- Insert size values
INSERT INTO instrument_sizes (size_name)
VALUES ('SMALL'), ('MEDIUM'), ('LARGE'), ('SHORT'), ('LONG'), ('N/A');

-- =====================================================
-- USER AND ORDER TABLES
-- =====================================================

CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(20) NOT NULL
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    order_date DATE DEFAULT (CURRENT_DATE()),
    order_patient VARCHAR(50),
	order_doctor VARCHAR(50),
    order_procedure VARCHAR(100),
    order_status ENUM('ON-GOING','COMPLETED') DEFAULT 'ON-GOING',
	order_expected_return DATE,
	order_actual_return DATE,
	instr_price DECIMAL(10, 2) DEFAULT 0.00
);

-- =====================================================
-- CORE INSTRUMENT TABLES
-- =====================================================

-- Main instruments table (base for all instruments)
CREATE TABLE instruments (
    instrument_id INT PRIMARY KEY AUTO_INCREMENT,
    instr_name VARCHAR(100) NOT NULL,
    instr_price DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    instr_stock_count INT NOT NULL DEFAULT 0,
    instr_description TEXT,
    instr_category ENUM('BORROWABLE', 'CONSUMABLE') NOT NULL,
    INDEX idx_instr_category (instr_category),
    INDEX idx_instr_name (instr_name)
);

-- Borrowable instruments
CREATE TABLE borrowable_instruments (
    instrument_id INT PRIMARY KEY,
    borrow_count INT NOT NULL DEFAULT 0,
    borrow_type ENUM('CUTTING', 'GRASPING', 'RETRACTOR', 'IMPACTING', 'WIRE') NOT NULL,
    FOREIGN KEY (instrument_id) REFERENCES instruments(instrument_id) ON DELETE CASCADE,
    INDEX idx_subtype (borrow_type),
    CHECK (borrow_count >= 0)
);

-- Consumable instruments
CREATE TABLE consumable_instruments (
    instrument_id INT PRIMARY KEY,
    consumable_type ENUM('ECELECTRODE', 'SUCTIONTUBING', 'GAUZEPAD') NOT NULL,
    FOREIGN KEY (instrument_id) REFERENCES instruments(instrument_id) ON DELETE CASCADE,
    INDEX idx_consumable_type (consumable_type)
);

-- =====================================================
-- BORROWABLE INSTRUMENT SUBTYPES
-- =====================================================

-- Cutting instruments (implements Typeable)
CREATE TABLE cutting_instruments (
    instrument_id INT PRIMARY KEY,
    type_id INT,
    size_id INT,
    cutting_type ENUM('BLADEHOLDER', 'SCISSORS', 'BONECHISEL', 'BONEFILE', 'CURETTE') NOT NULL,
    FOREIGN KEY (instrument_id) REFERENCES borrowable_instruments(instrument_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES instrument_types(type_id) ON DELETE SET NULL,
    FOREIGN KEY (size_id) REFERENCES instrument_sizes(size_id) ON DELETE SET NULL,
    INDEX idx_cutting_type (cutting_type)
);

-- Grasping instruments (implements Sizable)
CREATE TABLE grasping_instruments (
    instrument_id INT PRIMARY KEY,
    size_id INT,
    grasping_type ENUM('CURVECLAMP', 'BONEHOLDER', 'TISSUEELEVATOR') NOT NULL,
	FOREIGN KEY (instrument_id) REFERENCES borrowable_instruments(instrument_id) ON DELETE CASCADE,
    FOREIGN KEY (size_id) REFERENCES instrument_sizes(size_id) ON DELETE SET NULL,
    INDEX idx_grasping_type (grasping_type)
);

-- Retractor instruments (implements Typeable and Sizable)
CREATE TABLE retractor_instruments (
    instrument_id INT PRIMARY KEY,
    size_id INT,
    type_id INT,
    retractor_type ENUM('ANGLE', 'SELFRETAINING') NOT NULL,
	FOREIGN KEY (instrument_id) REFERENCES borrowable_instruments(instrument_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES instrument_types(type_id) ON DELETE SET NULL,
    FOREIGN KEY (size_id) REFERENCES instrument_sizes(size_id) ON DELETE SET NULL,
    INDEX idx_retractor_type (retractor_type)
);

-- Impacting instruments
CREATE TABLE impacting_instruments (
    instrument_id INT PRIMARY KEY,
    impacting_type ENUM('MALLET') NOT NULL,
    FOREIGN KEY (instrument_id) REFERENCES borrowable_instruments(instrument_id) ON DELETE CASCADE,
    INDEX idx_impacting_type (impacting_type)
);

-- Wire instruments
CREATE TABLE wire_instruments (
    instrument_id INT PRIMARY KEY,
    wire_type ENUM('CUTTER', 'PASSER', 'TWISTER') NOT NULL,
    FOREIGN KEY (instrument_id) REFERENCES borrowable_instruments(instrument_id) ON DELETE CASCADE,
    INDEX idx_wire_type (wire_type)
);

-- =====================================================
-- CONSUMABLE INSTRUMENT SUBTYPES
-- =====================================================

-- Gauze pads (implements Sizable)
CREATE TABLE gauze_pads (
    instrument_id INT PRIMARY KEY,
    size_id INT,
    FOREIGN KEY (size_id) REFERENCES instrument_sizes(size_id) ON DELETE SET NULL,
	FOREIGN KEY (instrument_id) REFERENCES consumable_instruments(instrument_id) ON DELETE CASCADE,
    INDEX idx_size (size_id)
);

-- =====================================================
-- TRANSACTION TABLES
-- =====================================================

-- Borrowing transactions (for borrowable instruments)
CREATE TABLE borrowing_transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    instrument_id INT NOT NULL,
    order_id INT,
    bor_trans_quantity INT NOT NULL,
    bor_trans_status ENUM('BORROWED', 'RETURNED', 'OVERDUE', 'LOST') DEFAULT 'BORROWED',
    bor_trans_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (instrument_id) REFERENCES borrowable_instruments(instrument_id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    INDEX idx_status (bor_trans_status),
    CHECK (bor_trans_quantity > 0)
);

-- Consumption transactions (for consumable instruments)
CREATE TABLE consumption_transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    instrument_id INT NOT NULL,
    order_id INT NOT NULL,
    cons_trans_quantity INT NOT NULL,
    cons_trans_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (instrument_id) REFERENCES consumable_instruments(instrument_id) ON DELETE RESTRICT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    CHECK (cons_trans_quantity > 0)
);

-- =====================================================
-- VIEWS FOR DISPLAY
-- =====================================================

CREATE VIEW all_instruments_view AS
	SELECT
		i.instrument_id,
		i.instr_name,
		i.instr_price,
		i.instr_stock_count,
		CAST(i.instr_category AS CHAR) AS instr_category,
		COALESCE(b.borrow_type, c.consumable_type) AS sub_type,
		COALESCE(b.borrow_count, 'N/A') AS borrow_count
	FROM instruments i
		LEFT JOIN borrowable_instruments b USING (instrument_id)
		LEFT JOIN consumable_instruments c USING (instrument_id);

-- =====================================================
-- SAMPLE DATA FOR ORTHOSHELF INVENTORY SYSTEM
-- =====================================================

-- Insert user
INSERT INTO users (id, username, password) VALUES (1, 'ortho_tech', 'admin123');

-- Insert sample orders
INSERT INTO orders (order_patient, order_doctor, order_procedure, order_status, order_expected_return, order_actual_return, instr_price) VALUES
('John Santos', 'Dr. Maria Cruz', 'Total Knee Replacement', 'ON-GOING', '2025-10-30', NULL, 15000.00),
('Ana Reyes', 'Dr. Ramon Garcia', 'Hip Fracture Fixation', 'ON-GOING', '2025-10-28', NULL, 12500.00),
('Pedro Lim', 'Dr. Sofia Fernandez', 'Spinal Fusion', 'COMPLETED', '2025-10-20', '2025-10-20', 20000.00),
('Carmen Tan', 'Dr. Jose Mendoza', 'Shoulder Arthroscopy', 'ON-GOING', '2025-11-02', NULL, 8000.00),
('Miguel Diaz', 'Dr. Isabel Torres', 'Ankle Reconstruction', 'COMPLETED', '2025-10-15', '2025-10-15', 10000.00);

-- =====================================================
-- BORROWABLE INSTRUMENTS (CUTTING)
-- =====================================================

-- Bone Chisel (NARROW type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Straight Bone Chisel 12mm', 1500.00, 5, 'Straight chisel for precise bone cutting', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (1, 2, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (1, 1, 6, 'BONECHISEL'); -- NARROW, N/A

-- Bone Chisel (BROAD type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Wide Bone Chisel 20mm', 1800.00, 4, 'Wide chisel for broader bone surfaces', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (2, 1, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (2, 2, 6, 'BONECHISEL'); -- BROAD, N/A

-- Mayo Scissors (TISSUE type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Mayo Scissors Curved 6.5"', 800.00, 8, 'Heavy-duty curved scissors for tissue dissection', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (3, 1, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (3, 5, 6, 'SCISSORS'); -- TISSUE, N/A

-- Metzenbaum Scissors (FABRIC type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Metzenbaum Scissors 7"', 750.00, 6, 'Delicate scissors for fine tissue dissection', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (4, 0, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (4, 6, 6, 'SCISSORS'); -- FABRIC, N/A

-- Scalpel Blade Holder (N/A type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Scalpel Handle #4', 350.00, 10, 'Standard blade holder for surgical incisions', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (5, 0, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (5, 9, 6, 'BLADEHOLDER'); -- N/A, N/A

-- Bone File (N/A type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Rasp Bone File Double-Ended', 1200.00, 4, 'For smoothing and shaping bone surfaces', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (6, 1, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (6, 9, 6, 'BONEFILE'); -- N/A, N/A

-- Curette (N/A type, SMALL size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Bone Curette Small', 900.00, 7, 'Small curette for bone cavity cleaning', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (7, 0, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (7, 9, 1, 'CURETTE'); -- N/A, SMALL

-- Curette (N/A type, MEDIUM size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Bone Curette Medium', 950.00, 6, 'Medium curette for general bone work', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (8, 1, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (8, 9, 2, 'CURETTE'); -- N/A, MEDIUM

-- Curette (N/A type, LARGE size - note: BIG maps to LARGE)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Bone Curette Large', 1000.00, 5, 'Large curette for extensive bone debridement', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (9, 0, 'CUTTING');
INSERT INTO cutting_instruments (instrument_id, type_id, size_id, cutting_type) VALUES (9, 9, 3, 'CURETTE'); -- N/A, LARGE

-- =====================================================
-- BORROWABLE INSTRUMENTS (GRASPING)
-- =====================================================

-- Curved Clamp (SMALL size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Kelly Curved Clamp Small 5"', 550.00, 8, 'Small curved hemostatic clamp', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (10, 1, 'GRASPING');
INSERT INTO grasping_instruments (instrument_id, size_id, grasping_type) VALUES (10, 1, 'CURVECLAMP'); -- SMALL

-- Curved Clamp (MEDIUM size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Kelly Curved Clamp Medium 6.25"', 600.00, 10, 'Medium curved hemostatic clamp for vessel control', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (11, 2, 'GRASPING');
INSERT INTO grasping_instruments (instrument_id, size_id, grasping_type) VALUES (11, 2, 'CURVECLAMP'); -- MEDIUM

-- Curved Clamp (LARGE size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Kelly Curved Clamp Large 8"', 700.00, 7, 'Large curved clamp for deep tissue', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (12, 1, 'GRASPING');
INSERT INTO grasping_instruments (instrument_id, size_id, grasping_type) VALUES (12, 3, 'CURVECLAMP'); -- LARGE

-- Tissue Elevator (SMALL size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Freer Periosteal Elevator Small', 450.00, 8, 'Small elevator for lifting soft tissue from bone', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (13, 0, 'GRASPING');
INSERT INTO grasping_instruments (instrument_id, size_id, grasping_type) VALUES (13, 1, 'TISSUEELEVATOR'); -- SMALL

-- Tissue Elevator (LARGE size - note: BIG maps to LARGE)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Langenbeck Periosteal Elevator Large', 550.00, 6, 'Large elevator for extensive tissue dissection', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (14, 1, 'GRASPING');
INSERT INTO grasping_instruments (instrument_id, size_id, grasping_type) VALUES (14, 3, 'TISSUEELEVATOR'); -- LARGE

-- Bone Holder (N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Bone Holding Forceps 9"', 2000.00, 5, 'Heavy-duty forceps for holding bone fragments', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (15, 1, 'GRASPING');
INSERT INTO grasping_instruments (instrument_id, size_id, grasping_type) VALUES (15, 6, 'BONEHOLDER'); -- N/A

-- =====================================================
-- BORROWABLE INSTRUMENTS (RETRACTOR)
-- =====================================================

-- Angle Retractor (NARROW type, SMALL size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Narrow Angle Retractor Small', 750.00, 8, 'Small narrow angle retractor', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (16, 0, 'RETRACTOR');
INSERT INTO retractor_instruments (instrument_id, size_id, type_id, retractor_type) VALUES (16, 1, 1, 'ANGLE'); -- SMALL, NARROW

-- Angle Retractor (BROAD type, MEDIUM size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Hohmann Bone Lever Retractor Medium', 900.00, 6, 'Medium broad angle retractor for bone exposure', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (17, 1, 'RETRACTOR');
INSERT INTO retractor_instruments (instrument_id, size_id, type_id, retractor_type) VALUES (17, 2, 2, 'ANGLE'); -- MEDIUM, BROAD

-- Angle Retractor (SKIN type, LARGE size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Beckman-Adson Retractor Large', 850.00, 7, 'Large retractor for joint exposure', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (18, 2, 'RETRACTOR');
INSERT INTO retractor_instruments (instrument_id, size_id, type_id, retractor_type) VALUES (18, 3, 3, 'ANGLE'); -- LARGE, SKIN

-- Angle Retractor (DEEP TISSUE type, MEDIUM size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Deep Tissue Retractor Medium', 950.00, 5, 'Medium retractor for deep tissue work', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (19, 1, 'RETRACTOR');
INSERT INTO retractor_instruments (instrument_id, size_id, type_id, retractor_type) VALUES (19, 2, 4, 'ANGLE'); -- MEDIUM, DEEP TISSUE

-- Self-Retaining (FIX ANGLE type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Gelpi Self-Retaining Retractor', 1100.00, 5, 'Self-retaining retractor with fixed angle', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (20, 0, 'RETRACTOR');
INSERT INTO retractor_instruments (instrument_id, size_id, type_id, retractor_type) VALUES (20, 6, 7, 'SELFRETAINING'); -- N/A, FIX ANGLE

-- Self-Retaining (FIX ANGLE type, N/A size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Weitlaner Self-Retaining 5.5"', 1250.00, 4, 'Self-retaining with sharp prongs', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (21, 1, 'RETRACTOR');
INSERT INTO retractor_instruments (instrument_id, size_id, type_id, retractor_type) VALUES (21, 6, 7, 'SELFRETAINING'); -- N/A, FIX ANGLE

-- =====================================================
-- BORROWABLE INSTRUMENTS (IMPACTING)
-- =====================================================

-- Surgical Mallet (no type, no size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Orthopedic Mallet 500g', 650.00, 6, 'Stainless steel mallet for impacting chisels', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (22, 1, 'IMPACTING');
INSERT INTO impacting_instruments (instrument_id, impacting_type) VALUES (22, 'MALLET');

-- =====================================================
-- BORROWABLE INSTRUMENTS (WIRE)
-- =====================================================

-- Wire Cutter (no type, no size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Wire Cutter Heavy Duty', 1400.00, 4, 'For cutting orthopedic wires', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (23, 1, 'WIRE');
INSERT INTO wire_instruments (instrument_id, wire_type) VALUES (23, 'CUTTER');

-- Wire Passer (no type, no size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Wire Passer Curved', 800.00, 5, 'For passing wires around bone', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (24, 0, 'WIRE');
INSERT INTO wire_instruments (instrument_id, wire_type) VALUES (24, 'PASSER');

-- Wire Twister (no type, no size)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Wire Twister Pliers', 700.00, 6, 'For twisting and securing cerclage wires', 'BORROWABLE');
INSERT INTO borrowable_instruments (instrument_id, borrow_count, borrow_type) VALUES (25, 1, 'WIRE');
INSERT INTO wire_instruments (instrument_id, wire_type) VALUES (25, 'TWISTER');

-- =====================================================
-- CONSUMABLE INSTRUMENTS
-- =====================================================

-- ECG Electrodes
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Disposable ECG Electrodes (Pack of 100)', 500.00, 50, 'Self-adhesive monitoring electrodes', 'CONSUMABLE');
INSERT INTO consumable_instruments (instrument_id, consumable_type) VALUES (26, 'ECELECTRODE');

-- Suction Tubing
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Sterile Suction Tubing 6ft', 150.00, 100, 'Single-use suction tubing', 'CONSUMABLE');
INSERT INTO consumable_instruments (instrument_id, consumable_type) VALUES (27, 'SUCTIONTUBING');

-- Gauze Pads - SMALL
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Sterile Gauze Pads 2x2" (Pack of 50)', 200.00, 200, 'Small sterile gauze for wound care', 'CONSUMABLE');
INSERT INTO consumable_instruments (instrument_id, consumable_type) VALUES (28, 'GAUZEPAD');
INSERT INTO gauze_pads (instrument_id, size_id) VALUES (28, 1); -- SMALL

-- Gauze Pads - LARGE (BIG)
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Sterile Gauze Pads 6x6" (Pack of 25)', 300.00, 150, 'Large gauze for extensive coverage', 'CONSUMABLE');
INSERT INTO consumable_instruments (instrument_id, consumable_type) VALUES (29, 'GAUZEPAD');
INSERT INTO gauze_pads (instrument_id, size_id) VALUES (29, 3); -- LARGE

-- Gauze Pads - SHORT
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Sterile Gauze Strips Short 3"', 180.00, 120, 'Short gauze strips for wound dressing', 'CONSUMABLE');
INSERT INTO consumable_instruments (instrument_id, consumable_type) VALUES (30, 'GAUZEPAD');
INSERT INTO gauze_pads (instrument_id, size_id) VALUES (30, 4); -- SHORT

-- Gauze Pads - LONG
INSERT INTO instruments (instr_name, instr_price, instr_stock_count, instr_description, instr_category) VALUES
('Sterile Gauze Strips Long 6"', 220.00, 100, 'Long gauze strips for extensive wrapping', 'CONSUMABLE');
INSERT INTO consumable_instruments (instrument_id, consumable_type) VALUES (31, 'GAUZEPAD');
INSERT INTO gauze_pads (instrument_id, size_id) VALUES (31, 5); -- LONG

-- =====================================================
-- SAMPLE TRANSACTIONS
-- =====================================================

-- Borrowing transactions
INSERT INTO borrowing_transactions (instrument_id, order_id, bor_trans_quantity, bor_trans_status, bor_trans_price) VALUES
(1, 1, 2, 'BORROWED', 3000.00),
(15, 1, 1, 'BORROWED', 2000.00),
(11, 2, 2, 'BORROWED', 1200.00),
(17, 2, 1, 'BORROWED', 900.00),
(18, 3, 2, 'RETURNED', 1700.00),
(22, 4, 1, 'BORROWED', 650.00),
(23, 5, 1, 'RETURNED', 1400.00);

-- Consumption transactions
INSERT INTO consumption_transactions (instrument_id, order_id, cons_trans_quantity, cons_trans_price) VALUES
(26, 1, 10, 50.00),
(27, 1, 5, 37.50),
(28, 2, 8, 100.00),
(29, 2, 12, 180.00),
(26, 3, 15, 75.00),
(30, 3, 3, 22.50),
(26, 4, 5, 25.00),
(31, 5, 6, 75.00);