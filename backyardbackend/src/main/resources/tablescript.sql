
-- DROP DATABASE IF EXISTS backyardwedding_db;
-- CREATE DATABASE backyardwedding_db;
-- USE backyardwedding_db;

CREATE TABLE customer (
  customer_id INT AUTO_INCREMENT,
  first_name VARCHAR(25),
  last_name VARCHAR(25),
  password VARCHAR(70) NOT NULL,
  email_id VARCHAR(50),
  CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

CREATE TABLE partner (
  partner_id INT AUTO_INCREMENT,
  first_name VARCHAR(25),
  last_name VARCHAR(25),
  password VARCHAR(70) NOT NULL,
  email_id VARCHAR(50),
  CONSTRAINT pk_partner PRIMARY KEY (partner_id)
);

CREATE TABLE backyard (
  backyard_id INT AUTO_INCREMENT,
  backyard_name VARCHAR(50),
  backyard_description VARCHAR(150),
  backyard_city VARCHAR(25),
  backyard_cost INT,
  partner_id INT,
  CONSTRAINT pk_backyard PRIMARY KEY (backyard_id),
  CONSTRAINT fk_backyard_partner FOREIGN KEY (partner_id) REFERENCES partner(partner_id)
);

CREATE TABLE event(
  event_id INT AUTO_INCREMENT,
  event_name VARCHAR(50),
  event_description VARCHAR(150),
  event_date DATE,
  customer_id INT,
  backyard_id INT,
  CONSTRAINT pk_event PRIMARY KEY (event_id),
  CONSTRAINT fk_event_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
  CONSTRAINT fk_event_backyard FOREIGN KEY (backyard_id) REFERENCES backyard(backyard_id)
);


INSERT INTO customer (first_name, last_name, password, email_id) VALUES 
    ("Kyla", "Friso", "KylaFriso@", "kylafriso@gmail.com"),
    ("Hadi", "Robertson","HadiR%", "hadirobertson@gmail.com");
    
INSERT INTO partner(first_name, last_name, password, email_id) VALUES 
    ("Julia", "Bubbles", "Juliabb@", "juliabubbles@gmail.com"),
    ("Ann", "Tseng", "annT$", "anntseng@gmail.com");

--@block
INSERT INTO backyard(backyard_name, backyard_description, backyard_city, backyard_cost, partner_id) VALUES
    ("Binxy J", "beautiful hillside", "Toronto", 2300, 001),        -- Julia's backyard
    ("Kitty C", "cat cafe", "Montreal", 3400, 001),                 -- Julia's backyard
    ("Ribbit A", "cabin next to the lake", "Kelowna", 1700, 002);   -- Ann's backyard

INSERT INTO event (event_name, event_description, event_date, customer_id, backyard_id) VALUES 
    ("K&A", "getting married","2022-08-23", 001, 001),                    -- customer Kyla at Julia's backyard in Toronto
    ("K&A2", "wedding anniversary", "2023-08-23", 001, 001),              -- customer kyla at Julia's backyard in Toronto
    ("Hadi loves Robbie", "getting married", "2022-01-14", 002, 003),     -- customer Hadi at Ann's backyard in Kelowna
    ("K junior", "gender reveal", "2025-03-15", 001, 003),                -- customer Kyla at Ann's backyard in Kelowna
    ("H&R junior", "gender reveal", "2024-04-23",002, 002);               -- customer Hadi at Julia's backyard in Montreal


