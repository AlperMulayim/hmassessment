CREATE DATABASE IF NOT EXISTS db_hm_dress_recommender;

USE db_hm_dress_recommender;


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


SELECT *  FROM hm_customers
INNER JOIN hm_purchase_history ON hm_purchase_history.customer_id = hm_customers.id
INNER JOIN hm_purchased_products_details ON hm_purchased_products_details.history_id = hm_purchase_history.id;
