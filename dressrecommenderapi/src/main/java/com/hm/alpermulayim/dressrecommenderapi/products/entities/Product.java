package com.hm.alpermulayim.dressrecommenderapi.products.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductAttributes attributes;
}
