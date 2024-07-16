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


INSERT INTO  hm_purchased_products_details (id, history_id,product_name, color, material, style, season) VALUES (1,1,"tsort","BLACK","linen", "sport","winter");
INSERT INTO  hm_purchased_products_details (id,history_id, product_name, color, material, style, season) VALUES (2,1,"tsort","Blue","linen", "sport","winter");

INSERT INTO  hm_purchased_products_details (id, history_id,product_name, color, material, style, season) VALUES (3,1,"tsort","Red","linen", "sport","spring");
INSERT INTO  hm_purchased_products_details (id,history_id, product_name, color, material, style, season) VALUES (4,1,"tsort","BLACK","linen", "formal","summer");
INSERT INTO  hm_purchased_products_details (id, history_id,product_name, color, material, style, season) VALUES (5,1,"tsort","BLACK","linen", "sport","summer");
