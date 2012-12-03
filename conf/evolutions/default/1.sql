# Accounts schema
 
# --- !Ups

CREATE SEQUENCE accounts_id_seq;
CREATE SEQUENCE products_id_seq;
CREATE SEQUENCE leases_id_seq;

CREATE TABLE accounts (
    id integer NOT NULL DEFAULT nextval('accounts_id_seq'),
    first_name varchar(255),
    last_name  varchar(255),
    status integer
);
 
CREATE TABLE products (
    id integer NOT NULL DEFAULT nextval('products_id_seq'),
    product_type varchar(255),
    name         varchar(255),
    price        double
);

CREATE TABLE leases (
    id integer NOT NULL DEFAULT nextval('leases_id_seq'),
    account integer NOT NULL references accounts(id),
    product integer NOT NULL UNIQUE references products(id),
    installments integer NOT NULL
);

# --- !Downs
 
DROP TABLE leases;
DROP SEQUENCE leases_id_seq;

DROP TABLE accounts;
DROP SEQUENCE accounts_id_seq;

DROP TABLE products;
DROP SEQUENCE products_id_seq;
