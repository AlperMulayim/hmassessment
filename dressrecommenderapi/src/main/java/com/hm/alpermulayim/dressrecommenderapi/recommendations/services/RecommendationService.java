package com.hm.alpermulayim.dressrecommenderapi.recommendations.services;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.Product;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.ProductAttributesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.services.HmProductService;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecepieRequest;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecommendedProduct;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecommendedRecipe;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.entities.ClothingRecepie;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private HmProductService productService;
    private ProductAttributesRepository attributesRepository;

    @Autowired
    public RecommendationService(HmProductService productService,ProductAttributesRepository attributesRepository) {
        this.productService = productService;
        this.attributesRepository = attributesRepository;
    }

    public RecommendedRecipe getRecipes(){

        List<Product> products = productService.getProducts().subList(37,44);

        //create recepies algorithm.
        //TODO: update here for recepies algorithm
        List<RecommendedProduct> recommendedProducts = products.stream()
                .map(product -> RecommendedProduct.builder()
                        .code(product.getCode())
                        .price(product.getPrice())
                        .name(product.getName())
                        .attribute(attributesRepository.findByProductId(product.getId()).get())
                        .build())
                .collect(Collectors.toList());

        BigDecimal totalPrice = BigDecimal.valueOf(products.stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum());

        return RecommendedRecipe.builder()
                .price(totalPrice)
                .code("RCP-123")
                .name("wedding")
                .products(recommendedProducts)
                .build();
    }


    public RecommendedRecipe getRecipesForPreferences(RecepieRequest recepieRequest){
        //TODO: Get products  < price
        //TODO: filter top clothes with user history preferences < % price clothes preference
        //TODO: filter bottom clothes with user history preferences < % price clothes preference
        //TODO: filter shoes with user history preferences < % price shoes preference
        //TODO: filter accessories with user history preferences < % price accessories preference

        //TODO: selection algorithm , Knapsack.

        //TODO: create recommended recipe

        //TODO: save recommended recepie

        //TODO: recepie will commented and scored create scoring table with userid and recepie id.




        return RecommendedRecipe.builder()
                .build();
    }

}
