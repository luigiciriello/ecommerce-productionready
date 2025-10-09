INSERT INTO product (code, name, price)
VALUES ('P001', 'V - T shirt', 599.0)
ON CONFLICT (code) DO NOTHING;

INSERT INTO product (code, name, price)
VALUES ('P002', 'V - Bag', 2499.0)
ON CONFLICT (code) DO NOTHING;

INSERT INTO product (code, name, price)
VALUES ('P003', 'V - Belt', 699.0)
ON CONFLICT (code) DO NOTHING;

INSERT INTO product (code, name, price)
VALUES ('P004', 'V - Necklace', 989.90)
ON CONFLICT (code) DO NOTHING;
