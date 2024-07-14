package com.hm.alpermulayim.dressrecommenderapi.products.clothes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HmClothes extends Product {
    @Column(name = "size")
    private String size;

    @Column(name = "clothe_type")
    private String type;
}
