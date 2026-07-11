package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Product.
 * Exposes public APIs under /products
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add a new product to inventory
    // POST /products
    @PostMapping
    public String addProduct(@RequestBody Product product) {
        int id = productService.addProduct(product);
        return "Product added successfully with id: " + id;
    }

    // Get all products
    // GET /products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get one product by id
    // GET /products/1
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
}
