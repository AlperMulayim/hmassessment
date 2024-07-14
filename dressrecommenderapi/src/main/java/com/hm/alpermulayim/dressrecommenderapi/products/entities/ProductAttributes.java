package com.hm.alpermulayim.dressrecommenderapi.products.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hm_product_attributes")
@AllArgsConstructor
@Data
@NoArgsConstructor
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
