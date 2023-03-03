-- Create database
CREATE DATABASE acme_bank;

-- Use database
USE acme_bank;

-- Create a table
CREATE TABLE accounts (
    account_id varchar(50) NOT NULL,
    name varchar(50) NOT NULL,
    balance decimal(10,2) NOT NULL,
	CONSTRAINT pkid PRIMARY KEY(account_id)
);

-- Insert data into table from data.csv
INSERT INTO accounts (account_id, name, balance)
VALUES
('V9L3Jd1BBI', 'fred', '100.00'),
('fhRq46Y6vB', 'barney', '300.00'),
('uFSFRqUpJy', 'wilma', '1000.00'),
('ckTV56axff', 'betty', '1000.00'),
('Qgcnwbshbh', 'pebbles', '50.00'),
('if9l185l18', 'bambam', '50.00');

-- Retrieve required data from table
SELECT * FROM accounts;
SELECT * FROM accounts WHERE account_id = 'V9L3Jd1BBI';

-- Transaction
START TRANSACTION;

SET @fromAcct = 'V9L3Jd1BBI',
    @toAcct = 'fhRq46Y6vB',
    @amount = 10;

UPDATE accounts
SET balance = balance - @amount
WHERE accountid = @fromAcct;

UPDATE accounts
SET balance = balance + @amount
WHERE account_id = @toAcct;

COMMIT;