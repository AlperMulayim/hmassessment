package com.hm.alpermulayim.dressrecommenderapi.products.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HmClothes extends Product {
    @Column(name = "prod_size")
    private String size;

    @Column(name = "clothe_type")
    private String type;
}
