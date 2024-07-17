package com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRequest {
    private Integer userId;
    private RecipeType type;
    private Double totalBudget;
    private RecipePricePreferences pricePreferences;
}
