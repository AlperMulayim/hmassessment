create table hm_product_attributes (
	id INTEGER NOT NULL,
    product_id INTEGER,
    color VARCHAR(40),
    material VARCHAR(80),
    style VARCHAR(90),
    season VARCHAR(80),
    CONSTRAINT PRIMARY KEY(id)
);

insert into  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 1, 1, "red","jean","summer","casual");
insert into  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 2, 2, "brown","linen","summer","formal");



SELECT * FROM hm_clothes
INNER JOIN hm_product_attributes ON hm_clothes.product_id = hm_product_attributes.product_id;