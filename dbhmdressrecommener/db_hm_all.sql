
DROP DATABASE db_hm_dress_recommender;
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
	name VARCHAR(100),
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
	name VARCHAR(120),
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
INSERT INTO product_seq values ( 90 );

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




INSERT INTO hm_clothes (product_id, name, stock, size, price, product_code, clothe_type) VALUES
(1, 'Jeans', 50, 'M', 39.99, 'HM001', 'bottom'),
(2, 'Chinos', 30, 'L', 49.99, 'HM002', 'bottom'),
(3, 'Shorts', 40, 'S', 29.99, 'HM003', 'bottom'),
(4, 'Skirt', 35, 'M', 34.99, 'HM004', 'bottom'),
(5, 'Leggings', 60, 'S', 24.99, 'HM005', 'bottom'),
(6, 'Trousers', 45, 'L', 54.99, 'HM006', 'bottom'),
(7, 'Joggers', 55, 'M', 44.99, 'HM007', 'bottom'),
(8, 'Capri Pants', 25, 'S', 29.99, 'HM008', 'bottom'),
(9, 'Pants', 70, 'L', 59.99, 'HM009', 'bottom'),
(10, 'Denim Shorts', 40, 'M', 34.99, 'HM010', 'bottom'),
(11, 'T-Shirt', 80, 'M', 19.99, 'HM011', 'top'),
(12, 'Blouse', 50, 'S', 29.99, 'HM012', 'top'),
(13, 'Shirt', 60, 'L', 24.99, 'HM013', 'top'),
(14, 'Tank Top', 65, 'S', 14.99, 'HM014', 'top'),
(15, 'Sweater', 55, 'M', 39.99, 'HM015', 'top'),
(16, 'Hoodie', 45, 'L', 49.99, 'HM016', 'top'),
(17, 'Polo Shirt', 70, 'M', 29.99, 'HM017', 'top'),
(18, 'Crop Top', 30, 'S', 19.99, 'HM018', 'top'),
(19, 'Cardigan', 40, 'L', 34.99, 'HM019', 'top'),
(20, 'Vest', 50, 'M', 24.99, 'HM020', 'top'),
(21, 'Raincoat', 25, 'L', 59.99, 'HM021', 'top'),
(22, 'Puffer Jacket', 35, 'M', 54.99, 'HM022', 'top'),
(23, 'Trench Coat', 20, 'S', 74.99, 'HM023', 'top'),
(24, 'Blazer', 30, 'L', 69.99, 'HM024', 'top'),
(25, 'Dress Shirt', 40, 'M', 44.99, 'HM025', 'top'),
(26, 'Denim Jacket', 45, 'L', 64.99, 'HM026', 'top'),
(27, 'Bomber Jacket', 50, 'S', 49.99, 'HM027', 'top'),
(28, 'Parka', 55, 'M', 79.99, 'HM028', 'top'),
(29, 'Windbreaker', 60, 'L', 39.99, 'HM029', 'top'),
(30, 'Peacoat', 35, 'S', 89.99, 'HM030', 'top'),
(31, 'Cargo Pants', 40, 'M', 54.99, 'HM031', 'bottom'),
(32, 'Cargo Shorts', 45, 'L', 34.99, 'HM032', 'bottom'),
(33, 'Cargo Jacket', 25, 'S', 69.99, 'HM033', 'top'),
(34, 'Cargo Vest', 30, 'M', 49.99, 'HM034', 'top'),
(35, 'Cargo Skirt', 35, 'L', 29.99, 'HM035', 'bottom'),
(36, 'Chino Shorts', 40, 'S', 19.99, 'HM036', 'bottom'),
(37, 'Chino Pants', 45, 'M', 39.99, 'HM037', 'bottom'),
(38, 'Chino Jacket', 50, 'L', 59.99, 'HM038', 'top'),
(39, 'Chino Vest', 55, 'S', 49.99, 'HM039', 'top'),
(40, 'Chino Skirt', 60, 'M', 24.99, 'HM040', 'bottom'),
(41, 'Sweatpants', 65, 'L', 34.99, 'HM041', 'bottom'),
(42, 'Sweat Shorts', 70, 'S', 29.99, 'HM042', 'bottom'),
(43, 'Sweatshirt', 40, 'M', 44.99, 'HM043', 'top'),
(44, 'Sweat Hoodie', 45, 'L', 54.99, 'HM044', 'top'),
(45, 'Sweat Vest', 50, 'S', 24.99, 'HM045', 'top'),
(46, 'Sweat Jacket', 55, 'M', 39.99, 'HM046', 'top'),
(47, 'Sweat Cardigan', 60, 'L', 49.99, 'HM047', 'top'),
(48, 'Denim Skirt', 25, 'S', 19.99, 'HM048', 'bottom'),
(49, 'Denim Pants', 30, 'M', 29.99, 'HM049', 'bottom'),
(50, 'Denim Shorts', 35, 'L', 39.99, 'HM050', 'bottom');


INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (51, 89, 'Casual Shoes', 41, 72.93, '60505-3670');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (52, 26, 'Sandals', 36, 54.74, '0245-0057');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (53, 29, 'Heels', 36, 41.43, '49852-165');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (54, 68, 'Sneakers', 38, 70.3, '41520-416');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (55, 39, 'Flip Flops', 42, 13.41, '59538-001');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (56, 72, 'Loafers', 40, 76.46, '66116-527');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (57, 51, 'Heels', 36, 46.51, '0942-9202');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (58, 98, 'Flip Flops', 40, 58.43, '55154-5767');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (59, 29, 'Loafers', 37, 45.08, '52472-100');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (60, 1, 'Sandals', 38, 37.24, '24338-851');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (61, 67, 'Sneakers', 36, 43.93, '59572-102');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (62, 75, 'Sandals', 37, 88.56, '0268-1117');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (63, 64, 'Boots', 39, 57.31, '63148-240');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (64, 24, 'Flip Flops', 38, 34.74, '0641-0928');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (65, 88, 'Sneakers', 37, 97.61, '37205-377');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (66, 94, 'Boots', 43, 13.93, '54458-908');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (67, 46, 'Heels', 40, 67.76, '54416-002');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (68, 76, 'Sandals', 37, 78.9, '59564-251');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (69, 94, 'Loafers', 41, 67.35, '0268-0646');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (70, 30, 'Sneakers', 40, 56.11, '41520-630');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (71, 81, 'Casual Shoes', 36, 69.26, '51532-4616');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (72, 48, 'Flip Flops', 41, 64.82, '51079-524');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (73, 79, 'Casual Shoes', 43, 94.25, '61786-021');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (74, 85, 'Loafers', 39, 20.35, '0310-0281');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (75, 37, 'Sport Shoes', 43, 69.12, '49884-666');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (76, 78, 'Boots', 42, 97.84, '52533-002');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (77, 93, 'Sneakers', 37, 82.24, '55154-5837');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (78, 50, 'Sport Shoes', 41, 59.74, '76357-200');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (79, 57, 'Sandals', 37, 49.71, '49781-044');
INSERT INTO hm_shoes (product_id, stock, name, size, price, product_code) values (80, 80, 'Sandals', 40, 37.01, '62175-204');
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (81, 'Belts', 19, '48951-3158', 3.19);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (82, 'Scarves', 21, '0085-4331', 67.43);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (83, 'Tie', 3, '52125-610', 89.69);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (84, 'Belts', 91, '63304-790', 11.74);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (85, 'Gloves', 17, '36987-2629', 19.53);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (86, 'Wallets', 74, '0781-5816', 9.89);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (87, 'Wallets', 99, '64720-183', 22.8);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (88, 'Belts', 12, '0009-0395', 26.01);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (89, 'Scarves', 88, '25021-813', 20.8);
INSERT INTO hm_accessories (product_id, name, stock, product_code, price) values (90, 'Tie', 52, '36987-1429', 7.19);
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 1, 1, "red","jean","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 2, 2, "brown","linen","summer","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 3, 3, "brown","cotton","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 4, 4, "pink","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 5, 5, "gray","linen","spring","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 6, 6, "blue","denim","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 7, 7, "black","cotton","spring","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 8, 8, "white","polyester","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 9, 9, "blue","denim","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 10, 10, "black","denim","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 11, 11, "blue","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 12, 12, "brown","silk","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 13, 13, "white","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 14, 14, "black","polyester","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 15, 15, "black","wool","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 16, 16, "brown","polyester","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 17, 17, "brown","blue","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 18, 18, "black","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 19, 19, "pink","wool","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 20, 20, "black","silk","spring","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 21, 21, "brown","polyester","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 22, 22, "white","polyester","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 23, 23, "gray","cotton","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 24, 24, "blue","cotton","winter","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 25, 25, "white","cotton","summer","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 26, 26, "brown","denim","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 27, 27, "brown","polyester","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 28, 28, "black","cotton","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 29, 29, "gray","polyester","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 30, 30, "blue","wool","winter","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 31, 31, "black","cotton","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 32, 32, "brown","cotton","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 33, 33, "white","cotton","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 34, 34, "blue","cotton","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 35, 35, "yellow","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 36, 36, "brown","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 37, 37, "gray","cotton","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 38, 38, "black","cotton","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 39, 39, "brown","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 40, 40, "pink","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 41, 41, "gray","cotton","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 42, 42, "black","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 43, 43, "white","wool","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 44, 44, "pink","cotton","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 45, 45, "brown","cotton","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 46, 46, "black","cotton","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 47, 47, "pink","cotton","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 48, 48, "yellow","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 49, 49, "gray","cotton","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 50, 50, "blue","cotton","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 51, 51, "navy blue","canvas","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 52, 52, "gray","leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 53, 53, "red","Synthetic Leather","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 54, 54, "black","canvas","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 55, 55, "blue","Synthetic Leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 56, 56, "brown","suede","winter","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 57, 57, "black","leather","spring","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 58, 58, "yellow","Synthetic Leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 59, 59, "green","suede","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 60, 60, "brown","leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 61, 61, "black","canvas","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 62, 62, "gray","leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 63, 63, "blue","suede","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 64, 64, "green","Synthetic Leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 65, 65, "navy blue","canvas","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 66, 66, "black","suede","winter","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 67, 67, "yellow","Leather","spring","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 68, 68, "brown","Synthetic Leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 69, 69, "blue","suede","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 70, 70, "white","canvas","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 71, 71, "black","canvas","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 72, 72, "black","Synthetic Leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 73, 73, "yellow","canvas","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 74, 74, "navy blue","suede","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 75, 75, "black","canvas","spring","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 76, 76, "white","leather","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 77, 77, "green","canvas","spring","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 78, 78, "gray","mesh","spring","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 79, 79, "brown","leather","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 80, 80, "black","leather","summer","sport");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 81, 81, "brown","leather","winter","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 82, 82, "red","wool","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 83, 83, "black","fabric","winter","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 84, 84, "gray","leather","spring","formal");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 85, 85, "white","wool","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 86, 86, "brown","leather","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 87, 87, "black","Synthetic Leather","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 88, 88, "brown","leather","summer","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 89, 89, "white","wool","winter","casual");
INSERT INTO  hm_product_attributes (id, product_id, color, material, season, style) VALUES( 90, 90, "red","fabric","winter","formal");

