package com.hm.alpermulayim.dressrecommenderapi.products.repositories;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.HmAccessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HmAccessoriesRepository extends JpaRepository<HmAccessory,Integer> {
    List<HmAccessory> findByPriceLessThan(Double price);
}
