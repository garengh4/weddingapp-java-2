
-- DROP DATABASE IF EXISTS backyardwedding_db;
-- CREATE DATABASE backyardwedding_db;
-- USE backyardwedding_db;

CREATE TABLE customer (
  customer_id INT AUTO_INCREMENT,
  first_name VARCHAR(25),
  last_name VARCHAR(25),
  CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

CREATE TABLE partner (
  partner_id INT AUTO_INCREMENT,
  first_name VARCHAR(25),
  last_name VARCHAR(25),
  partner_rating INT,
  CONSTRAINT pk_partner PRIMARY KEY (partner_id)
);

CREATE TABLE backyard (
  backyard_id INT AUTO_INCREMENT,
  backyard_description VARCHAR(150),
  backyard_rating INT,
  backyard_city VARCHAR(25),
  backyard_cost INT,
  partner_id INT,
  CONSTRAINT pk_backyard PRIMARY KEY (backyard_id),
  CONSTRAINT fk_backyard_partner FOREIGN KEY (partner_id) REFERENCES partner(partner_id)
);

CREATE TABLE event(
  event_id INT AUTO_INCREMENT,
  event_name VARCHAR(50),
  event_date DATE,
  customer_id INT,
  backyard_id INT,
  CONSTRAINT pk_event PRIMARY KEY (event_id),
  CONSTRAINT fk_event_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
  CONSTRAINT fk_event_backyard FOREIGN KEY (backyard_id) REFERENCES backyard(backyard_id)
);



INSERT INTO customer (first_name, last_name) VALUES 
    ("Kyla", "Friso"),
    ("Hadi", "Robertson");
    
INSERT INTO partner(first_name, last_name, partner_rating) VALUES 
    ("Julia", "Bubbles", 3.5),
    ("Ann", "Tseng", 4.5);

INSERT INTO backyard(backyard_description, backyard_rating, backyard_city, backyard_cost, partner_id) VALUES
    ("beautiful hillside", 4.0, "Toronto", "2000", 001),    -- Julia's backyard
    ("cabin next to the lake", 3.0, "Kelowna", 1500, 002),  -- Ann's backyard
    ("the cafe", 2.5, "Montreal", 1000, 001);               -- Julia's backyard

INSERT INTO event (event_name, event_date, customer_id, backyard_id) VALUES 
    ("getting married", "2022-08-23", 001, 001),            -- customer Kyla at Julia's backyard in Toronto
    ("wedding anniversary", "2023-08-23", 001, 001),        -- customer kyla at Julia's backyard in Toronto
    ("getting married", "2023-01-15", 002, 002),            -- customer Hadi at Ann's backyard in Kelowna
    ("gender reveal", "2025-03-15", 001, 002),              -- customer Kyla at Ann's backyard in Toronto
    ("gender reveal", "2024-04-23", 002, 003);              -- customer Hadi at Julia's backyard in Montreal


