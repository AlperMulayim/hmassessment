package com.hm.alpermulayim.dressrecommenderapi.products.shoes;

import com.hm.alpermulayim.dressrecommenderapi.products.clothes.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
