package com.hm.alpermulayim.dressrecommenderapi.historymanager.repositories;

import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchasedProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedProductDetailsRepository extends JpaRepository<PurchasedProductDetails,Integer> {
}
