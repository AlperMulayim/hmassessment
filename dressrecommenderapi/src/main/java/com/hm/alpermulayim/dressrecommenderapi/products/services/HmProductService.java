package com.hm.alpermulayim.dressrecommenderapi.products.services;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.Product;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.HmProductRepository;
import com.hm.alpermulayim.dressrecommenderapi.products.repositories.ProductAttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HmProductService {
    private HmProductRepository productRepository;

    private ProductAttributesRepository attributesRepository;

    @Autowired
    public HmProductService(HmProductRepository repository){
        this.productRepository = repository;
    }

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }
}