INSERT INTO  hm_purchase_history (id, voucher_code, customer_id,price ) VALUES (1, "PRCH-1234",1, 1200);

INSERT INTO  hm_purchased_products_details (id, history_id,product_name, color, material, style, season) VALUES (1,1,"tsort","BLACK","linen", "sport","winter");
INSERT INTO  hm_purchased_products_details (id,history_id, product_name, color, material, style, season) VALUES (2,1,"tsort","Blue","linen", "sport","winter");

INSERT INTO  hm_purchased_products_details (id, history_id,product_name, color, material, style, season) VALUES (3,1,"tsort","Red","linen", "sport","spring");
INSERT INTO  hm_purchased_products_details (id,history_id, product_name, color, material, style, season) VALUES (4,1,"tsort","BLACK","linen", "formal","summer");
INSERT INTO  hm_purchased_products_details (id, history_id,product_name, color, material, style, season) VALUES (5,1,"tsort","BLACK","linen", "sport","summer");

INSERT INTO recipe_tags(id,name) VALUES(1,"casual");
INSERT INTO recipe_tags(id,name) VALUES(2,"formal");
INSERT INTO recipe_tags(id,name) VALUES(3,"sport");

INSERT INTO hm_recipes(id,name) VALUES(1,"wedding");
INSERT INTO hm_recipes(id,name) VALUES(2,"sport");
INSERT INTO hm_recipes(id,name) VALUES(3,"travel");
INSERT INTO hm_recipes(id,name) VALUES(4,"party");
INSERT INTO hm_recipes(id,name) VALUES(5,"work");

INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(1,1,1);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(2,1,2);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(3,2,3);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(4,3,3);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(5,3,1);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(6,4,1);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(7,4,2);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(8,5,1);
INSERT INTO hm_recipe_tags(id,recipe_id,tag_id) VALUES(9,5,2);
