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



USE  db_hm_dress_recommender;

SELECT COUNT(*) FROM hm_clothes 
INNER JOIN  hm_product_attributes  ON hm_product_attributes.product_id = hm_clothes.product_id
WHERE hm_clothes.clothe_type = "top" AND  hm_clothes.price <= 250;

SELECT * FROM hm_clothes 
INNER JOIN  hm_product_attributes  ON hm_product_attributes.product_id = hm_clothes.product_id
WHERE hm_clothes.clothe_type = "top" AND  hm_clothes.price <= 250;

SELECT COUNT(*) FROM hm_clothes WHERE hm_clothes.price < 250 AND hm_clothes.clothe_type ="top";

SELECT * FROM hm_clothes WHERE hm_clothes.price < 250 AND hm_clothes.clothe_type ="top" ORDER BY hm_clothes.price;


SELECT * FROM hm_clothes WHERE hm_clothes.price < 100 AND hm_clothes.clothe_type ="bottom" ORDER BY hm_clothes.price;

SELECT * FROM hm_accessories;
SELECT * FROM hm_accessories INNER JOIN hm_product_attributes ON hm_product_attributes.product_id = hm_accessories.product_id;

SELECT * FROM hm_shoes WHERE hm_shoes.price < 100 ORDER BY hm_shoes.price;