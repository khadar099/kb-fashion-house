package com.kb.fashionhouse.service;

import com.kb.fashionhouse.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductServiceImpl() {
        products.add(new Product(1L, "T-Shirt", 499));
        products.add(new Product(2L, "Jeans", 1299));
        products.add(new Product(3L, "Jacket", 1999));
    }

    // =========================
    // GET ALL PRODUCTS
    // =========================
    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    // =========================
    // GET PRODUCT BY ID
    // =========================
    @Override
    public Product getProductById(Long id) {

        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
