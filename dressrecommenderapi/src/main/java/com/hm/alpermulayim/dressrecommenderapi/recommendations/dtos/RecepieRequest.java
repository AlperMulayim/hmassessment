package com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecepieRequest {
    private Integer userId;
    private RecepieType type;
    private Double price;
    private RecipePricePreferences pricePreferences;
}
