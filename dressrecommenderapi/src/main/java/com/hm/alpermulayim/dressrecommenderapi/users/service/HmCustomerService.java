package com.hm.alpermulayim.dressrecommenderapi.users.service;

import com.hm.alpermulayim.dressrecommenderapi.users.HmCustomer;
import com.hm.alpermulayim.dressrecommenderapi.users.repository.HmCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HmCustomerService {

    private HmCustomerRepository repository;

    @Autowired
    public HmCustomerService(HmCustomerRepository repository){
        this.repository = repository;
    }

    public List<HmCustomer> getAll(){
        return repository.findAll();
    }
}
