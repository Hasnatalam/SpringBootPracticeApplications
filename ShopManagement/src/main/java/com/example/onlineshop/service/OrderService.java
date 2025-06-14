package com.example.onlineshop.service;

import com.example.onlineshop.dto.OrderDTO;
import com.example.onlineshop.model.ShopOrder;

import java.util.List;

public interface OrderService {
    ShopOrder createOrder(OrderDTO orderDTO);
    ShopOrder updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
    ShopOrder getOrderById(Long id);
    List<ShopOrder> getAllOrders();
    List<ShopOrder> getOrdersByUser(Long userId);
}