package com.hm.alpermulayim.dressrecommenderapi.historymanager.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerHistoryAnalysis {
   private Map<String,Long> colorMap;
   private Map<String,Long> materialMap;
   private Map<String,Long> styleMap;
   private Map<String,Long> seasonMap;
   private Optional<String> favColor;
   private Optional<String> favStyle;
   private Optional<String> favMaterial;
   private Optional<String> favSeason;
   private Set<String> colors;
   private Set<String> materials;
   private Set<String> styles;
   private Set<String> seasons;
}
