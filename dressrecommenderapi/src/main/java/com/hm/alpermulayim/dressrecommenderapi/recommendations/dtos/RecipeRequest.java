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
    private String recipeName;
    private Double totalBudget;
    private Integer numOfRecipe;
    private RecipePricePreferences pricePreferences;
}
