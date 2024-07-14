package com.hm.alpermulayim.dressrecommenderapi.products.repositories;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductAttributesRepository extends JpaRepository<ProductAttributes,Integer> {
    Optional<ProductAttributes> findByProductId(Integer id);
}
