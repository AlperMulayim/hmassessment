package com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.ProductAttributes;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendedProduct {
    private String code;
    private String name;
    private BigDecimal price;
    private ProductAttributes attribute;
}

