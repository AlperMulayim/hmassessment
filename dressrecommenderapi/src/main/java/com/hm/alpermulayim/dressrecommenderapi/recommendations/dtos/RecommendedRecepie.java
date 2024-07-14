package com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendedRecepie {
    private String name;
    private BigDecimal price;
    private String code;
    private List<RecommendedProduct> products;
}
