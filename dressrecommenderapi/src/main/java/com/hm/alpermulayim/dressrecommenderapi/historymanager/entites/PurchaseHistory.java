package com.hm.alpermulayim.dressrecommenderapi.historymanager.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "hm_purchase_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "voucher_code")
    private String code;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany
    @JoinColumn(name = "history_id")
    private List<PurchasedProductDetails> productDetails;
}

//    CREATE TABLE hm_purchase_history(
//        id INT NOT NULL,
//        voucher_code VARCHAR(100),
//    customer_id  INT NOT NULL,
//    purchased_product_detail_id INT NOT NULL,
//    price DECIMAL(6,2),
//    CONSTRAINT pk_purchase_history PRIMARY KEY(id),
//    CONSTRAINT hm_purchased_products_details FOREIGN KEY(purchased_product_detail_id) REFERENCES hm_purchased_products_details(id),
//        CONSTRAINT fk_user FOREIGN KEY(customer_id) REFERENCES hm_customers(id)
//        );