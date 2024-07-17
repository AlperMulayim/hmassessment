package com.hm.alpermulayim.dressrecommenderapi.recipes.repositories;

import com.hm.alpermulayim.dressrecommenderapi.recipes.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
    Optional<Recipe> findByName(String name);
}
