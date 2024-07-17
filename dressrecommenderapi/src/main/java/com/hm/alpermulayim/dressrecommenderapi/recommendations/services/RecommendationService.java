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

    public RecommendedRecipe getDefaultRecommendedRecipe() {

        List<HmClothes> topClothes = getUserBudgetClothesByPrice(Double.MAX_VALUE, ClotheType.top);
        List<HmClothes> bottomClothes = getUserBudgetClothesByPrice(Double.MAX_VALUE, ClotheType.bottom);
        List<HmShoes> shoes = getUserBudgetShoes(Double.MAX_VALUE);
        List<HmAccessory> accessories = getUserBudgetAccessories(Double.MAX_VALUE);

        return generateDefaultRecipe("HM's-Favorite-Recipe",
                topClothes.isEmpty() ? null : topClothes.get(0),
                bottomClothes.isEmpty() ? null : bottomClothes.get(0),
                shoes.isEmpty() ? null : shoes.get(0),
                accessories.isEmpty() ? null : accessories.get(0)
        );
    }


    public List<RecommendedRecipe> getRecipesForPreferences(RecipeRequest recipeRequest) {
        Integer defaultNumOfRecipe = 2;
        List<RecommendedRecipe> recommendedRecipes = new ArrayList<>();

        CustomerBudget budget = getCustomerBudget(recipeRequest.getTotalBudget(), recipeRequest.getPricePreferences());
        List<HmClothes> topClothes = getUserBudgetClothesByPrice(budget.getTop(), ClotheType.top);
        List<HmClothes> bottomClothes = getUserBudgetClothesByPrice(budget.getBottom(), ClotheType.bottom);
        List<HmShoes> shoes = getUserBudgetShoes(budget.getShoes());
        List<HmAccessory> accessories = getUserBudgetAccessories(budget.getAccessories());

        List<PurchaseHistory> history = historyService.getPurchaseHistoryForCustomer(recipeRequest.getUserId());
        CustomerHistoryAnalysis customerAnalysis = historyAnalyseManager.analyze(history);

        if (history.isEmpty()) {
            recommendedRecipes.add(generateDefaultRecipe(recipeRequest.getRecipeName(),
                    topClothes.isEmpty() ? null : topClothes.get(0),
                    bottomClothes.isEmpty() ? null : bottomClothes.get(0),
                    shoes.isEmpty() ? null : shoes.get(0),
                    accessories.isEmpty() ? null : accessories.get(0)
            ));
            return recommendedRecipes;
        }

        topClothes = applyCustomerAnalysisFilterForClothes(topClothes, customerAnalysis);
        bottomClothes = applyCustomerAnalysisFilterForClothes(bottomClothes, customerAnalysis);
        shoes = applyCustomerAnalysisFilterForShoes(shoes, customerAnalysis);
        accessories = applyCustomerAnalysisFilterForAccessories(accessories, customerAnalysis);

        Optional<Recipe> recipe = recipeService.getRecipe(recipeRequest.getRecipeName());

        if (recipe.isPresent()) {
            List<String> recipeTags = recipe.get().getTags().stream()
                    .map(RecipeTag::getName).toList();

            topClothes = filterAndSortClothesByRecipeTags(topClothes, recipeTags);
            bottomClothes = filterAndSortClothesByRecipeTags(bottomClothes, recipeTags);
            shoes = filterAndSortShoesByRecipeTags(shoes, recipeTags);
            accessories = filterAndSortAccessoriesByRecipeTags(accessories, recipeTags);

            Integer totalRecipe = recipeRequest.getNumOfRecipe() == null ? defaultNumOfRecipe : recipeRequest.getNumOfRecipe();

            recommendedRecipes = createRecipesFromSelectProducts(totalRecipe,
                    recipeRequest.getRecipeName(),
                    topClothes,
                    bottomClothes,
                    shoes,
                    accessories
            );
        }
        return recommendedRecipes;
    }

    public List<HmClothes> getUserBudgetClothesByPrice(Double price, ClotheType type) {
        return clothesRepository.findByPriceLessThanAndType(price, type.name());
    }

    public List<HmShoes> getUserBudgetShoes(Double price) {
        return shoesRepository.findByPriceLessThan(price);
    }

    public List<HmAccessory> getUserBudgetAccessories(Double price) {
        return accessoriesRepository.findByPriceLessThan(price);
    }

    public CustomerBudget getCustomerBudget(Double totalBudget, RecipePricePreferences preferences) {
        return new CustomerBudgetCalculator().getPrices(totalBudget, preferences);
    }

    public List<HmClothes> applyCustomerAnalysisFilterForClothes(List<HmClothes> clothes, CustomerHistoryAnalysis analysis) {
        return clothes.stream()
                .filter(clothe -> analysis.getColors().contains(clothe.getAttributes().getColor().toLowerCase()) ||
                        analysis.getMaterials().contains(clothe.getAttributes().getMaterial().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<HmShoes> applyCustomerAnalysisFilterForShoes(List<HmShoes> shoes, CustomerHistoryAnalysis analysis) {
        return shoes.stream()
                .filter(top -> analysis.getColors().contains(top.getAttributes().getColor().toLowerCase()) ||
                        analysis.getMaterials().contains(top.getAttributes().getMaterial().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<HmAccessory> applyCustomerAnalysisFilterForAccessories(List<HmAccessory> shoes, CustomerHistoryAnalysis analysis) {
        return shoes.stream()
                .filter(top -> analysis.getColors().contains(top.getAttributes().getColor().toLowerCase()) ||
                        analysis.getMaterials().contains(top.getAttributes().getMaterial().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<RecommendedProduct> getRecommendedProducts(List<Product> selectedProducts) {
        return selectedProducts.stream()
                .map(product -> RecommendedProduct.builder()
                        .code(product.getCode())
                        .price(product.getPrice())
                        .name(product.getName())
                        .attribute(product.getAttributes())
                        .build())
                .collect(Collectors.toList());

    }

    public Double calculateTotalCostForRecipe(List<Product> selectedProducts) {
        return selectedProducts.stream()
                .mapToDouble(prod -> prod.getPrice().doubleValue())
                .sum();
    }

    public List<HmClothes> filterAndSortClothesByRecipeTags(List<HmClothes> clothes, List<String> recipeTags) {
        return clothes.stream()
                .filter(clothe -> recipeTags.contains(clothe.getAttributes().getStyle().toLowerCase()))
                .sorted(Comparator.comparing(HmClothes::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<HmShoes> filterAndSortShoesByRecipeTags(List<HmShoes> shoes, List<String> recipeTags) {
        return shoes.stream()
                .filter(shoe -> recipeTags.contains(shoe.getAttributes().getStyle().toLowerCase()))
                .sorted(Comparator.comparing(HmShoes::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<HmAccessory> filterAndSortAccessoriesByRecipeTags(List<HmAccessory> accessories, List<String> recipeTags) {
        return accessories.stream()
                .filter(accs -> recipeTags.contains(accs.getAttributes().getStyle().toLowerCase()))
                .sorted(Comparator.comparing(HmAccessory::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<RecommendedRecipe> createRecipesFromSelectProducts(Integer totalRecipe,
                                                                   String recipeName,
                                                                   List<HmClothes> tops,
                                                                   List<HmClothes> bottoms,
                                                                   List<HmShoes> shoes,
                                                                   List<HmAccessory> accessories) {

        List<RecommendedRecipe> recommendedRecipes = new ArrayList<>();
        //selection algorithm
        for (int i = 0; i < totalRecipe; ++i) {
            List<Product> selectedProducts = new ArrayList<>();
            if (!accessories.isEmpty()) {
                HmAccessory selected = i >= accessories.size() - 1 ? accessories.get(0) : accessories.get(i);
                selectedProducts.add(selected);
            }
            if (!tops.isEmpty()) {
                HmClothes selected = i >= tops.size() - 1 ? tops.get(0) : tops.get(i);
                selectedProducts.add(selected);
            }
            if (!bottoms.isEmpty()) {
                HmClothes selected = i >= bottoms.size() - 1 ? bottoms.get(0) : bottoms.get(i);
                selectedProducts.add(selected);
            }
            if (!shoes.isEmpty()) {
                HmShoes selected = i >= shoes.size() - 1 ? shoes.get(0) : shoes.get(i);
                selectedProducts.add(selected);
            }

            List<RecommendedProduct> recommendedProducts = getRecommendedProducts(selectedProducts);
            Double totalCost = calculateTotalCostForRecipe(selectedProducts);

            recommendedRecipes.add(
                    RecommendedRecipe.builder()
                            .name(recipeName)
                            .code("HM-Recipe-" + recipeName + "-" + i + 1)
                            .price(new BigDecimal(totalCost).setScale(2, RoundingMode.DOWN))
                            .products(recommendedProducts)
                            .build()
            );
        }
        return recommendedRecipes;
    }

    public RecommendedRecipe generateDefaultRecipe(String recipeName,
                                                   HmClothes top,
                                                   HmClothes bottom,
                                                   HmShoes shoes,
                                                   HmAccessory accessory) {


        List<Product> products = List.of(top, bottom, shoes, accessory);
        List<RecommendedProduct> recommendedProducts = getRecommendedProducts(products);

        Double totalPrice = products.stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum();

        return RecommendedRecipe.builder()
                .name(recipeName)
                .code("Recipe-" + recipeName + "-" + "hm_provided")
                .price(new BigDecimal(totalPrice).setScale(2, RoundingMode.DOWN))
                .products(recommendedProducts)
                .build();
    }
}
