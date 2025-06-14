package com.example.onlineshop.service;

import com.example.onlineshop.dto.ProductDTO;
import com.example.onlineshop.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> searchProducts(String query);
}