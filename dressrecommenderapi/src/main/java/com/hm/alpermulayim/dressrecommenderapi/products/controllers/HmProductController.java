package com.hm.alpermulayim.dressrecommenderapi.products.controllers;

import com.hm.alpermulayim.dressrecommenderapi.products.entities.Product;
import com.hm.alpermulayim.dressrecommenderapi.products.services.HmProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class HmProductController {

    private HmProductService service;

    @Autowired
    HmProductController(HmProductService service){
        this.service = service;
    }

    @GetMapping
    public List<Product> getProducts(){
        return this.service.getProducts();
    }
}
