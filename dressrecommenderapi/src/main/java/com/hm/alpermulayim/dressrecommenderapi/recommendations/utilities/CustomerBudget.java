package com.hm.alpermulayim.dressrecommenderapi.recommendations.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerBudget {
    private Double total;
    private Double top;
    private Double bottom;
    private Double accessories;
    private Double shoes;
}
