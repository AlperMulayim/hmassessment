
CREATE DATABASE IF NOT EXISTS  db_hm_dress_recommender;

USE db_hm_dress_recommender;

CREATE TABLE IF NOT EXISTS hm_customers(
	id INT AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	code VARCHAR(50),
	CONSTRAINT PRIMARY KEY(id)
);
INSERT INTO hm_customers (id, first_name, last_name, code) values (1, 'Shamus', 'Batecok', '13733-030');
INSERT INTO hm_customers (id, first_name, last_name, code) values (2, 'Patricia', 'Thorington', '47202-1143');
INSERT INTO hm_customers (id, first_name, last_name, code) values (3, 'Shellysheldon', 'Tyrrell', '15127-900');
INSERT INTO hm_customers (id, first_name, last_name, code) values (4, 'Dickie', 'Tenny', '49371-019');
INSERT INTO hm_customers (id, first_name, last_name, code) values (5, 'Esma', 'Ayres', '0378-3000');
INSERT INTO hm_customers (id, first_name, last_name, code) values (6, 'Damiano', 'Stonnell', '36987-2792');
INSERT INTO hm_customers (id, first_name, last_name, code) values (7, 'Alvina', 'Worsfield', '42403-020');
INSERT INTO hm_customers (id, first_name, last_name, code) values (8, 'Peadar', 'Kenneford', '65174-270');
INSERT INTO hm_customers (id, first_name, last_name, code) values (9, 'Simonette', 'Donke', '50488-1001');
INSERT INTO hm_customers (id, first_name, last_name, code) values (10, 'Dreddy', 'Abramowitch', '66184-465');
INSERT INTO hm_customers (id, first_name, last_name, code) values (11, 'Doyle', 'Sweeten', '0093-8192');
INSERT INTO hm_customers (id, first_name, last_name, code) values (12, 'Evvie', 'Felton', '35356-333');
INSERT INTO hm_customers (id, first_name, last_name, code) values (13, 'Michale', 'Pardue', '0054-0196');
INSERT INTO hm_customers (id, first_name, last_name, code) values (14, 'Gnni', 'Pendlebery', '54569-3456');
INSERT INTO hm_customers (id, first_name, last_name, code) values (15, 'Orsa', 'Scragg', '54569-2784');

