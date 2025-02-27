package com.hasnat.controller;

import com.hasnat.entity.Shopkeeper;
import com.hasnat.service.ShopkeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shopkeepers")
public class ShopkeeperController {
    @Autowired
    private ShopkeeperService shopkeeperService;

    @GetMapping
    public String listShopkeepers(Model model) {
        model.addAttribute("shopkeepers", shopkeeperService.getAllShopkeepers());
        return "shopkeepers";
    }
    
   
    

    @PostMapping("/saveRegister")
    public String saveShopkeeper(@ModelAttribute("shopkeeper") Shopkeeper shopkeeper) {
        shopkeeperService.saveShopkeeper(shopkeeper);
        return "redirect:/shopkeepers";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("shopkeeper", new Shopkeeper());
        return "register";
    }
    
}
