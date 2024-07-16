package com.hm.alpermulayim.dressrecommenderapi.historymanager.repositories;

import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseHistoryRepository  extends JpaRepository<PurchaseHistory,Integer> {
    List<PurchaseHistory> findByCustomerId(Integer customerId);
}
