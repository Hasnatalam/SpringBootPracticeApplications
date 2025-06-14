package com.example.onlineshop.controller;

import com.example.onlineshop.dto.ProductDTO;
import com.example.onlineshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "products/detail";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String query, Model model) {
        model.addAttribute("products", productService.searchProducts(query));
        model.addAttribute("query", query);
        return "products/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        return "products/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO, 
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "products/form";
        }

        productService.createProduct(productDTO);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("productDTO", productService.getProductById(id));
        return "products/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, 
                              @Valid @ModelAttribute("productDTO") ProductDTO productDTO, 
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "products/form";
        }

        productService.updateProduct(id, productDTO);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}