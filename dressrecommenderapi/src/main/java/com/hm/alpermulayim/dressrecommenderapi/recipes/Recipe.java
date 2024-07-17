package com.hm.alpermulayim.dressrecommenderapi.recipes;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hm_recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "hm_recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<RecipeTag> tags;
}
