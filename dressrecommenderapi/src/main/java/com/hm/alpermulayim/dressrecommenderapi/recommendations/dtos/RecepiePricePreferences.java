package com.hm.alpermulayim.dressrecommenderapi.recommendations.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecepiePricePreferences {
    private Double top;
    private Double bottom;
    private Double shoes;
    private Double accessories;
}
