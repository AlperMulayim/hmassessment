package com.hm.alpermulayim.dressrecommenderapi.recommendations.contollers;

import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecipeRequest;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecommendedRecipe;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recommendations/recipes")
public class RecommendationController {

    private RecommendationService service;

    @Autowired
    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping
    public RecommendedRecipe getAllRecepies(){
        return service.getRecipes();
    }

    @PostMapping
    public List<RecommendedRecipe> createRecepieWithMyPreferences(@RequestBody RecipeRequest recipeRequest){
        System.out.println(recipeRequest);
        return service.getRecipesForPreferences(recipeRequest);
    }

}
