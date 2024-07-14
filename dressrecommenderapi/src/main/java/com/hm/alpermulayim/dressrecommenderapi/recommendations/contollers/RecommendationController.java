package com.hm.alpermulayim.dressrecommenderapi.recommendations.contollers;

import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecepieRequest;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecommendedProduct;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecommendedRecepie;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.entities.ClothingRecepie;
import com.hm.alpermulayim.dressrecommenderapi.recommendations.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recommendations/recepies")
public class RecommendationController {

    private RecommendationService service;

    @Autowired
    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping
    public RecommendedRecepie getAllRecepies(){
        return service.getRecepies();
    }

    @PostMapping
    public RecommendedRecepie createRecepieWithMyPreferences(@RequestBody RecepieRequest recepieRequest){
        System.out.println(recepieRequest);
        return service.getRecepies();
    }

}
