package com.hm.alpermulayim.dressrecommenderapi.products.services;

import com.hm.alpermulayim.dressrecommenderapi.products.clothes.Product;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HmProductService {
    private HmProductRepository productRepository;

    @Autowired
    public HmProductService(HmProductRepository repository){
        this.productRepository = repository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}
