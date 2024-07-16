package com.hm.alpermulayim.dressrecommenderapi.historymanager.services;

import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.CustomerHistoryAnalysis;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchaseHistory;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchasedProductDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CustomerPurchaseHistoryAnalysis {

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

        System.out.println("  >  "+ colorFrequency);
        System.out.println("  >  "+ styleFrequency);
        System.out.println("  >  "+ seasonFrequency);
        System.out.println("  >  "+ materialFrequency);
            return null;
    }

}
