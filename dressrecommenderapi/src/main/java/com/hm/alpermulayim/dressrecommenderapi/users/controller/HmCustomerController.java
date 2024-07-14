package com.hm.alpermulayim.dressrecommenderapi.users.controller;

import com.hm.alpermulayim.dressrecommenderapi.users.HmCustomer;
import com.hm.alpermulayim.dressrecommenderapi.users.service.HmCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class HmCustomerController {

    private HmCustomerService customerService;

    @Autowired
    public  HmCustomerController(HmCustomerService service){
        this.customerService = service;
    }

    @GetMapping()
    public ResponseEntity<List<HmCustomer>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }
}
