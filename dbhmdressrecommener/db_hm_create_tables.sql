CREATE DATABASE IF NOT EXISTS  db_hm_dress_recommender;

USE db_hm_dress_recommender;

CREATE TABLE IF NOT EXISTS hm_customers(
	id INT AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	code VARCHAR(50),
	CONSTRAINT PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS hm_accessories (
	product_id INT,
	name VARCHAR(7),
	stock INT,
	product_code VARCHAR(50),
	price DECIMAL(5,2),
    CONSTRAINT PRIMARY KEY(product_id)
);

CREATE TABLE IF NOT EXISTS hm_product_attributes (
	id INTEGER NOT NULL,
    product_id INTEGER,
    color VARCHAR(40),
    material VARCHAR(80),
    style VARCHAR(90),
    season VARCHAR(80),
    CONSTRAINT PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS hm_clothes (
	product_id INT,
	name VARCHAR(100),
	stock INT,
	size VARCHAR(3),
	price DECIMAL(5,2),
	product_code VARCHAR(50),
	clothe_type VARCHAR(10),
    CONSTRAINT PRIMARY KEY(product_id)
);

CREATE TABLE IF NOT EXISTS hm_shoes (
	product_id INT,
	stock INT,
	name VARCHAR(12),
	size INT,
	price DECIMAL(5,2),
	product_code VARCHAR(50),
    CONSTRAINT PRIMARY KEY(product_id)
);


CREATE TABLE IF NOT EXISTS hm_purchase_history(
    id INT NOT NULL,
    voucher_code VARCHAR(100),
    customer_id  INT NOT NULL,  
    price DECIMAL(6,2),
    CONSTRAINT pk_purchase_history PRIMARY KEY(id),
    CONSTRAINT fk_user FOREIGN KEY(customer_id) REFERENCES hm_customers(id)
);


CREATE TABLE  IF NOT EXISTS hm_purchased_products_details(
    id INT NOT NULL,
    product_name VARCHAR(100),
    history_id INT,
    color VARCHAR(100),
    material VARCHAR(200),
    style VARCHAR(200),
    season VARCHAR(200),
    CONSTRAINT fk_history FOREIGN KEY(history_id) REFERENCES hm_purchase_history(id),
    CONSTRAINT pk_purchased_product_details PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS product_seq (next_val bigint) engine=InnoDB;

CREATE TABLE IF NOT EXISTS hm_recipes(
    id INT NOT NULL,
    name VARCHAR(200),
    CONSTRAINT pk_hm_recipes PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS recipe_tags(
    id INT NOT NULL,
    name VARCHAR(200),
    CONSTRAINT pk_recipes PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS hm_recipe_tags(
    id INT NOT NULL,
    recipe_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT hm_recipe_tags PRIMARY KEY(id),
    CONSTRAINT fk_recipe FOREIGN KEY(recipe_id) REFERENCES hm_recipes(id),
    CONSTRAINT fk_tag FOREIGN KEY(tag_id) REFERENCES recipe_tags(id)
);