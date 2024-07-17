package com.hm.alpermulayim.dressrecommenderapi.recipes.services;

import com.hm.alpermulayim.dressrecommenderapi.recipes.Recipe;
import com.hm.alpermulayim.dressrecommenderapi.recipes.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {
    private RecipeRepository repository;

    @Autowired
    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public Optional<Recipe> getRecipe(String name){
        return repository.findByName(name);
    }
}
