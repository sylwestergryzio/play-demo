# Accounts schema
 
# --- !Ups
INSERT INTO accounts values(nextval('accounts_id_seq'), 'John', 'Doe', 0);
INSERT INTO accounts values(nextval('accounts_id_seq'), 'James', 'Smith', 0);
INSERT INTO accounts values(nextval('accounts_id_seq'), 'Maya', 'May', 1);

INSERT INTO products values(nextval('products_id_seq'), 'car', 'Renault Megane', 60000);
INSERT INTO products values(nextval('products_id_seq'), 'car', 'Kia Sportage', 80000);
INSERT INTO products values(nextval('products_id_seq'), 'laptop', 'Asus', 3500);
INSERT INTO products values(nextval('products_id_seq'), 'mobile phone', 'HTC', 550.25);

INSERT INTO leases   values(nextval('leases_id_seq'), 1, 1, 36);
INSERT INTO leases   values(nextval('leases_id_seq'), 1, 3, 12);
INSERT INTO leases   values(nextval('leases_id_seq'), 2, 2, 36);
INSERT INTO leases   values(nextval('leases_id_seq'), 2, 4, 12);

# --- !Downs
DELETE FROM leases; 
DELETE FROM accounts; 
DELETE FROM products; 

