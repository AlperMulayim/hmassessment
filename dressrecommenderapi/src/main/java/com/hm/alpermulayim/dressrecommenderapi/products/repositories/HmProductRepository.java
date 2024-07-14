package com.hm.alpermulayim.dressrecommenderapi.products.repositories;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HmProductRepository  extends JpaRepository<Product,Integer> {
}
