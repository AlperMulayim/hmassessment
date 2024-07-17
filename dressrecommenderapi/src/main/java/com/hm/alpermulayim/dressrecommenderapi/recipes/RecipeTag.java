package com.hm.alpermulayim.dressrecommenderapi.recipes;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_tags")
public class RecipeTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
