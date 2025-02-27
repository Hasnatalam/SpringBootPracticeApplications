package com.hasnat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hasnat.entity.Shopkeeper;
import com.hasnat.repository.ShopkeeperRepository;

@Service
public class ShopkeeperService {
    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    public List<Shopkeeper> getAllShopkeepers() {
        return shopkeeperRepository.findAll();
    }

    public Shopkeeper saveShopkeeper(Shopkeeper shopkeeper) {
        return shopkeeperRepository.save(shopkeeper);
    }

    public void deleteShopkeeper(Integer id) {
        shopkeeperRepository.deleteById(id);
    }

}
