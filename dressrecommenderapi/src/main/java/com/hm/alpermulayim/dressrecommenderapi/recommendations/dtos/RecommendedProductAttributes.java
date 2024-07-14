package com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendedProductAttributes {
    private Integer productId;
    private String  color;
    private String  material;
    private String  style;
    private String  season;
}
