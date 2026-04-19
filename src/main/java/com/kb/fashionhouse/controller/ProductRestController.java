package com.kb.fashionhouse.controller;

import com.kb.fashionhouse.model.Product;
import com.kb.fashionhouse.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    // =========================
    // GET ALL PRODUCTS
    // =========================
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // =========================
    // GET PRODUCT BY ID (optional but useful)
    // =========================
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
