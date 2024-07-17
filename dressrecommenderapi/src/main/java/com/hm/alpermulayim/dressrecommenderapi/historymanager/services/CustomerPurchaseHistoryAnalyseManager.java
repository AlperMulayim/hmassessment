package com.hm.alpermulayim.dressrecommenderapi.historymanager.services;

import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.CustomerHistoryAnalysis;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchaseHistory;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchasedProductDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerPurchaseHistoryAnalyseManager {

    public CustomerHistoryAnalysis analyze(List<PurchaseHistory> history){

        List<PurchasedProductDetails> productDetails = history.stream()
                .flatMap(c-> c.getProductDetails().stream())
                .collect(Collectors.toList());

        Map<String,Long> colorFrequency = productDetails.stream()
                  .collect(Collectors.groupingBy(PurchasedProductDetails::getColor, Collectors.counting()));

        Map<String,Long> materialFrequency = productDetails.stream()
                .collect(Collectors.groupingBy(PurchasedProductDetails::getMaterial, Collectors.counting()));

        Map<String,Long> styleFrequency = productDetails.stream()
                .collect(Collectors.groupingBy(PurchasedProductDetails::getStyle, Collectors.counting()));

        Map<String,Long> seasonFrequency = productDetails.stream()
                .collect(Collectors.groupingBy(PurchasedProductDetails::getSeason, Collectors.counting()));

            return CustomerHistoryAnalysis.builder()
                    .styleMap(styleFrequency)
                    .materialMap(materialFrequency)
                    .colorMap(colorFrequency)
                    .seasonMap(seasonFrequency)
                    .favMaterial(findMaxFrequentInMap(materialFrequency))
                    .favStyle(findMaxFrequentInMap(styleFrequency))
                    .favColor(findMaxFrequentInMap(colorFrequency))
                    .favSeason(findMaxFrequentInMap(seasonFrequency))
                    .colors(getKeysListFromMap(colorFrequency))
                    .materials(getKeysListFromMap(materialFrequency))
                    .styles(getKeysListFromMap(styleFrequency))
                    .seasons(getKeysListFromMap(seasonFrequency))
                    .build();
    }

    private Optional<String> findMaxFrequentInMap(Map<String,Long> map){
        Optional<Map.Entry<String,Long>> maxEntry =  map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        if(maxEntry.isPresent()){
            return Optional.of(maxEntry.get().getKey());
        }
        return Optional.empty();
    }

    private Set<String> getKeysListFromMap(Map<String,Long> map){
        return map.keySet().stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

}
