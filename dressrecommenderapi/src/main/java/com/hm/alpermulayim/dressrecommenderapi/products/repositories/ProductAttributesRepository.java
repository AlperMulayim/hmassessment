package com.hm.alpermulayim.dressrecommenderapi.products.repositories;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductAttributesRepository extends JpaRepository<ProductAttributes,Integer> {
    Optional<ProductAttributes> findByProductId(Integer id);
    List<ProductAttributes> findByColorIn(Set<String> colors);
}
