package com.hm.alpermulayim.dressrecommenderapi.products.entities;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HmShoes  extends Product {
    @Column(name = "size")
    private Integer size;
}
