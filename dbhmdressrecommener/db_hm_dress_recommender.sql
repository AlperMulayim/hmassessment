CREATE DATABASE IF NOT EXISTS db_hm_dress_recommender;

USE db_hm_dress_recommender;


CREATE TABLE hm_purchase_history(
    id INTEGER NOT NULL,
    customer_id  INTEGER NOT NULL,  
    product_id INTEGER NOT NULL,
    CONSTRAINT pk_purchase_history PRIMARY KEY(id),
    CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES hm_products(product_id),
    CONSTRAINT fk_user FOREIGN KEY(customer_id) REFERENCES hm_customers(id)
);

INSERT INTO hm_purchase_history(id,customer_id,product_id) VALUES(1,1,2);
INSERT INTO hm_purchase_history(id,customer_id,product_id) VALUES(2,1,3);
INSERT INTO hm_purchase_history(id,customer_id,product_id) VALUES(3,1,10);
INSERT INTO hm_purchase_history(id,product_id,customer_id) VALUES(4,2,2);

