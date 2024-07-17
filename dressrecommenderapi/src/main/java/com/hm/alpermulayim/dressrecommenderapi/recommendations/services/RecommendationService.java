package com.hm.alpermulayim.dressrecommenderapi.recommendations.services;

import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.CustomerHistoryAnalysis;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchaseHistory;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.services.CustomerPurchaseHistoryAnalyseManager;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.services.PurchaseHistoryService;
import com.hm.alpermulayim.dressrecommenderapi.products.entities.*;
import com.hm.alpermulayim.dressrecommenderapi.products.enums.ClotheType;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmAccessoriesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmClothesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmShoesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.ProductAttributesRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.services.HmProductService;
import com.hm.alpermulayim.dressrecommenderapi.recipes.Recipe;
import com.hm.alpermulayim.dressrecommenderapi.recipes.RecipeTag;
import com.hm.alpermulayim.dressrecommenderapi.recipes.services.RecipeService;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.*;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.utilities.CustomerBudget;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.utilities.CustomerBudgetCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private HmProductService productService;
    private ProductAttributesRepository attributesRepository;
    private HmClothesRepository clothesRepository;
    private HmShoesRepository shoesRepository;

    private HmAccessoriesRepository accessoriesRepository;

    private PurchaseHistoryService historyService;

    private CustomerPurchaseHistoryAnalyseManager historyAnalyseManager;

    private RecipeService recipeService;

    @Autowired
    public RecommendationService(HmProductService productService,
                                 ProductAttributesRepository attributesRepository,
                                 HmClothesRepository clothesRepository,
                                 HmShoesRepository shoesRepository,
                                 HmAccessoriesRepository accessoriesRepository,
                                 PurchaseHistoryService historyService,
                                 CustomerPurchaseHistoryAnalyseManager analyseManager,
                                 RecipeService recipeService) {
        this.productService = productService;
        this.attributesRepository = attributesRepository;
        this.clothesRepository = clothesRepository;
        this.shoesRepository = shoesRepository;
        this.accessoriesRepository = accessoriesRepository;
        this.historyService = historyService;
        this.historyAnalyseManager = analyseManager;
        this.recipeService = recipeService;
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

        Double totalPrice = products.stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum();

        return RecommendedRecipe.builder()
                .price(new BigDecimal(totalPrice).setScale(2,RoundingMode.DOWN))
                .code("RCP-123")
                .name("wedding")
                .products(recommendedProducts)
                .build();
    }


    public RecommendedRecipe getRecipesForPreferences(RecipeRequest recipeRequest){
        //TODO: Get products  < price
        //TODO: filter top clothes with user history preferences < % price clothes preference
        //TODO: filter bottom clothes with user history preferences < % price clothes preference
        //TODO: filter shoes with user history preferences < % price shoes preference
        //TODO: filter accessories with user history preferences < % price accessories preference
        List<Product> selectedProducts = new LinkedList<>();

        CustomerBudget budget = getCustomerBudget(recipeRequest.getTotalBudget(),recipeRequest.getPricePreferences());

        List<HmClothes> topClothes = getUserBudgetClothesByPrice(budget.getTop(),ClotheType.top);
        List<HmClothes> bottomClothes = getUserBudgetClothesByPrice(budget.getBottom(),ClotheType.bottom);
        List<HmShoes>  shoes = getUserBudgetShoes(budget.getShoes());
        List<HmAccessory> accessories = getUserBudgetAccessories(budget.getAccessories());



        System.out.println(topClothes);

        List<RecommendedProduct> recipeProducts = new ArrayList<>();
        //SELECT top

        //SELECT bottom

        //SELECT shoe
        List<PurchaseHistory> history = historyService.getPurchaseHistoryForCustomer(recipeRequest.getUserId());

        CustomerHistoryAnalysis customerAnalysis = historyAnalyseManager.analyze(history);

        //SELECT accesory


        System.out.println( attributesRepository.findByColorIn(Set.of("black","red")));
        System.out.println(customerAnalysis);

       topClothes = applyCustomerAnalysisFilterForClothes(topClothes,customerAnalysis);
       bottomClothes = applyCustomerAnalysisFilterForClothes(bottomClothes,customerAnalysis);
       shoes = applyCustomerAnalysisFilterForShoes(shoes,customerAnalysis);
       accessories = applyCustomerAnalysisFilterForAccessories(accessories,customerAnalysis);

       Optional<Recipe> recipe = recipeService.getRecipe(recipeRequest.getRecipeName());


       if(recipe.isPresent()){
          List<String> recipeTags = recipe.get().getTags().stream()
                  .map(RecipeTag::getName).toList();

          topClothes = topClothes.stream()
                  .filter(clothe-> recipeTags.contains(clothe.getAttributes().getStyle().toLowerCase()))
                  .sorted(Comparator.comparing(HmClothes::getPrice).reversed())
                  .collect(Collectors.toList());

           bottomClothes = bottomClothes.stream()
                   .filter(clothe-> recipeTags.contains(clothe.getAttributes().getStyle().toLowerCase()))
                   .sorted(Comparator.comparing(HmClothes::getPrice).reversed())
                   .collect(Collectors.toList());


           shoes = shoes.stream()
                   .filter(shoe-> recipeTags.contains(shoe.getAttributes().getStyle().toLowerCase()))
                   .sorted(Comparator.comparing(HmShoes::getPrice).reversed())
                   .collect(Collectors.toList());

           accessories = accessories.stream()
                   .filter(accs-> recipeTags.contains(accs.getAttributes().getStyle().toLowerCase()))
                   .sorted(Comparator.comparing(HmAccessory::getPrice).reversed())
                   .collect(Collectors.toList());


           if(!accessories.isEmpty()){
               selectedProducts.add(accessories.get(0));
           }
           if(!topClothes.isEmpty()){
               selectedProducts.add(topClothes.get(0));
           }
           if(!bottomClothes.isEmpty()) {
               selectedProducts.add(bottomClothes.get(0));
           }
           if(!shoes.isEmpty()){
               selectedProducts.add(shoes.get(0));
           }

       }

       //create recommendation boxes; check request wedding swimming like and add filter.


        List<RecommendedProduct> recommendedProducts = selectedProducts.stream()
                .map(product -> RecommendedProduct.builder()
                        .code(product.getCode())
                        .price(product.getPrice())
                        .name(product.getName())
                        .attribute(product.getAttributes())
                        .build())
                .collect(Collectors.toList());

        //ONLY BASED ON BROWNS;


        Double totalCost = selectedProducts.stream()
                .mapToDouble(prod-> prod.getPrice().doubleValue())
                .sum();

        //TODO: add recommended basket for this step.

        return RecommendedRecipe.builder()
                .name(recipeRequest.getRecipeName())
                .price(new BigDecimal(totalCost).setScale(2,RoundingMode.DOWN))
                .products(recommendedProducts)
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

    public List<HmClothes> applyCustomerAnalysisFilterForClothes(List<HmClothes> clothes, CustomerHistoryAnalysis analysis){
       return clothes.stream()
                .filter(clothe-> analysis.getColors().contains(clothe.getAttributes().getColor().toLowerCase()) ||
                        analysis.getMaterials().contains(clothe.getAttributes().getMaterial().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<HmShoes> applyCustomerAnalysisFilterForShoes(List<HmShoes> shoes, CustomerHistoryAnalysis analysis){
        return shoes.stream()
                .filter(top-> analysis.getColors().contains(top.getAttributes().getColor().toLowerCase()) ||
                        analysis.getMaterials().contains(top.getAttributes().getMaterial().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<HmAccessory> applyCustomerAnalysisFilterForAccessories(List<HmAccessory> shoes, CustomerHistoryAnalysis analysis){
        return shoes.stream()
                .filter(top-> analysis.getColors().contains(top.getAttributes().getColor().toLowerCase()) ||
                        analysis.getMaterials().contains(top.getAttributes().getMaterial().toLowerCase()))
                .collect(Collectors.toList());
    }
}
