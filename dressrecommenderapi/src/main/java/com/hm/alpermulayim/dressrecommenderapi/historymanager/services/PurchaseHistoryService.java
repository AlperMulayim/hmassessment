package com.hm.alpermulayim.dressrecommenderapi.historymanager.services;

import com.hm.alpermulayim.dressrecommenderapi.historymanager.entites.PurchaseHistory;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.repositories.PurchaseHistoryRepository;
import com.hm.alpermulayim.dressrecommenderapi.historymanager.repositories.PurchasedProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryService {
    private PurchaseHistoryRepository historyRepository;
    private PurchasedProductDetailsRepository productDetailsRepository;

    @Autowired
    public PurchaseHistoryService(PurchaseHistoryRepository historyRepository,
                                  PurchasedProductDetailsRepository productDetailsRepository) {
        this.historyRepository = historyRepository;
        this.productDetailsRepository = productDetailsRepository;
    }


    public List<PurchaseHistory> getPurchaseHistoryForCustomer(Integer customerId){
        return historyRepository.findByCustomerId(customerId);
    }
}
