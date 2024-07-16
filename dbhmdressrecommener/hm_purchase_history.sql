CREATE TABLE IF NOT EXISTS hm_purchase_history(
    id INT NOT NULL,
    voucher_code VARCHAR(100),
    customer_id  INT NOT NULL,  
    price DECIMAL(6,2),
    CONSTRAINT pk_purchase_history PRIMARY KEY(id),
    CONSTRAINT fk_user FOREIGN KEY(customer_id) REFERENCES hm_customers(id)
);

INSERT INTO  hm_purchase_history (id, voucher_code, customer_id,price ) VALUES (1, "PRCH-1234",1, 1200);