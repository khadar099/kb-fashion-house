package com.kb.fashionhouse.service;

import com.kb.fashionhouse.model.Product;
import java.util.List;

public interface ProductService {

    // =========================
    // GET ALL PRODUCTS
    // =========================
    List<Product> getAllProducts();

    // =========================
    // GET PRODUCT BY ID
    // =========================
    Product getProductById(Long id);
}
