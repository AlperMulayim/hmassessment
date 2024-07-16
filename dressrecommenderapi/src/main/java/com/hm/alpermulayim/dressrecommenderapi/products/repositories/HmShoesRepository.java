package com.hm.alpermulayim.dressrecommenderapi.products.repositories;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.HmShoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HmShoesRepository  extends JpaRepository<HmShoes,Integer> {
    List<HmShoes> findByPriceLessThan(Double price);
}
