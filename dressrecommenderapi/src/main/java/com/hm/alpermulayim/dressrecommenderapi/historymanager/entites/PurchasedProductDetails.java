package com.hm.alpermulayim.dressrecommenderapi.historymanager.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hm_purchased_products_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchasedProductDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "color")
    private String color;

    @Column(name = "material")
    private String material;

    @Column(name = "style")
    private String style;

    @Column(name = "season")
    private String season;

}
