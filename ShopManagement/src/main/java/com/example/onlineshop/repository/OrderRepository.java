package com.example.onlineshop.repository;

import com.example.onlineshop.model.ShopOrder;
import com.example.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ShopOrder, Long> {
    List<ShopOrder> findByUser(User user);
}