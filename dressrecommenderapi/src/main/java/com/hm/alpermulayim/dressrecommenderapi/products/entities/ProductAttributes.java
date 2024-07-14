package com.hm.alpermulayim.dressrecommenderapi.products.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "hm_product_attributes")
public class ProductAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;
    private String  color;
    private String  material;
    private String  style;
    private String  season;
}
