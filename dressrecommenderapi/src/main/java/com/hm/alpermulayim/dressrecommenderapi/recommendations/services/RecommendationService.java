package com.hm.alpermulayim.dressrecommenderapi.recommendations.services;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.HmAccessory;
import com.hm.alpermulayim.dressrecommenderapi.products.entities.HmClothes;
import com.hm.alpermulayim.dressrecommenderapi.products.entities.HmShoes;
import com.hm.alpermulayim.dressrecommenderapi.products.entities.Product;
import com.hm.alpermulayim.dressrecommenderapi.products.enums.ClotheType;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmAccessoriesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmClothesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmShoesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.ProductAttributesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.services.HmProductService;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecipePricePreferences;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecipeRequest;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecommendedProduct;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecommendedRecipe;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.utilities.CustomerBudget;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.utilities.CustomerBudgetCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private HmProductService productService;
    private ProductAttributesRepository attributesRepository;
    private HmClothesRepository clothesRepository;
    private HmShoesRepository shoesRepository;

    private HmAccessoriesRepository accessoriesRepository;

    @Autowired
    public RecommendationService(HmProductService productService,
                                 ProductAttributesRepository attributesRepository,
                                 HmClothesRepository clothesRepository,
                                 HmShoesRepository shoesRepository,
                                 HmAccessoriesRepository accessoriesRepository) {
        this.productService = productService;
        this.attributesRepository = attributesRepository;
        this.clothesRepository = clothesRepository;
        this.shoesRepository = shoesRepository;
        this.accessoriesRepository = accessoriesRepository;
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


    public RecommendedRecipe getRecipesForPreferences(RecipeRequest recepieRequest){
        //TODO: Get products  < price
        //TODO: filter top clothes with user history preferences < % price clothes preference
        //TODO: filter bottom clothes with user history preferences < % price clothes preference
        //TODO: filter shoes with user history preferences < % price shoes preference
        //TODO: filter accessories with user history preferences < % price accessories preference

        CustomerBudget budget = getCustomerBudget(recepieRequest.getTotalBudget(),recepieRequest.getPricePreferences());

        List<HmClothes> topClothes = getUserBudgetClothesByPrice(budget.getTop(),ClotheType.top);
        List<HmClothes> bottomClothes = getUserBudgetClothesByPrice(budget.getBottom(),ClotheType.bottom);
        List<HmShoes>  shoes = getUserBudgetShoes(budget.getShoes());
        List<HmAccessory> accessories = getUserBudgetAccessories(budget.getAccessories());



        System.out.println(topClothes);
        //TODO: selection algorithm , Knapsack.

        //TODO: create recommended recipe

        //TODO: save recommended recepie

        //TODO: recepie will commented and scored create scoring table with userid and recepie id.


        List<RecommendedProduct> recipeProducts = new ArrayList<>();
        //SELECT top

        //SELECT bottom

        //SELECT shoe

        //SELECT accesory



        List<RecommendedProduct> recommendedProducts = accessories.stream()
                .map(product -> RecommendedProduct.builder()
                        .code(product.getCode())
                        .price(product.getPrice())
                        .name(product.getName())
                        .attribute(attributesRepository.findByProductId(product.getId()).get())
                        .build())
                .collect(Collectors.toList());

        //ONLY BASED ON BROWNS;



        //TODO: add recommended basket for this step.

        return RecommendedRecipe.builder()
                .products(recommendedProducts.stream().filter(pr->pr.getAttribute().getColor().equals("brown")).collect(Collectors.toList()))
                .build();
    }

    public List<HmClothes> getUserBudgetClothesByPrice(Double price,ClotheType type){
        return clothesRepository.findByPriceLessThanAndType(price,type.name());
    }

    public List<HmShoes> getUserBudgetShoes(Double price){
        return shoesRepository.findByPriceLessThan(price);
    }

    public List<HmAccessory> getUserBudgetAccessories(Double price){
        return accessoriesRepository.findByPriceLessThan(price);
    }
    public CustomerBudget getCustomerBudget(Double totalBudget, RecipePricePreferences preferences){
        return new CustomerBudgetCalculator().getPrices(totalBudget,preferences);
    }



}
