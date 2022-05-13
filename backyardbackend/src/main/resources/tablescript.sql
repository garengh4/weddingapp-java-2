
INSERT INTO customer (first_name, last_name, password, customer_email_id) VALUES 
    ("Kyla", "Friso", "KylaF@", "kylaf@gmail.com"),
    ("Hadi", "Robertson","HadiR@", "hadir@gmail.com");
    
INSERT INTO partner(first_name, last_name, password, partner_email_id) VALUES 
    ("Julia", "Bubbles", "JuliaB@", "juliab@gmail.com"),
    ("Ann", "Tseng", "AnnT@", "annt@gmail.com");

--@block
INSERT INTO backyard(backyard_name, backyard_description, backyard_city, backyard_cost, partner_email_id) VALUES
    ("Binxy J", "beautiful hillside", "Toronto", 2300, "juliab@gmail.com"),         -- Julia's backyard
    ("Kitty J", "cat cafe", "Montreal", 3400, "juliab@gmail.com"),                  -- Julia's backyard
    ("Ribbit A", "cabin next to the lake", "Kelowna", 1700, "annt@gmail.com");      -- Ann's backyard

--@block
INSERT INTO event (event_name, event_description, event_date, customer_email_id, backyard_id) VALUES 
    ("K&A", "getting married","2022-08-23", "kylaf@gmail.com", 001),                    -- customer Kyla at Julia's backyard in Toronto
    ("K&A2", "wedding anniversary", "2023-08-23", "kylaf@gmail.com", 001),              -- customer kyla at Julia's backyard in Toronto
    ("h&r", "getting married", "2022-01-14", "hadir@gmail.com", 003),                   -- customer Hadi at Ann's backyard in Kelowna
    ("K junior", "gender reveal", "2025-03-15", "kylaf@gmail.com", 003),                -- customer Kyla at Ann's backyard in Kelowna
    ("H&R junior", "gender reveal", "2024-04-23", "hadir@gmail.com", 002);              -- customer Hadi at Julia's backyard in Montreal


--@block -- Get all events in backyard 1
SELECT 
    e.event_name
FROM event e WHERE backyard_id = 1;

--@block -- Get all customers registered to partner_id = 1
SELECT 
    c.first_name,
    c.last_name
FROM customer c
INNER JOIN event e ON c.customer_email_id = e.customer_email_id
WHERE e.backyard_id = 1;