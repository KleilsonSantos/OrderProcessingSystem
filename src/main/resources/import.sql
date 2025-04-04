CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    customer VARCHAR(255) NOT NULL,
    total DOUBLE PRECISION NOT NULL
);

TRUNCATE TABLE orders;

INSERT INTO orders (customer, total) VALUES ('Alice Martins', 120.75);
INSERT INTO orders (customer, total) VALUES ('Carlos Souza', 210.50);
INSERT INTO orders (customer, total) VALUES ('Bruna Silva', 85.40);
INSERT INTO orders (customer, total) VALUES ('Diego Fernandes', 150.00);
INSERT INTO orders (customer, total) VALUES ('Fabiana Lima', 99.99);
INSERT INTO orders (customer, total) VALUES ('Guilherme Santos', 180.49);
INSERT INTO orders (customer, total) VALUES ('Helena Costa', 55.99);
INSERT INTO orders (customer, total) VALUES ('Igor Almeida', 75.20);
INSERT INTO orders (customer, total) VALUES ('Juliana Pereira', 99.49);
INSERT INTO orders (customer, total) VALUES ('Lucas Oliveira', 120.00);
