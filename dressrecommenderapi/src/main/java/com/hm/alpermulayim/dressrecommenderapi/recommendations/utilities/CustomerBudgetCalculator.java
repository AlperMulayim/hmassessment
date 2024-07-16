package com.hm.alpermulayim.dressrecommenderapi.recommendations.utilities;

import com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos.RecipePricePreferences;
import org.springframework.stereotype.Component;

@Component
public class CustomerBudgetCalculator {
    private final Double ONE_HUNDRED = 100.0;
    public CustomerBudget getPrices(Double budget, RecipePricePreferences preferences){
        return CustomerBudget.builder()
                .total(budget)
                .accessories((preferences.getAccessories() == 0) ? 0 :  budget * ( preferences.getAccessories() / ONE_HUNDRED))
                .bottom((preferences.getBottom() == 0) ? 0 :  budget * (preferences.getBottom() / ONE_HUNDRED))
                .top((preferences.getTop() == 0) ? 0 :  budget * (preferences.getTop() / ONE_HUNDRED))
                .shoes( (preferences.getShoes() == 0) ? 0 :  budget * (preferences.getShoes() / ONE_HUNDRED))
                .build();
    }
}
