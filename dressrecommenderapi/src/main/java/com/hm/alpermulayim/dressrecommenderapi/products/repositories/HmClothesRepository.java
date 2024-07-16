package com.hm.alpermulayim.dressrecommenderapi.products.repositories;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.HmClothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HmClothesRepository  extends JpaRepository<HmClothes,Integer> {
    List<HmClothes> findByPriceLessThanAndType(Double price,String type);
}
